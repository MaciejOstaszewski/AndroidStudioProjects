package com.example.start.l3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.example.start.l3.Adapter.StudentAdapter;
import com.example.start.l3.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {

    Intent intent;
    Button addButton;
    Button removeButton;
    ListView studentsList;
    //    ArrayAdapter<String> adapter;
    StudentAdapter adapter;
    List<String> checked = new ArrayList<>();
    List<String> myStrings = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        intent = new Intent(this, AddStudentAcitivity.class);
        addButton = (Button) findViewById(R.id.ADD);
        removeButton = (Button) findViewById(R.id.REMOVE);
        studentsList = (ListView) findViewById(R.id.studentsList);
        addButton.setOnClickListener(addListener);
        removeButton.setOnClickListener(removeListener);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStrings);
        adapter = new StudentAdapter(this, students);
        studentsList.setAdapter(adapter);
        studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.addRemoveUsers(position, view);
            }
        });

    }

    public View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivityForResult(intent, 1);
        }
    };

    public View.OnClickListener removeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            adapter.removeUsers().notifyDataSetChanged();
        }
    };






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                CharSequence name = data.getCharSequenceExtra("Name");
                CharSequence phone = data.getCharSequenceExtra("Phone");
                students.add(new Student(String.valueOf(name), String.valueOf(phone)));
//                myStrings.add(String.valueOf(name)+" "+String.valueOf(phone));
                adapter.notifyDataSetChanged();
            }
            if (requestCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}
