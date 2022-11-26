package kmwe.afw.infogame.mapper;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO getFromModel(Company company);
    Company getFromDTO(CompanyDTO companyDTO);

    CompanyDTOFull getFromModelForFull(Company company);
    Company getFromDTOOfFull(CompanyDTOFull companyDTOFull);
}
