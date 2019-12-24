package com.anish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anish.employeeapi.API.EmployeeAPI;
import com.anish.employeeapi.Models.Employee;
import com.anish.employeeapi.Models.EmployeeCUD;
import com.anish.employeeapi.URL.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateDeleteActivity extends AppCompatActivity {
    EditText etEmployeeNumber, etEmployeeName, etEmployeeSalary,etAge;
    Button btnUpdateDeleteSearch, btnUpdate, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        etEmployeeNumber = findViewById(R.id.etUpdateid);
        etEmployeeName = findViewById(R.id.etUpdateName);
        etEmployeeSalary = findViewById(R.id.etUpdateSalary);
        etAge=findViewById(R.id.etUpdateAge);
        btnUpdateDeleteSearch = findViewById(R.id.btnUpdateDeleteSearch);
        btnUpdate = findViewById(R.id.btnUpdateEmployee);
        btnDelete = findViewById(R.id.btnDelete);

        btnUpdateDeleteSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

    }

    private void loadData() {
        EmployeeAPI employeeAPI=URL.createInstance().create(EmployeeAPI.class);
        Call<Employee> listCall=employeeAPI.getEmployeeByID(Integer.parseInt(etEmployeeNumber.getText().toString()));
        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                    etEmployeeName.setText(response.body().getEmployee_name());
                    etEmployeeSalary.setText(Float.toString(response.body().getEmployee_salary()));
                    etAge.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Errror", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void updateEmployee(){
        EmployeeAPI employeeAPI=URL.createInstance().create(EmployeeAPI.class);

        EmployeeCUD employeeCUD=new EmployeeCUD(
                etEmployeeName.getText().toString(),Float.parseFloat(etEmployeeSalary.getText().toString()),
                Integer.parseInt(etAge.getText().toString())
        );
        Call<Void> voidCall=employeeAPI.UpdateEmployee(Integer.parseInt(etEmployeeNumber .getText().toString()),employeeCUD);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void deleteEmployee(){
        EmployeeAPI employeeAPI=URL.createInstance().create(EmployeeAPI.class);
        Call<Void> voidCall=employeeAPI.deleteEmployee(Integer.parseInt(etEmployeeNumber.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
