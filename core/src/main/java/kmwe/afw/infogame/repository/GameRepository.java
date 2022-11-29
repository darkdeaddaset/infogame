package kmwe.afw.infogame.repository;

import kmwe.afw.infogame.model.Company;
import kmwe.afw.infogame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> getGameByName(String name);
    List<Game> getGameByCompany(Company company);
}
