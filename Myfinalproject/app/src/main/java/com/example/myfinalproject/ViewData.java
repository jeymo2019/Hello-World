package com.example.myfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {



    RecyclerView myRc;
    ArrayList<StudentModel> arrayList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        myRc = findViewById(R.id.rvAllstudents);
        databaseHelper = new DatabaseHelper(this);
        showStudent();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                databaseHelper.delete(arrayList.get(position).getId());
                arrayList.remove(position);
                Toast.makeText(ViewData.this, "Student deleted!", Toast.LENGTH_LONG).show();
                showStudent();
            }
        });
        helper.attachToRecyclerView(myRc);
    }

    public void showStudent() {
        arrayList = new ArrayList<>(databaseHelper.getAllStudents());
        myRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myRc.setItemAnimator(new DefaultItemAnimator());
        StudentAdapter adapter = new StudentAdapter(getApplicationContext(), this, arrayList);
        myRc.setAdapter(adapter);
    }
}