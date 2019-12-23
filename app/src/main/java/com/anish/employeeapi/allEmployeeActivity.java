package com.anish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.anish.employeeapi.API.EmployeeApi;
import com.anish.employeeapi.URL.API_URL;
import com.anish.employeeapi.model.Employee;
import com.anish.employeeapi.model.EmployeeAdapter;

import java.net.URI;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class allEmployeeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
//    private  TextView tvOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employee);

//        tvOutput=findViewById(R.id.tvOutput);

        recyclerView=findViewById(R.id.recycleView);

        Retrofit retrofit=new Retrofit.Builder().baseUrl(API_URL.base_url).addConverterFactory(GsonConverterFactory.create()).build();
        final EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);

        Call<List<Employee>> listCall=employeeApi.getAllEmployees();

        //Asynchoronous

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(allEmployeeActivity.this, "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }


                List<Employee> employeeList = response.body();
//
//                for (Employee emp : employeeList){
//                    String data = "";
//                    data += "Name is :" +emp.getEmployee_name() + "\n";
//                    data += "Address is :" +emp.getEmployee_salary() + "\n";
//                    data += "Age is :" +emp.getEmployee_age() + "\n";
//                    data += "..............." + "\n";
//                    tvOutput.append(data);
//
//
////
////
//                }



                EmployeeAdapter employeeAdapter=new EmployeeAdapter(allEmployeeActivity.this, employeeList);
                recyclerView.setAdapter(employeeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(allEmployeeActivity.this,LinearLayoutManager.HORIZONTAL,false));            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg", "onFailure:" + t.getLocalizedMessage());
                Toast.makeText(allEmployeeActivity.this, "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
