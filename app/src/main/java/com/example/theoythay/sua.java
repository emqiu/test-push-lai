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

public class sua extends AppCompatActivity {
    private EditText etName,etAge,etAddress;
    private RadioGroup rgGender;
    private Button btnEdit;
    private RadioButton rbMale, rbFemale;
    private int employeeIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua);
        init();


        Intent intent=getIntent();
        if (intent != null) {
            String employee = intent.getStringExtra("employee");
            employeeIndex = intent.getIntExtra("index", -1);
            if (employee != null) {
                String[] employeeDetails = employee.split(" - ");
                etName.setText(employeeDetails[0]);
                etAge.setText(employeeDetails[1]);
                etAddress.setText(employeeDetails[2]);
                if ("Nam".equals(employeeDetails[3])) {
                    rbMale.setChecked(true);
                } else {
                    rbFemale.setChecked(true);
                }
            }
        }
        btnEdit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String address = etAddress.getText().toString();
            String gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();

            if (!name.isEmpty() && !age.isEmpty() && !address.isEmpty()) {
                String employee = name + " - " + age + " - " + address + " - " + gender;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("employee", employee);
                resultIntent.putExtra("index", employeeIndex);
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
        rbFemale = findViewById(R.id.rbFemale);
        btnEdit = findViewById(R.id.btnEdit);
        rbMale.setChecked(true);
    }
}
