package storage;

import exception.StorageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jc on 3. 4. 2014.
 */
public class ObjectStorage implements Storage {
    private Map<Class, List<Object>> db = new ConcurrentHashMap<>();

    @Override
    public void createForClass(Class clazz) {
        if (db.containsKey(clazz)) {
            throw new StorageException("Storage for this class already exists");
        }

        db.put(clazz, new ArrayList<>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T selectUnique(final Class<T> clazz, Predicate<? super Object> selector) {
        if (!db.containsKey(clazz)) {
            throw new StorageException("Storage for this class doesn't exists!");
        }

        Stream<Object> filter = db.get(clazz).stream().filter(selector);
        List<Object> resultSet = filter.collect(Collectors.toList());

        if (resultSet == null || resultSet.isEmpty()) {
            return null;
        }

        return (T) resultSet.get(0);
    }

    @Override
    public <T> List<T> select(Predicate<? super Object> selector) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public <T> T insert(T object) {
        if (!db.containsKey(object.getClass())) {
            throw new StorageException("Storage for this class doesn't exists!");
        }

        db.get(object.getClass()).add(object);

        return object;
    }

    @Override
    public <T> T update(T object) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public <T> T delete(T object) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
