package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.simpletodo.EditTaskDialogFragment.EditNameDialogListener;

import static android.R.attr.name;
import static com.codepath.simpletodo.R.id.editTaskName;

public class ViewItemActivity extends AppCompatActivity implements EditNameDialogListener {
    Task to;
    int position;
    int REQUEST_CODE = 400;
    TextView tName;
    TextView tDate;
    TextView tDesc;
    TextView tPriority;
    TextView tStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        to = (Task) getIntent().getSerializableExtra("taskObject");
        position = getIntent().getIntExtra("taskPosition", 0);

        tName = (TextView) findViewById(R.id.editTaskName);
        tName.setText(to.getTaskName());

        tDate = (TextView) findViewById(R.id.editTaskDate);
        tDate.setText(to.getTaskDate());

        tDesc = (TextView) findViewById(R.id.editTaskDescription);
        tDesc.setText(to.getTaskDetail());


        tPriority = (TextView) findViewById(R.id.editTaskPriority);
        tPriority.setText(to.getTaskPriority());


        tStatus = (TextView) findViewById(R.id.editTaskStatus);
        tStatus.setText(to.getTaskStatus());


    }

    public void onEditTask(View v) {

        FragmentManager fm = getSupportFragmentManager();
        EditTaskDialogFragment editTaskDialogFragment =
                EditTaskDialogFragment.newInstance(position,to);
        editTaskDialogFragment.show(fm, "activity_edit_item");

    }

    public void onBackTask(View v) {

        Intent parenti = new Intent();
        parenti.putExtra("editedTaskObj", to);
        parenti.putExtra("taskPosition", position); // ints work too
        setResult(RESULT_OK, parenti);
      finish();

    }


    @Override
    public void onFinishEditDialog(Task tOb, int p) {
        to = tOb;
        position=p;
        tName.setText(tOb.getTaskName());
        tDate.setText(tOb.getTaskDate());
        tDesc.setText(tOb.getTaskDetail());
        tPriority.setText(tOb.getTaskPriority());
        tStatus.setText(tOb.getTaskStatus());

        //   finish();
    }

}