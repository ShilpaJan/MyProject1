package com.relfor.mydatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rizwan on 14-Jan-18.
 */

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder> {
    List<Employee> mEmployeeList;
    DBHelper dbHelper;

    // Constructor
    EmployeeListAdapter(Context context, List<Employee> employeeList) {
        this.mEmployeeList = employeeList;
        dbHelper = new DBHelper(context);
    }


    // Creation of row
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_employee_list, parent, false);

        return new MyViewHolder(itemView);
    }

    // Data bind
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Employee employee = mEmployeeList.get(position);
        holder.txtEmployeeName.setText("Name : " + employee.getEmpName());
        holder.txtEmployeeAddress.setText("Address : " + employee.getEmpAddress());
        holder.txtEmployeePhone.setText("Phone : " + employee.getEmpPhone());
        holder.txtEmployeeSalary.setText("Salary : Rs" + employee.getEmpSalary());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleted = dbHelper.deleteEmployee(employee.getEmpId());
                if (isDeleted) {
                    mEmployeeList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

    }

    // count row
    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    // Recycling
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtEmployeeName, txtEmployeeAddress, txtEmployeePhone, txtEmployeeSalary;

        Button btnDelete;

        MyViewHolder(View itemView) {
            super(itemView);
            txtEmployeeAddress = (TextView) itemView.findViewById(R.id.txtEmployeeAddress);
            txtEmployeeName = (TextView) itemView.findViewById(R.id.txtEmployeeName);
            txtEmployeePhone = (TextView) itemView.findViewById(R.id.txtEmployeePhone);
            txtEmployeeSalary = (TextView) itemView.findViewById(R.id.txtEmployeeSalary);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }

    }
}