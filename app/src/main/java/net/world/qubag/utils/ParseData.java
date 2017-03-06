package net.world.qubag.utils;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Context;
import android.os.AsyncTask;

import net.world.qubag.callbacks.CallbackUtil;
import net.world.qubag.models.Category;
import net.world.qubag.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mvnpavan on 05/03/17.
 */

public class ParseData extends AsyncTask<Void,Void,Void> {
     private CallbackUtil callback;

     HashMap<String , Category> categories = new HashMap<>();
     List<String> items = new ArrayList<>();

    public ParseData(CallbackUtil callback) {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            JSONObject jsonObject = new JSONObject(Utils.SAMPLE_CATEGORIES);
            JSONArray categoriesArray = jsonObject.getJSONArray("Categories");
            for (int i = 0; i < categoriesArray.length(); i++) {
                Category category = new Category();
                category.setCategory(categoriesArray.getString(i));
                categories.put(category.getCategory() , category);
                items.add(category.getCategory());
            }

            JSONObject object = new JSONObject(Utils.SAMPLE_ITEMS);

            JSONArray itemsArray = object.getJSONArray("Items");

            for (int i = 0; i < itemsArray.length(); i++) {

                Item item = new Item();

                JSONObject itemObject = itemsArray.getJSONObject(i);
                item.setName(itemObject.getString("name"));
                item.setPrice(itemObject.getString("price"));

                categories.get(itemObject.getString("category")).setItem(item);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.onComplete(items,categories);
    }
}
