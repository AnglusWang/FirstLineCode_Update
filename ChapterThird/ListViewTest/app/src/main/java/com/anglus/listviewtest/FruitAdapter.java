package com.anglus.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anglus on 2016/10/8.
 * 水果 LIstView 适配器
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);// 获取当前项的 Fruit 实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitText = (TextView) view.findViewById(R.id.fruit_text);
            view.setTag(viewHolder);// 将 ViewHolder 存储在 View 中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();// 重新获取 ViewHolder
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitText.setText(fruit.getName());
        return view;
    }

    private class ViewHolder {
        ImageView fruitImage;
        TextView fruitText;
    }
}
