package net.world.qubag.callbacks;

import net.world.qubag.models.Category;
import net.world.qubag.models.Item;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mvnpavan on 05/03/17.
 */

public interface CallbackUtil {

    void onComplete(List<String> categories,HashMap<String,Category> data);

    void addItemToCart(Item item);
}
