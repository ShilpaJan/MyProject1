package com.relfor.mydatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAddEmp, btnSearchEmp, btnViewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddEmp = findViewById(R.id.btnAddEmp);
        btnSearchEmp = findViewById(R.id.btnSearchEmp);
        btnViewAll = findViewById(R.id.btnViewAll);

        btnAddEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEmpActivity.class);
                startActivity(intent);
            }
        });
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAllEmpActivity.class);
                startActivity(intent);
            }
        });

        btnSearchEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchEmployeeActivity.class);
                startActivity(intent);
            }
        });

    }
}
