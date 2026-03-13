package cat.itacademy.s04.t02.n01.fruit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int weightInKilos;

    protected Fruit() {}

    public Fruit(String name, int weightKilos) {
        this.name = name;
        this.weightInKilos = weightKilos;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeightInKilos() {
        return weightInKilos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeightInKilos(int weightInKilos) {
        this.weightInKilos = weightInKilos;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id + "\n" +
                "name=" + name + "\n" +
                "weightKilos=" + weightInKilos +
                "\n}";
    }
}
