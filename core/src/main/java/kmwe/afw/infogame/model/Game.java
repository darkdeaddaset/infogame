package kmwe.afw.infogame.model;

import com.sun.istack.NotNull;
import kmwe.afw.infogame.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "games", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinTable(name = "games_companies", schema = "public", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
    private Company company;

    @ManyToOne
    @JoinTable(name = "games_publisher", schema = "public", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
    private Company publisher;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "genres_games", schema = "public", joinColumns = @JoinColumn(name = "game_id"))
    private Set<Genre> genres;
}