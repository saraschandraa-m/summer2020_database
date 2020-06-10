package com.appstone.database;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentListHolder> {
    /**
     * 1. Create a view holder class and extend the view holder class to RecyclerView.ViewHolder
     * 2. Extend the parent class to RecyclerView.Adapter<with the view holder created>
     * 3. Implement the abstract methods (onCreateViewHolder, onBindViewHolder, getItemCount)
     * 4. Create a constructor to get the context and data from the activity.
     */

    private Context context;
    private ArrayList<Student> students;
    private StudentListClickListener listener;

    public StudentListAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    public void setListener(StudentListClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("OnCreate-Adapter", "called");
        View view = LayoutInflater.from(context).inflate(R.layout.cell_student, parent, false);
        StudentListHolder holder = new StudentListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListHolder holder, int position) {
        final Student currentStudent = students.get(position);

        holder.mTvRegNo.setText(String.valueOf(currentStudent.regNo));
        holder.mTvStudentName.setText(currentStudent.studentName);
        holder.mTvStudentBranch.setText(currentStudent.studentBranch);
        holder.mTvBookBorrowed.setText(currentStudent.bookborrowed);
        holder.mTvIssueDate.setText(currentStudent.issueDate);
        holder.mTvReturnDate.setText(currentStudent.returnDate);


        holder.mRlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onEditClicked(currentStudent);
                }
            }
        });

        holder.mRlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDeleteClicked(currentStudent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentListHolder extends RecyclerView.ViewHolder {

        private TextView mTvRegNo;
        private TextView mTvStudentName;
        private TextView mTvStudentBranch;
        private TextView mTvBookBorrowed;
        private TextView mTvIssueDate;
        private TextView mTvReturnDate;

        private RelativeLayout mRlEdit;
        private RelativeLayout mRlDelete;

        public StudentListHolder(@NonNull View itemView) {
            super(itemView);


            mTvRegNo = itemView.findViewById(R.id.tv_reg_title);
            mTvStudentName = itemView.findViewById(R.id.tv_student_name);
            mTvStudentBranch = itemView.findViewById(R.id.tv_student_branch);
            mTvBookBorrowed = itemView.findViewById(R.id.tv_book_borrowed);
            mTvIssueDate = itemView.findViewById(R.id.tv_issue_date);
            mTvReturnDate = itemView.findViewById(R.id.tv_return_date);

            mRlEdit = itemView.findViewById(R.id.rl_edit);
            mRlDelete = itemView.findViewById(R.id.rl_delete);
        }
    }

    public interface StudentListClickListener {
        void onEditClicked(Student student);

        void onDeleteClicked(Student student);
    }

}
