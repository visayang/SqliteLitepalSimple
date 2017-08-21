package com.inventec.sqliteexample;

import android.content.ContentValues;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
//    private List<HashMap<String, Object>> data;
    private List<Student> datas;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyAdapter();
        datas = new ArrayList<>();
        lv.setAdapter(adapter);
    }

    public void create(View view){
        Student ss = new Student();
        ss.setName(UUID.randomUUID().toString().substring(10));
        ss.setAddress("上海");
        ss.save();
    }

    public void delete(View view){
        DataSupport.delete(Student.class,1);
    }
    public void update(View view){
        ContentValues values = new ContentValues();
        values.put("name", "AAA");
        values.put("address", "BBB");
        DataSupport.update(Student.class,values,1);
    }
    public void retrieve(View view){
        datas.clear();
        datas.addAll(DataSupport.findAll(Student.class));
        adapter.notifyDataSetChanged();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder holder = null;
            if(convertView==null){
                holder = new MyHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_spinner_simple, null);
                holder.tv = (TextView) convertView.findViewById(R.id.textView);
                holder.tv2 = (TextView) convertView.findViewById(R.id.textView2);
                convertView.setTag(holder);
            }else{
               holder = (MyHolder) convertView.getTag();
            }

            holder.tv.setText(datas.get(position).getName());

            holder.tv.setText(datas.get(position).getAddress());

            return convertView;
        }

        class MyHolder {
            TextView tv,tv2;
        }

    }
}
