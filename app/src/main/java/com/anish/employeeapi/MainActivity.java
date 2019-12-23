package com.anish.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
        Button btnShowAllEmployee,btnSearchEmployee,btnRegisterEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowAllEmployee=findViewById(R.id.btnShowAllEmployee);
        btnSearchEmployee=findViewById(R.id.btnSearchEmployee);
        btnRegisterEmployee=findViewById(R.id.btnRegisterEmployee);

        btnShowAllEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,allEmployeeActivity.class);
                startActivity(intent);
            }
        });

        btnSearchEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, employeeSearch.class);
                startActivity(intent);

            }
        });
        btnRegisterEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,registerEmployeeActivity.class);
                startActivity(intent);
            }
        });


    }
}
