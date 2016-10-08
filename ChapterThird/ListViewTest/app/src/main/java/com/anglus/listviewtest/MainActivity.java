package com.anglus.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFurits();// 初始化水果数据
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,
                R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFurits() {
        Fruit apple = new Fruit("Apple", R.mipmap.ic_launcher);
        fruitList.add(apple);
        Fruit banana = new Fruit("banana", R.mipmap.ic_launcher);
        fruitList.add(banana);
        Fruit orange = new Fruit("orange", R.mipmap.ic_launcher);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("watermelon", R.mipmap.ic_launcher);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("pear", R.mipmap.ic_launcher);
        fruitList.add(pear);
        Fruit grape = new Fruit("grape", R.mipmap.ic_launcher);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("pineapple", R.mipmap.ic_launcher);
        fruitList.add(pineapple);
        Fruit cherry = new Fruit("cherry", R.mipmap.ic_launcher);
        fruitList.add(cherry);
    }
}
