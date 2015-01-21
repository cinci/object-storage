package storage;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import storage.object.Item;

/**
 * Created by jc on 3. 4. 2014.
 */
public class StorageTest {
    private static Storage storage;

    @BeforeClass
    public static void beforeClass() {
        storage = new ObjectStorage();
    }

    @Test
    public void testSelectUnique() {
        final String itemName = "my item";

        storage.createForClass(Item.class);
        storage.insert(new Item(itemName));

        Item i = storage.selectUnique(Item.class, item -> ((Item) item).getData().equals("my item"));

        Assert.assertEquals("Correct item was selected", itemName, i.getData());
    }
}
