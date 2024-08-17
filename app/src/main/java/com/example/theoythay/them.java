package com.example.theoythay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class them extends AppCompatActivity {
    private EditText etName,etAge,etAddress;
    private RadioGroup rgGender;
    private Button btnAdd;
    private RadioButton rbMale, rbFemale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them);
        init();

        btnAdd.setOnClickListener(v->{
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String address = etAddress.getText().toString();
            String gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();

            if (!name.isEmpty() && !age.isEmpty() && !address.isEmpty()) {
                String employee = name + " - " + age + " - " + address + " - " + gender;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("employee", employee);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private  void init()
    {
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAddress = findViewById(R.id.etAddress);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale =findViewById(R.id.rbFemale);
        btnAdd = findViewById(R.id.btnAdd);
        rbMale.setChecked(true);
    }
}
