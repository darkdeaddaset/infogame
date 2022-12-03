package kmwe.afw.infogame.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "strategy", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Strategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinTable(name = "authors", schema = "public", joinColumns = @JoinColumn(name = "strategy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private User author;

    @NotNull
    @ManyToOne
    @JoinTable(name = "games_guide", schema = "public", joinColumns = @JoinColumn(name = "strategy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private Game game;

    @Lob
    @NotNull
    @Column(name = "description")
    private String description;
}