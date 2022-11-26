package kmwe.afw.infogame.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyDTO {
    private String name;
    private long companyId;
    private long publisherId;
}
