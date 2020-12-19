package com.example.myfinalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editDepartment, editMarks,editId;
    Button btnAddData;
    Button btnviewAll;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);


        editName = (EditText) findViewById(R.id.editText_name);
        editDepartment = (EditText) findViewById(R.id.editText_department);
        editMarks = (EditText) findViewById(R.id.editText_marks);
        editId = (EditText) findViewById(R.id.editText_Id);
        btnAddData = (Button) findViewById(R.id.btn_add);
        btnviewAll = (Button) findViewById(R.id.btn_view);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        AddData();
        //viewAll();
        UpdateData();
    }
    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpate = myDb.updateData(editId.getText().toString(), editName.getText().toString(),
                                editDepartment.getText().toString(),
                                editMarks.getText().toString());
                        if(isUpate == true)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "No Data Updated", Toast.LENGTH_LONG).show();

                    }
                }

        );
    }
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editDepartment.getText().toString(),
                                editMarks.getText().toString());

                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                        Toast.makeText(MainActivity.this, "Failed To insert Data", Toast.LENGTH_LONG).show();
                    }
                }

        );

    }
    /*public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() ==0){
                            //Some Message Here
                            showMessage("Error","No data Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+res.getString(1)+"\n");
                            buffer.append("Department :"+res.getString(2)+"\n");
                            buffer.append("Marks :"+res.getString(3)+"\n\n");
                        }

                        //show all Data
                        showMessage("Students Data", buffer.toString());

                    }
                }

        );

    }*/
    public void showMessage(String title, String Message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void launchViewData(View view) {
        Intent intent = new Intent(this, ViewData.class);
        startActivity(intent);


    }
}