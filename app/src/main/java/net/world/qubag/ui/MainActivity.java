package net.world.qubag.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.world.qubag.R;
import net.world.qubag.adpaters.ListAdapter;
import net.world.qubag.callbacks.CallbackUtil;
import net.world.qubag.models.Category;
import net.world.qubag.models.Item;
import net.world.qubag.utils.ParseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mvnpavan on 04/03/17.
 */

public class MainActivity extends AppCompatActivity implements CallbackUtil {

    private RecyclerView recyclerView;
    private HashMap<String,Category> data;
    private List<String> categories;
    private GridLayoutManager layoutManager;
    private Spinner spinner;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming Soon.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.myList);

        spinner = (Spinner) findViewById(R.id.spinner);

        new ParseData(this).execute();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                List<Item> items;
                if (pos == 0){
                    items = getListOfItems("");

                }else {
                    items = getListOfItems(categories.get(pos-1));
                }

                if (items.size() == 0){
                    Snackbar.make(recyclerView, "No items found.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                listAdapter.resetData(items);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onComplete(List<String> categories, HashMap<String,Category> data) {
        this.categories = categories;
        this.data = data;
        initList(getListOfItems(""));
    }

    private List<Item> getListOfItems(String val){
        List<Item> items = new ArrayList<>();

        if (val.equals("")) {
            for (String key : data.keySet()) {
                Category category = data.get(key);
                items.addAll(category.getItem());
            }
        }else {
            items.addAll(data.get(val).getItem());
        }

        return items;
    }

    @Override
    public void addItemToCart(Item item) {
        Snackbar.make(recyclerView, R.string.addesdtocart, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    private void initList(List<Item> items){
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setVisibility(View.VISIBLE);

        listAdapter = new ListAdapter(this,items,this);

        recyclerView.setAdapter(listAdapter);

        List<String> data = new ArrayList<>();
        data.add("All");
        data.addAll(categories);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

    }
}
