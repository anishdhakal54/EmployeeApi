package com.anish.employeeapi.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anish.employeeapi.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployViewHolder>{
    Context context;
    List<Employee> employeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_all_employee,parent,false);
        return new EmployViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployViewHolder holder, int position) {
        Employee employee=employeeList.get(position);
        holder.tvName.setText(employee.getEmployee_name());
        holder.tvAge.setText(employee.getEmployee_age());
        holder.tvSalary.setText(employee.getEmployee_salary());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvSalary,tvAge;


        public EmployViewHolder(@NonNull View itemView) {
            super(itemView);
        tvName=itemView.findViewById(R.id.tvName);
        tvAge=itemView.findViewById(R.id.tvAge);
        tvSalary=itemView.findViewById(R.id.tvSalary);

        }
    }


}