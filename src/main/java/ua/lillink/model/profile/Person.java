package ua.lillink.model.profile;

import lombok.*;
import ua.lillink.model.Music;
import ua.lillink.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id")
    private Photo photo;

    @OneToMany(mappedBy = "music", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Music> music;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
