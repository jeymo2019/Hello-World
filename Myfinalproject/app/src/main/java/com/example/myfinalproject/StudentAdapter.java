package com.example.myfinalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.viewHolder> {
    Context context;
    Activity activity;
    ArrayList<StudentModel> arrayList;
    DatabaseHelper databaseHelper;
    Integer pos;

    public StudentAdapter(Context context, Activity activity, ArrayList<StudentModel> arrayList) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public StudentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_student, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.viewHolder holder, int position) {
        holder.Id.setText(arrayList.get(position).getId());
        holder.Name.setText(arrayList.get(position).getName());
        holder.Department.setText(arrayList.get(position).getDepartment());
        holder.Marks.setText(arrayList.get(position).getMarks());
        databaseHelper=new DatabaseHelper(context);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView Id, Name, Department, Marks;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.tvstudentId);
            Name = itemView.findViewById(R.id.tvstudentName);
            Department = itemView.findViewById(R.id.tvstudentDepart);
            Marks = itemView.findViewById(R.id.tvstudentMarks);
        }
    }
}
