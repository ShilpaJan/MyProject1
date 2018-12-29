package com.relfor.mydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEmpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] roomname = { "Bedroom", "Living room", "Dining room", "Kitchen"};
    String[] appliancename = { "Light1", "Light2", "Light3", "Fan"};

    EditText edtScheduleName;
    Button btnSave;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);
        Spinner spin=(Spinner)findViewById(R.id.spinner2);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,appliancename);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);

        Spinner spinner=(Spinner)findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,roomname);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        edtScheduleName = findViewById(R.id.edtScheduleName);


        btnSave = findViewById(R.id.btnSave);

        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtScheduleName.getText().toString().trim();





                Employee employee = new Employee();
                employee.setEmpName(name);

               boolean isInserted = dbHelper.addEmployee(employee);
               if(isInserted){

                   edtScheduleName.setText("");
                   Toast.makeText(getBaseContext(), "Emplyee details inserted", Toast.LENGTH_LONG).show();
               }

            }
        });
    }

    @Override
    public void onItemSelected (AdapterView<?> adapterView, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),roomname[position],Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),appliancename[position],Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected (AdapterView<?> arg0) {

    }
}