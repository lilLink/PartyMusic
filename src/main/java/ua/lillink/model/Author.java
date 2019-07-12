package ua.lillink.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "music", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Music> music;

}
