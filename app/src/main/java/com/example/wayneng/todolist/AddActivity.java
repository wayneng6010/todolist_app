package com.example.wayneng.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private Button mAddTask;
    private EditText mTaskTitle;
    private EditText mTaskDescription;
    private Spinner mTaskPriority;
    private TextView mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.task_priority);
        //create a list of items for the spinner.
        String[] items = new String[]{"High", "Medium", "Low"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        mAddTask = (Button) findViewById(R.id.add_button);
        mTaskTitle = (EditText)findViewById(R.id.task_title);
        mTaskDescription = (EditText)findViewById(R.id.task_description);
        mTaskPriority  = (Spinner)findViewById(R.id.task_priority);
        mCancel = (TextView) findViewById(R.id.cancel);

        mAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                String task_title = mTaskTitle.getText().toString();
//                String task_description = mTaskDescription.getText().toString();
//                String text = mTaskPriority.getSelectedItem().toString();

                if (mTaskTitle.getText().toString().equals("")) {
                    mTaskTitle.setError("Task title cannot be empty");
                    mTaskTitle.requestFocus();
                    Toast.makeText(getApplicationContext(), "Please fill in the title", Toast.LENGTH_SHORT).show();
                } else {
                    returnTaskDetails();
                }

            }
        });

        mCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

        public void returnTaskDetails(){
            Intent data = new Intent();

            data.putExtra("title", mTaskTitle.getText().toString());
            data.putExtra("description", mTaskDescription.getText().toString());
            data.putExtra("priority", mTaskPriority.getSelectedItem().toString());

            // use bundle to pass back multiple data
//            Bundle task_details_bundle = new Bundle();
//            task_details_bundle.putString("title", mTaskTitle.getText().toString());
//            task_details_bundle.putString("description", mTaskDescription.getText().toString());
//            task_details_bundle.putString("priority", mTaskPriority.getSelectedItem().toString());

//            data.putExtras(task_details_bundle);
            setResult(RESULT_OK, data);
            finish();

        }

}
