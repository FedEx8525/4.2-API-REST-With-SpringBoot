package cat.itacademy.s04.t02.n01.fruit.exception;

public class FruitNotFoundException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Fruit not found with id: ";

    public FruitNotFoundException(String id) {
        super(DEFAULT_MESSAGE + id);
    }
}
