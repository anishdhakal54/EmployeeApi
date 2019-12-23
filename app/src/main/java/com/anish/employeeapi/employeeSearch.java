package com.anish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anish.employeeapi.API.EmployeeApi;
import com.anish.employeeapi.URL.SearchEmployee;
import com.anish.employeeapi.model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class employeeSearch extends AppCompatActivity {
    private EditText etEmployeeNo;
    private Button btnSearch;
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_search);
        etEmployeeNo=findViewById(R.id.etEmployeeNo);
        btnSearch=findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           loadData();



            }
        });

    }

    private void loadData(){
        tvOutput=findViewById(R.id.tvOutput);

        Retrofit retrofit= new Retrofit.Builder().baseUrl(SearchEmployee.SearchUrl).addConverterFactory(GsonConverterFactory.create()).build();
        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);
        Call<Employee> listCall=employeeApi.getEmployeeByID(Integer.parseInt(etEmployeeNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(employeeSearch.this,response.body().toString(),Toast.LENGTH_SHORT ).show();
                String Content="";
                Content+="Name :"+response.body().getEmployee_name()+"\n";
                Content+="Age :"+response.body().getEmployee_age()+"\n";
                Content+="Salary :"+response.body().getEmployee_salary()+"\n";

                tvOutput.setText(Content);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(employeeSearch.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
