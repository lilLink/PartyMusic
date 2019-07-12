package ua.lillink.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "music")
public class Music {

    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private Long musicId;

    @Column(name = "name")
    private String name;

}
