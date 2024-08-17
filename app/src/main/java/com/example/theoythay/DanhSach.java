package com.example.theoythay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DanhSach extends AppCompatActivity {
    private Button addButton, editButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> employeeList;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach);
        init();
        employeeList =new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,employeeList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view , position, id) ->{
            selectedIndex =position;
            editButton.setEnabled(true);
        });
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(DanhSach.this,them.class);
            startActivityForResult(intent, 1);

        });
        editButton.setOnClickListener(v -> {
            if (selectedIndex != -1){
                Intent intent = new Intent(DanhSach.this,sua.class);
                intent.putExtra("employee",employeeList.get(selectedIndex));
                intent.putExtra("index",selectedIndex);
                startActivityForResult(intent, 2);
            }else {
                Toast.makeText(this,"Vui lòng chọn nhân viên để sửa",Toast.LENGTH_SHORT).show();
            }

        });







        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showCloseDialog(i);
                return true;
            }
        });

    }

    private  void init()
    {
        addButton = findViewById(R.id.addButton);
        editButton = findViewById(R.id.editButton);
        listView = findViewById(R.id.listView);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK && intent != null) {
            String employee = intent.getStringExtra("employee");
            int index = intent.getIntExtra("index", -1);

            if (requestCode == 1) {
                if (employee != null) {
                    employeeList.add(employee);
                }
            } else if (requestCode == 2) {
                if (index != -1 && employee != null) {
                    employeeList.set(index, employee);
                }
            }

            adapter.notifyDataSetChanged();
        }
    }
    private  void showCloseDialog(final int i)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Xóa item");
        builder.setMessage("Bạn có chắc xóa item này không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                employeeList.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(DanhSach.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
