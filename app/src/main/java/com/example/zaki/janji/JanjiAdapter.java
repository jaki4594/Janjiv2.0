package com.example.zaki.janji;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class JanjiAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Janji_data> mDataSource;
    private String resEtat;

    public JanjiAdapter(android.content.Context context, ArrayList<Janji_data> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item_janji, parent, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.id_janji);
        TextView dateTextView = (TextView) rowView.findViewById(R.id.p1);
        TextView ticketidTextView = (TextView) rowView.findViewById(R.id.v1);
        TextView emailTextView = (TextView) rowView.findViewById(R.id.p2);
        TextView etatTextView = (TextView) rowView.findViewById(R.id.v2);

        Janji_data recipe = (Janji_data) getItem(position);
        titleTextView.setText(recipe.id);
        dateTextView.setText(recipe.p1);
        ticketidTextView.setText(recipe.v1);
        emailTextView.setText(recipe.p2);
        etatTextView.setText(recipe.v2);


        return rowView;
    }
}
