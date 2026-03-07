package cat.itacademy.s04.t02.n01.fruit.model;

import jakarta.persistence.*;

@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int weightKilos;

    protected Fruit() {}

    public Fruit(Long id, String name, int weightKilos) {
        this.id = id;
        this.name = name;
        this.weightKilos = weightKilos;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeightKilos() {
        return weightKilos;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weightKilos=" + weightKilos +
                '}';
    }
}
