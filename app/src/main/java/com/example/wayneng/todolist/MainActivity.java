package com.example.wayneng.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String task_title = "";
    private static final String task_description = "";
    private static final String task_priority = "";
    private List<Tasks> mTaskList = new ArrayList<Tasks>();
    private TextView mEmptyListLabel;

    private void updateTasks(){
        mEmptyListLabel = (TextView) findViewById(R.id.empty_list_label);

        if (mTaskList.isEmpty()){
            mEmptyListLabel.setVisibility(View.VISIBLE); // makes label visible
        } else {
            String title, description, priority;
            mEmptyListLabel.setVisibility(View.GONE); // makes label invisible

            for (int i = 0; i < mTaskList.size(); i++) {
                title = mTaskList.get(i).getTitle();
                description = mTaskList.get(i).getDescription();
                priority = mTaskList.get(i).getPriority();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);

                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == 1){
            if (data == null) {
                return;
            }
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String priority = data.getStringExtra("priority");

            mTaskList.add(new Tasks(title, description, priority));
        }
    }
}
