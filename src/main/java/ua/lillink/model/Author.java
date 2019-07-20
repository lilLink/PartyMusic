package ua.lillink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "name_author")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "author_music",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "music_id")})
    private Set<Music> musics;

}
