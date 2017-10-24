package com.example.fake2edison.wordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fake2edison on 2017/10/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int resourceId;
    public WordAdapter(Context context, int textViewResourceId, List<Word> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Word word = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView word1 = (TextView)view.findViewById(R.id.listWord1);
        TextView word2 = (TextView)view.findViewById(R.id.listWord2);
        word1.setText(word.getListWord1());
        word2.setText(word.getListWord2());
        return view;
    }
}
