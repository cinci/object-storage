package storage;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jc on 3. 4. 2014.
 */
public interface Storage {

    public void createForClass(Class clazz);

    public <T> T selectUnique(Class<T> clazz, Predicate<? super Object> selector);

    public <T> List<T> select(Predicate<? super Object> selector);

    public <T> T insert(T object);

    public <T> T update(T object);

    public <T> T delete(T object);

}
