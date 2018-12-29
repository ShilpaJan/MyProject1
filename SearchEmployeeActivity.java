package com.relfor.mydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchEmployeeActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText edtEmployeeNameSearch, edtEmployeeName, edtEmployeeAddress, edtEmployeeSalary, edtEmployeePhone;
    Button btnSearch, btnUpdate;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);
        dbHelper = new DBHelper(this);

        edtEmployeeNameSearch = findViewById(R.id.edtEmployeeNameSearch);
        edtEmployeeName = findViewById(R.id.edtEmployeeName);
        edtEmployeeAddress = findViewById(R.id.edtEmployeeAddress);
        edtEmployeeSalary = findViewById(R.id.edtEmployeeSalary);
        edtEmployeePhone = findViewById(R.id.edtEmployeePhone);
        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtEmployeeNameSearch.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Please enter name to search", Toast.LENGTH_SHORT).show();
                } else {
                    employee = dbHelper.searchEmployee(name);
                    if (employee == null) {
                        Toast.makeText(getApplicationContext(), "No employee found with this name", Toast.LENGTH_SHORT).show();
                    } else {
                        edtEmployeeName.setText(employee.empName);
                        edtEmployeeAddress.setText(employee.empAddress);
                        edtEmployeePhone.setText(employee.empPhone);
                        edtEmployeeSalary.setText(String.valueOf(employee.empSalary));
                    }
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This method is for updating employee info
                int id = employee.empId;
                String name = edtEmployeeNameSearch.getText().toString().trim();
                String phone = edtEmployeePhone.getText().toString().trim();
                String address = edtEmployeeAddress.getText().toString().trim();
                String salary = edtEmployeeSalary.getText().toString().trim();
                double salaryValue = Double.parseDouble(salary);

                Employee employee = new Employee(id, name, address, phone, salaryValue);
                boolean isUpdated = dbHelper.updateEmployee(employee);

                if(isUpdated){
                    Toast.makeText(SearchEmployeeActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SearchEmployeeActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
