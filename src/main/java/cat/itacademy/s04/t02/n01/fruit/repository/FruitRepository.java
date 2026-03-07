package cat.itacademy.s04.t02.n01.fruit.repository;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;

public interface FruitRepository extends CrudRepository<Fruit, Long> {

    @Override
    Fruit save(Fruit fruit);
}
