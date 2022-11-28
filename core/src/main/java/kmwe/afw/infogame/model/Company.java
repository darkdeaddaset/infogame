package kmwe.afw.infogame.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "company", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "country")
    private String country;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "company")
    private Set<Game> developGames;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "publisher")
    private Set<Game> publishingGames;
}
