package com.example.sqlitesaveimagekpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnThem;
    ListView lvDoVat;
    ArrayList<DoVat> arrayDoVat;
    DoVatAdapter adapter;
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem = (Button) findViewById(R.id.buttonThem);
        lvDoVat = (ListView) findViewById(R.id.listviewDoVat);
        arrayDoVat = new ArrayList<>();

        adapter = new DoVatAdapter(this,R.layout.dong_do_vat,arrayDoVat);
        lvDoVat.setAdapter(adapter);
        // Gọi database
        database = new Database(this,"QuanLy1.sqlite",null,1);
        // tạo bảng database
        database.QueryData("CREATE TABLE IF NOT EXISTS DoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");

//        // get data
        Cursor cursor = database.GetData("SELECT * FROM DoVat");

        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String ten = cursor.getString(1);
                String moTa = cursor.getString(2);
                byte[] hinhAnh = cursor.getBlob(3);

                arrayDoVat.add(new DoVat(id, ten, moTa, hinhAnh));
            }
        } finally {
            cursor.close(); // Đảm bảo đóng cursor sau khi sử dụng xong
        }


        adapter.notifyDataSetChanged();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ThemDoVatActivity.class));
            }
        });
    }
}