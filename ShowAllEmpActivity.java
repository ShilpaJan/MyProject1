package com.relfor.mydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ShowAllEmpActivity extends AppCompatActivity {

    DBHelper dbHelper;
    RecyclerView listEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_emp);

        listEmployee = findViewById(R.id.rvEmployeeList);
        dbHelper = new DBHelper(this);
        List<Employee> employeeList = dbHelper.showAllEmployee();
        EmployeeListAdapter listAdapter = new EmployeeListAdapter(this, employeeList);
        listEmployee.setLayoutManager(new LinearLayoutManager(this));
        listEmployee.setAdapter(listAdapter);
    }
}
