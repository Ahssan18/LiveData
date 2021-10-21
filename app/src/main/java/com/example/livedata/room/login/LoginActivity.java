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
import com.example.livedata.room.helper.Helper;
import com.example.livedata.room.reports.ViewReportsActivity;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etEmail, etPassword;
    private TextView tvRegister;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidgets();
        clickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Helper.getLogin(LoginActivity.this) != null) {
            startActivity(new Intent(LoginActivity.this, ViewReportsActivity.class));
        }
    }

    private void clickListener() {
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                PersonDatabase personDatabase = MyDataBase.getInstance(LoginActivity.this);
                Student student = registerViewModel.personDao.getRecord(email, password);
                if (student != null) {
                    Toast.makeText(LoginActivity.this, "Login succesfully!", Toast.LENGTH_SHORT).show();
                    Helper.saveLogin(student, LoginActivity.this);
                    Intent intent = new Intent(LoginActivity.this, ViewReportsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "No record found!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void initWidgets() {

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
    }
}