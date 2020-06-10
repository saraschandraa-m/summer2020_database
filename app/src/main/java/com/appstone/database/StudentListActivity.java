package com.appstone.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity implements StudentListAdapter.StudentListClickListener {

    /**
     * There are three types of RecyclerViews
     * <p>
     * 1. LinearLayout
     * 2. GridLayout
     * 3. StaggeredGridLayout
     */

    private DatabaseHelper dbHelper;
    private RecyclerView mRcStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        mRcStudentList = findViewById(R.id.rc_student_list);
        mRcStudentList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

//        mRcStudentList.setLayoutManager(new GridLayoutManager(this, 2));
//        mRcStudentList.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));

        dbHelper = new DatabaseHelper(this);
        getDataFromDatabase();
    }

    private void getDataFromDatabase() {
        ArrayList<Student> students = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());

        StudentListAdapter adapter = new StudentListAdapter(this, students);
        adapter.setListener(this);
        mRcStudentList.setAdapter(adapter);
    }

    @Override
    public void onEditClicked(Student student) {
        Toast.makeText(this, "Edit Clicked", Toast.LENGTH_LONG).show();
        Intent editIntent = new Intent(StudentListActivity.this, MainActivity.class);
        editIntent.putExtra("STUDENT", student);
        editIntent.putExtra("IS_EDIT", true);
        startActivityForResult(editIntent, 12345);

    }

    @Override
    public void onDeleteClicked(Student student) {
        dbHelper.deleteDataFromDatabase(student, dbHelper.getWritableDatabase());
        getDataFromDatabase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12345 && resultCode == Activity.RESULT_OK) {
            getDataFromDatabase();
        }
    }
}
