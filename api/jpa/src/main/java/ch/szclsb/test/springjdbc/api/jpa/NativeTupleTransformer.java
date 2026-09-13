package ch.szclsb.test.springjdbc.api.jpa;

import jakarta.persistence.Query;
import org.hibernate.jpa.spi.NativeQueryTupleTransformer;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.TupleTransformer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

public class NativeTupleTransformer<T> implements TupleTransformer<T> {
    private final NativeQueryTupleTransformer nativeQueryTupleTransformer;
    private final Function<jakarta.persistence.Tuple, T> function;

    public NativeTupleTransformer(Function<jakarta.persistence.Tuple, T> function) {
        this.nativeQueryTupleTransformer = new NativeQueryTupleTransformer();
        this.function = function;
    }

    @Override
    public T transformTuple(Object[] tuple, String[] aliases) {
        var nativeTuple = nativeQueryTupleTransformer.transformTuple(tuple, aliases);
        return function.apply(nativeTuple);
    }

    public NativeQuery<T> apply(Query query) {
        return transformNative(query, this);
    }

    public static <T> NativeQuery<T> transformNative(Query query, NativeTupleTransformer<T> tupleTransformer) {
        NativeQuery<?> hibernateQuery = query.unwrap(org.hibernate.query.NativeQuery.class);
        return hibernateQuery.setTupleTransformer(tupleTransformer);
    }

    public static <E extends Enum<E>> Optional<E> tryParseEnum(String enumName, Class<E> enumClass) {
        try {
            return Optional.of(Enum.valueOf(enumClass, enumName));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static LocalDate toLocalDate(Date date) {
        return date == null ? null : date.toLocalDate();
    }
}
