package com.relfor.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rizwan on 21-Jan-18.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "emp.sqlite";

    private static final String TABLE_NAME = "emp";

    private static final String EMP_ID = "id";

    private static final String EMP_NAME = "emp_name";

    private static final String EMP_ADD = "emp_add";

    private static final String EMP_PHONE = "emp_phone";

    private static final String EMP_SALARY = "emp_salary";

    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  " + TABLE_NAME
                + "(" + EMP_ID + " integer primary key autoincrement, " + EMP_NAME + " text, " + EMP_ADD + " text, " +
                EMP_PHONE + " text," + EMP_SALARY + " double)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addEmployee(Employee emp) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMP_NAME, emp.getEmpName());
        contentValues.put(EMP_ADD, emp.getEmpAddress());
        contentValues.put(EMP_PHONE, emp.getEmpPhone());
        contentValues.put(EMP_SALARY, emp.getEmpSalary());
        double noOfRows = db.insert(TABLE_NAME, null, contentValues);

        if (noOfRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateEmployee(Employee emp) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMP_NAME, emp.getEmpName());
        contentValues.put(EMP_ADD, emp.getEmpAddress());
        contentValues.put(EMP_PHONE, emp.getEmpPhone());
        contentValues.put(EMP_SALARY, emp.getEmpSalary());
        double noOfRows = db.update(TABLE_NAME,  contentValues, EMP_ID + "="+emp.empId, null);

        if (noOfRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Employee> showAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String query = "select * from " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EMP_ID));
            String name = cursor.getString(cursor.getColumnIndex(EMP_NAME));
            String address = cursor.getString(cursor.getColumnIndex(EMP_ADD));
            String phone = cursor.getString(cursor.getColumnIndex(EMP_PHONE));
            double salary = cursor.getDouble(cursor.getColumnIndex(EMP_SALARY));

            Employee employee = new Employee(id, name, address, phone, salary);
            employees.add(employee);
        }

        return employees;
    }

    public boolean deleteEmployee(int employeeId) {
        SQLiteDatabase database = getWritableDatabase();

        int deletedRow = database.delete(TABLE_NAME, EMP_ID + " = " + employeeId, null);

        if (deletedRow > 0) {
            return true;
        }
        return false;
    }

    public Employee searchEmployee(String nameQuery) {
        Employee employee = null;
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + "  where " + EMP_NAME + " = '" + nameQuery + "'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(EMP_ID));
            String name = cursor.getString(cursor.getColumnIndex(EMP_NAME));
            String address = cursor.getString(cursor.getColumnIndex(EMP_ADD));
            String phone = cursor.getString(cursor.getColumnIndex(EMP_PHONE));
            double salary = cursor.getDouble(cursor.getColumnIndex(EMP_SALARY));

            employee = new Employee(id, name, address, phone, salary);
        }
        return employee;
    }
}
