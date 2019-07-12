package ua.lillink.model.profile;

import lombok.*;
import ua.lillink.model.Music;

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
@Table(name = "person")
public class Author implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long personId;

    @Column(name = "user_name")
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id")
    private Photo photo;

    @OneToMany(mappedBy = "music", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Music> music;
}
