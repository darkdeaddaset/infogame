package kmwe.afw.infogame.service.converter;

import kmwe.afw.infogame.model.enums.Genre;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {
    @Override
    public String convertToDatabaseColumn(Genre genre) {
        if (genre == null) {
            return null;
        }
        return genre.getValue();
    }

    @Override
    public Genre convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        return Stream.of(Genre.values())
                .filter(f -> f.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
