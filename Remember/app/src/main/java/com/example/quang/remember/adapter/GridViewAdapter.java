package com.example.quang.remember.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.remember.R;
import com.example.quang.remember.model.ItemNote;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ItemNote> arrItem;

    public GridViewAdapter(Context context, int layout, ArrayList<ItemNote> arrItem) {
        this.context = context;
        this.layout = layout;
        this.arrItem = arrItem;
    }

    private class ViewHolder{
        TextView tvTitle;
        TextView tvDate;
    }

    @Override
    public int getCount() {
        return arrItem.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewRow = view;
        if(viewRow == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflater.inflate(layout,viewGroup,false);

            ViewHolder holder = new ViewHolder();
            holder.tvTitle = viewRow.findViewById(R.id.tvItemTitle);
            holder.tvDate = viewRow.findViewById(R.id.tvItemDate);

            viewRow.setTag(holder);
        }
        ItemNote itemGridView = arrItem.get(i);
        ViewHolder holder = (ViewHolder) viewRow.getTag();
        holder.tvTitle.setText(itemGridView.getTitle());
        holder.tvDate.setText(itemGridView.getDate());
        return viewRow;
    }
}
