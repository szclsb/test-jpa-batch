package ch.szclsb.test.jpa.api.jdbctempl;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public interface CustomRowMapper<T> extends RowMapper<T> {
    static <E extends Enum<E>> Optional<E> tryParseEnum(String enumName, Class<E> enumClass) {
        try {
            return Optional.of(Enum.valueOf(enumClass, enumName));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    static LocalDate toLocalDate(Date date) {
        return date == null ? null : date.toLocalDate();
    }
}
