package kmwe.afw.infogame.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
@AllArgsConstructor
public class CompanyInfo {
    private final String name;
    private final Path logo;
    private final String country;
}
