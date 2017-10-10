package com.example.jeremiahwong.triviart;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadArticle extends Fragment{
    public static ReadArticle newInstance(String articleTopic) {
        ReadArticle art = new ReadArticle();
        Bundle args = new Bundle();
        args.putString("ITEM", articleTopic);
        art.setArguments(args);
        return art;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Bundle args = getArguments();
        String articleTopic = (String) args.get("ITEM");
        View view = inflater.inflate(R.layout.article,container, false);
        ((TextView)view.findViewById(R.id.articleView)).setText(articleTopic);
        return view;
    }
}
