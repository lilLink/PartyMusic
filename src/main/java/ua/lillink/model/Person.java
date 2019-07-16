package ua.lillink.model;

import lombok.*;
import ua.lillink.model.profile.Photo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id")
    private Photo photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
