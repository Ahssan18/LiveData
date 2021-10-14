package com.example.livedata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedata.room.MyDataBase;
import com.example.livedata.room.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.CustomView> {
    public List<Student> list;
    private Context context;

    public StudentAdapter(List<Student> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {
        setData(list.get(position), holder);
    }

    private void setData(Student student, CustomView holder) {
        holder.name.setText(student.getName());
        holder.age.setText(student.getAge());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase.getInstance(context).personDao().deleteRecord(student);
                list.remove(student);
                notifyDataSetChanged();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("name", student.getName());
                intent.putExtra("age", student.getAge());
                intent.putExtra("id", student.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {
        TextView name, age, del, edit;

        public CustomView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            age = itemView.findViewById(R.id.tv_age);
            del = itemView.findViewById(R.id.tv_delete);
            edit = itemView.findViewById(R.id.tv_edit);
        }
    }
}
