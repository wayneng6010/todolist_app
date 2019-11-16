package com.example.wayneng.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private static final String task_title = "";
    private static final String task_description = "";
    private static final String task_priority = "";
    private ArrayList<Tasks> taskList = new ArrayList<Tasks>();
    private TextView mEmptyListLabel;
    private RelativeLayout mListLayout;
    private TableLayout mListLayoutOuter;

    @SuppressLint("ResourceType")
//    private void updateTasks(){
//        mEmptyListLabel = (TextView) findViewById(R.id.empty_list_label);
//
//        if (mTaskList.isEmpty()){
//            mEmptyListLabel.setVisibility(View.VISIBLE); // makes label visible
//        } else {
//            String title, description, priority;
//            mEmptyListLabel.setVisibility(View.GONE); // makes label invisible
//            mListLayoutOuter = (TableLayout) findViewById(R.id.list_layout_outer);
//            mListLayoutOuter.removeAllViews();
//
//            for (int i = 0; i < mTaskList.size(); i++) {
//                title = mTaskList.get(i).getTitle();
//                description = mTaskList.get(i).getDescription();
//                priority = mTaskList.get(i).getPriority();
//
//                CheckBox mCheckBox = new CheckBox(this);
//                mCheckBox.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
//                mCheckBox.setId(i);
//
//                TextView mTitle = new TextView(this);
//                mTitle.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
//                mTitle.setGravity(Gravity.CENTER_VERTICAL);
//                mTitle.setText(title);
//                mTitle.setPadding(100,0,0,0);
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)mTitle.getLayoutParams();
//                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
//                mTitle.setLayoutParams(layoutParams);
//
//                RelativeLayout mNewItem = new RelativeLayout(this);
//                mNewItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
//                mNewItem.setPadding(20,20,20,20);
//
//                ((RelativeLayout) mNewItem).addView(mCheckBox);
//                ((RelativeLayout) mNewItem).addView(mTitle);
//
//                ((LinearLayout) mListLayoutOuter).addView((RelativeLayout) mNewItem, i);
//            }
//        }
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Do Do"); // for set actionbar title

        final ListView listView = findViewById(R.id.listView);
        final TasksAdapter adapter = new TasksAdapter(this, taskList);

        //adapter.setData(taskList);
        listView.setAdapter(adapter);

        FloatingActionButton newTaskBtn = (FloatingActionButton) findViewById(R.id.newTaskBtn);
        newTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(MainActivity.this, AddActivity.class);
                //startActivityForResult(i, 1);
                final EditText taskTitleInput = new EditText(MainActivity.this);
                taskTitleInput.setSingleLine();
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Add a new task")
                        .setMessage("What is your new task")
                        .setView(taskTitleInput)
                        .setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Tasks newTask = new Tasks(taskTitleInput.getText().toString(), null, null);
                                adapter.add(newTask);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
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

            taskList.add(new Tasks(title, description, priority));
            //updateTasks();
        }
    }

    class TasksAdapter extends ArrayAdapter<Tasks> {

        public TasksAdapter(Context context, ArrayList<Tasks> tasks) {
            super(context, 0, tasks);
        }

        void setData(List<Tasks> mTaskList){
            taskList.clear();
            taskList.addAll(mTaskList);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return taskList.size();
        }

        @Override
        public Tasks getItem(int position){
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.item, parent, false);
            final TextView textView = rowView.findViewById(R.id.task);
            textView.setText(taskList.get(position).getTitle());
            return rowView;
        }
    }
}
