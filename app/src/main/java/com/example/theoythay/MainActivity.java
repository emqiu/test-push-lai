package com.example.theoythay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= etUsername.getText().toString();
                String password= etPassword.getText().toString();

                if(username.equals("quy") && password.equals("123456"))
                {
                    Intent intent = new Intent(MainActivity.this,DanhSach.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"Thông tin tài khoản hoặc mật khẩu sai!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void init()
    {
        etUsername= findViewById(R.id.etUsername);
        etPassword= findViewById(R.id.etPassword);
        btnLogin= findViewById(R.id.btnLogin);
    }
}