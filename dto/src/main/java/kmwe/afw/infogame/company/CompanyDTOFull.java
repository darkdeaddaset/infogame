package kmwe.afw.infogame.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTOFull {
    private String name;
    private byte[] logo;
    private char[] description;
}