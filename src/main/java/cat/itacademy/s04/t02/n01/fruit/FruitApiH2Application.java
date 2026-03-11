package cat.itacademy.s04.t02.n01.fruit;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FruitApiH2Application {

    public static void main(String[] args) {
        SpringApplication.run(FruitApiH2Application.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(FruitRepository repository) {

        return(args) -> {

            repository.save(new Fruit("orange", 50));
            repository.save(new Fruit("peach", 25));
            repository.save(new Fruit("pineapple", 10));
            repository.save(new Fruit("apple", 45));
            repository.save(new Fruit("banana", 75));

        };
    } */

}
