package dh.meli.reviewtests.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "hair_color")
    private String hairColor;

    @Column(name = "skin_color")
    private String skinColor;

    @Column(name = "eye_color")
    private String eyeColor;

    @Column(name = "birth_year")
    private String birthYear;

    @Column(name = "home_world")
    private String homeWorld;

    private String gender;
    private String species;
    private int height;
    private int mass;
}
