package com.example.fake2edison.wordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fake2edison on 2017/10/18.
 */

public class wordLandAdapter extends ArrayAdapter<wordLand> {
    private int resourceId;
    public wordLandAdapter(Context context, int textViewResourceId, List<wordLand> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        wordLand word = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView word1 = (TextView)view.findViewById(R.id.wordlistLand_item);
        word1.setText(word.getWord());
        return view;
    }
}
