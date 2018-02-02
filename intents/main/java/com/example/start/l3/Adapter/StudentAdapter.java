package com.example.start.l3.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.start.l3.Model.Student;
import com.example.start.l3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by start on 2017-11-01.
 */

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> students = new ArrayList<>();
    private ArrayList<Student> studentsListRemove = new ArrayList<>();
    private Student student;
    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.debtors_list_item, null);
        student = (Student) getItem(i);
        ((TextView) row.findViewById(R.id.StudentName)).setText(student.getName());
        ((TextView) row.findViewById(R.id.StudentPhone)).setText(student.getPhone());
//        ((CheckedTextView) row.findViewById(R.id.student_checked)).setEnabled(true);
//        ((CheckedTextView) row.findViewById(R.id.student_checked)).setVisibility(View.VISIBLE);
        return row;
    }
    public void addRemoveUsers(int position, View view) {
        CheckedTextView ctv = (CheckedTextView) view.findViewById(R.id.student_checked);
        ctv.setChecked(!ctv.isChecked());
        if (ctv.isChecked()) {
            studentsListRemove.add((Student) this.getItem(position));
        } else {
            studentsListRemove.remove((Student) this.getItem(position));
        }
    }

    public StudentAdapter removeUsers() {
        for (Student student : studentsListRemove) {
            students.remove(student);
        }
        studentsListRemove = new ArrayList<>();

        return this;
    }
}
