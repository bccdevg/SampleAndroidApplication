package com.example.iwaki.mysampleapplication.sample.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.iwaki.mysampleapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewSampleActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);

        List<Map<String, String>> listItems = new ArrayList<>();
        final String TITLE = "title";
        final String MONEY = "money";
        HashMap<String, String> item = new HashMap<>();
        item.put(TITLE, "Android プログラミングレシピ");
        item.put(MONEY, "￥3600");
        listItems.add(item);
        item = new HashMap<>();
        item.put(TITLE, "Android プログラミングレシピ2");
        item.put(MONEY, "￥5600");
        listItems.add(item);

        // アダプターに以下を設定する
        //  ・表示させるアイテムのリスト
        //  ・各アイテムを配置するビュー
        //  ・ビューの項目とアイテムの項目の紐づけ情報
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                listItems,
                android.R.layout.simple_list_item_2,
                new String[]{TITLE, MONEY},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text1 = (TextView)view.findViewById(android.R.id.text1);
                TextView text2 = (TextView)view.findViewById(android.R.id.text2);
                Log.d("★", "onItemClick: title = " + text1.getText() + " money = " + text2.getText());
            }
        });


    }


}
