package ru.tt.DogShelter.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;;
import java.util.Objects;


@Entity
public class Cat {

    public Cat(Long id, String breed, String name, int yearOfBirth, String description) {
        this.id = id;
        this.breed = breed;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.description = description;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String breed;
    private String name;
    private int yearOfBirth;
    private String description;

    public Cat() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return id == cat.id && yearOfBirth == cat.yearOfBirth && Objects.equals(breed, cat.breed) && Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, name, yearOfBirth);
    }
}
