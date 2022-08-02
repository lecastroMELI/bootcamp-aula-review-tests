package dh.meli.reviewtests.util;

import dh.meli.reviewtests.model.Person;

import java.util.ArrayList;
import java.util.List;

public class GeneratePerson {

    public static Person newPerson1ToSave() {
        return Person.builder()
                .name("Luke Skywalker")
                .height(172)
                .mass(77)
                .hairColor("blond")
                .skinColor("fair")
                .gender("male")
                .eyeColor("blue")
                .birthYear("19BBY")
                .homeWorld("Tatooine")
                .species("Human")
                .build();
    }

    public static Person newPerson2ToSave() {
        return Person.builder()
                .name("R2-D2")
                .height(96)
                .mass(32)
                .hairColor("NA")
                .skinColor("white, blue")
                .gender("NA")
                .eyeColor("red")
                .birthYear("33BBY")
                .homeWorld("Naboo")
                .species("Droid")
                .build();
    }

    public static Person validPerson1() {
        return Person.builder()
                .id(1)
                .name("Luke Skywalker")
                .height(172)
                .mass(77)
                .hairColor("blond")
                .skinColor("fair")
                .gender("male")
                .eyeColor("blue")
                .birthYear("19BBY")
                .homeWorld("Tatooine")
                .species("Human")
                .build();
    }

    public static Person validPerson2() {
        return Person.builder()
                .id(2)
                .name("R2-D2")
                .height(96)
                .mass(32)
                .hairColor("NA")
                .skinColor("white, blue")
                .gender("NA")
                .eyeColor("red")
                .birthYear("33BBY")
                .homeWorld("Naboo")
                .species("Droid")
                .build();
    }

    public static List<Person> listPersons() {
        List<Person> list = new ArrayList<>();

        list.add(validPerson1());
        list.add(validPerson2());

        return list;
    }
}
