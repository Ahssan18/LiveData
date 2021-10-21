package com.example.livedata.room.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.livedata.R;
import com.example.livedata.room.database.MyDataBase;
import com.example.livedata.room.database.PersonDatabase;

public class RegisterActivity extends AppCompatActivity {

    private Button count;
    private EditText etname, etEmail, etPassword;
    int id;
    private TextView tvLogin;
    RegisterViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setData();
        clickListener();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        id = intent.getIntExtra("id", -1);
        etname.setText(name);
        etEmail.setText(age);
        count.setText("Update");

    }

    private void clickListener() {
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myViewModel.getData().setValue(i++);
                String name = etname.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                PersonDatabase personDatabase = MyDataBase.getInstance(RegisterActivity.this);
                viewModel.registerAccount(new Student(name, email, password));
                Toast.makeText(RegisterActivity.this, "Registered User Successfully!", Toast.LENGTH_SHORT).show();

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void setData() {

    }

    private void initWidgets() {
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        count = findViewById(R.id.btn_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etname = findViewById(R.id.et_name);
        tvLogin = findViewById(R.id.tv_login);
    }
}