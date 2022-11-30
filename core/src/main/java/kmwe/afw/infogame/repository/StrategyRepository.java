package kmwe.afw.infogame.repository;

import kmwe.afw.infogame.model.Game;
import kmwe.afw.infogame.model.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long> {
    Optional<Strategy> getStrategiesByName(String name);
    Optional<Strategy> getStrategiesByGame(Game game);
}
