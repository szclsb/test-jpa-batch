package ch.szclsb.test.springjdbc.api.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final HttpClient.Builder clientBuilder;
    private final ObjectMapper objectMapper;

    public ApiClient() {
        this.clientBuilder = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL);
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public <W> HttpResponse.BodySubscriber<W> asJSON(HttpResponse.ResponseInfo responseInfo, TypeReference<W> targetType) {
        return HttpResponse.BodySubscribers.mapping(
                HttpResponse.BodySubscribers.ofInputStream(),
                inputStream -> {
                    if (responseInfo.statusCode() != 200) {
                        return null;
                    }
                    try (var is = inputStream) {
                        return objectMapper.readValue(is.readAllBytes(), targetType);  // without .readAllBytes throws error: java.net.http.HttpClient chunked transfer encoding, state: READING_LENGTH
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
    }

    public <T> T fetch(URI uri, String method, TypeReference<T> typeReference) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(uri)
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        var client = clientBuilder.build();
        var response = client.send(request, responseInfo -> asJSON(responseInfo, typeReference));
        if (response.statusCode() != 200) {
            throw new RuntimeException("Unexpected status code " + response.statusCode());
        }
        return response.body();
    }
}
