package utilities;

import java.io.Serializable;

public class Tuple<T, E, K> implements Serializable {
    private final T entityOne;
    private final E entityTwo;
    private final K entityThree;

    public Tuple(T entityOne, E entityTwo, K entityThree) {
        this.entityOne = entityOne;
        this.entityTwo = entityTwo;
        this.entityThree = entityThree;
    }

    public T getEntityOne() {
        return entityOne;
    }

    public E getEntityTwo() {
        return entityTwo;
    }

    public K getEntityThree() {
        return entityThree;
    }
}
