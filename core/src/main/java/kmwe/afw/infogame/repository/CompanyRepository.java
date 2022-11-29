package kmwe.afw.infogame.repository;

import kmwe.afw.infogame.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> getCompaniesByName(String name);
}
