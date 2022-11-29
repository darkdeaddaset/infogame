package kmwe.afw.infogame.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Lob
    @NotNull
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "company")
    private List<Game> developGames;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "publisher")
    private List<Game> publishingGames;
}