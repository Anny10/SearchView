package com.yjmfortune.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MySearchView.SearchHintListener {
    MySearchView my_searchView;
    ArrayAdapter<String> hintAdapter;
    TextView SeelctText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        my_searchView = (MySearchView) findViewById(R.id.my_searchView);
        //获取查询的总数据
        GetData();
        //设置数据,  //设置回掉
        hintAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, myHintList);
        my_searchView.setHintAdapter(hintAdapter, this);
        SeelctText = (TextView) findViewById(R.id.my_text);

    }

    //获取查询的总数据
    ArrayList<String> myList = new ArrayList<String>();
    //获取数据
    private void GetData() {
        for (int i = 0; i < 20; i++) {
            myList.add("lx" + i);
        }

        for (int i = 0; i < 20; i++) {
            myList.add("lj" + i);
        }

        for (int i = 0; i < 20; i++) {
            myList.add("sr" + i);
        }
    }

    //获取查询的总数据
   ArrayList<String> myHintList= new ArrayList<String>();

    //设置hint数据
    @Override
    public void UpdateHintAdapter(String mys) {
        myHintList.clear();
        for(String s :myList){
            if(s.contains(mys)){
                myHintList.add(s);
            }
        }
        if(hintAdapter!=null){
            hintAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void setSelectText(String selectItem) {
        SeelctText.setText(selectItem);
    }
}
