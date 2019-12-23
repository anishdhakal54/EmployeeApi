package com.anish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anish.employeeapi.API.EmployeeApi;
import com.anish.employeeapi.URL.API_URL;
import com.anish.employeeapi.model.EmployeeCUD;

import java.security.PublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registerEmployeeActivity extends AppCompatActivity {
    private EditText etName,etSalary,etAge;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);
        etName=findViewById(R.id.etName);
        etAge=findViewById(R.id.etAge);
        etSalary=findViewById(R.id.etSalary);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    private  void Register(){
String name=etName.getText().toString();
float salary=Float.parseFloat(etSalary.getText().toString());
int age=Integer.parseInt(etAge.getText().toString());

        EmployeeCUD employeeCUD=new EmployeeCUD(name,salary,age);

        Retrofit retrofit=new Retrofit.Builder().baseUrl(API_URL.base_url).addConverterFactory(GsonConverterFactory.create()).build();

        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);
        Call<Void> voidCall=employeeApi.registerEmployee(employeeCUD);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(registerEmployeeActivity.this, "You have been successfully registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(registerEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
