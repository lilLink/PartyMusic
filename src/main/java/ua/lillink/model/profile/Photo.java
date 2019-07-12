package ua.lillink.model.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "photo_id")
    private Long photoId;

    @Column(name = "name")
    private String name;

}
