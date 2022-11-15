package kodlama.io.devs.entities.concretes;

import kodlama.io.devs.entities.abstracts.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "programming_languages")
public class ProgrammingLanguage implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "name")
    private String name;

    @OneToMany (mappedBy = "programmingLanguage")
    private List<Technology> technologies;
}
