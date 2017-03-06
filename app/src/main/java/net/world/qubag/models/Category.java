package net.world.qubag.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvnpavan on 05/03/17.
 */

public class Category {

    String category;

    List<Item> item;

    public Category() {
        item = new ArrayList<>();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<Item> getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item.add(item);
    }
}
