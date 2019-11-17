package com.example.wayneng.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String task_title = "";
    private static final String task_description = "";
    private static final String task_priority = "";
    private ArrayList<Tasks> taskList = new ArrayList<Tasks>();
    private TextView mEmptyListLabel;
    private TextView mAllTaskLabel;
    private RelativeLayout mListLayout;
    private TableLayout mListLayoutOuter;
    private CheckBox mIsDone;

    private RecyclerView listView;
    private Parcelable mListState;
    private RecyclerView.LayoutManager mLayoutManager;
    private TasksAdapter adapter;

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

        mEmptyListLabel = (TextView) findViewById(R.id.empty_list_label);
        mAllTaskLabel = (TextView) findViewById(R.id.all_task_label);
        listView = findViewById(R.id.listView);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);

        if (savedInstanceState != null) {
            Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();

            //mLayoutManager.onRestoreInstanceState(mListState);
            //adapter.notifyDataSetChanged();
            taskList = (ArrayList) savedInstanceState.getParcelableArrayList("taskList");
            //ArrayList<Tasks> list = savedInstanceState.getParcelable("taskList");
            //TasksAdapter adapter = new TasksAdapter(list);
            //listView.setAdapter(adapter);

            //mEmptyListLabel.setVisibility(View.GONE); // makes label invisible
            //mAllTaskLabel.setVisibility(View.VISIBLE); // makes label visible
            //listView.setVisibility(View.VISIBLE); // makes list visible
        }

        if (taskList.isEmpty()) {
            mEmptyListLabel.setVisibility(View.VISIBLE); // makes label visible
//            mAllTaskLabel.setVisibility(View.GONE); // makes label invisible
            listView.setVisibility(View.GONE); // makes list invisible
        } else {
            mEmptyListLabel.setVisibility(View.GONE); // makes label invisible
            mAllTaskLabel.setVisibility(View.VISIBLE); // makes label visible
            listView.setVisibility(View.VISIBLE); // makes list visible
        }



        adapter = new TasksAdapter(taskList);
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
                                taskList.add(new Tasks(taskTitleInput.getText().toString(), null, null, false));
                                //adapter.add(newTask);
                                adapter.notifyDataSetChanged();
                                mEmptyListLabel.setVisibility(View.GONE); // makes label invisible
                                listView.setVisibility(View.VISIBLE); // makes list visible
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        if (savedInstanceState != null) {
            Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();

            //mLayoutManager.onRestoreInstanceState(mListState);
            //adapter.notifyDataSetChanged();
            ArrayList<Tasks> list = savedInstanceState.getParcelable("taskList");
            TasksAdapter adapter = new TasksAdapter(list);
            //listView.setAdapter(adapter);

            mEmptyListLabel.setVisibility(View.GONE); // makes label invisible
            mAllTaskLabel.setVisibility(View.VISIBLE); // makes label visible
            listView.setVisibility(View.VISIBLE); // makes list visible
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            if (data == null) {
                return;
            }
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String priority = data.getStringExtra("priority");

            taskList.add(new Tasks(title, description, priority, false));
            //updateTasks();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        private Tasks mTask;

        public TaskHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.task);
            //mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            //mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Tasks task) {
            mTask = task;
            mTitleTextView.setText(mTask.getTitle());
            //mDateTextView.setText(mTask.getDate().toString());
            //mSolvedCheckBox.setChecked(mTask.isSolved());
        }
    }

    class TasksAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Tasks> mTasks;
        public TasksAdapter(List<Tasks> tasks) {
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            //LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item, parent, false);
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Tasks crime = mTasks.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        public List<Tasks> getList() {
            return mTasks;
        }

//        public TasksAdapter(Context context, ArrayList<Tasks> tasks) {
//            super(context, 0, tasks);
//        }
//
//        void setData(List<Tasks> mTaskList) {
//            taskList.clear();
//            taskList.addAll(mTaskList);
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public int getCount() {
//            return taskList.size();
//        }
//
//        @Override
//        public Tasks getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            final View rowView = inflater.inflate(R.layout.item, parent, false);
//            final TextView textView = rowView.findViewById(R.id.task);
//            textView.setText(taskList.get(position).getTitle());
//            textView.setSelected(taskList.get(position).getIsDone());
//            rowView.setTag(position);
//            return rowView;
//        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save list state
        //mListState = mLayoutManager.onSaveInstanceState();
        //savedInstanceState.putParcelableArrayList("taskList", (ArrayList) taskList);
        savedInstanceState.putParcelableArrayList("taskList", (ArrayList) new ArrayList<Tasks>(adapter.getList()));
        super.onSaveInstanceState(savedInstanceState);
    }

//    protected void onRestoreInstanceState(Bundle state) {
//        super.onRestoreInstanceState(state);
//        // Retrieve list state and list/item positions
//        if(state != null){
//            mListState = state.getParcelable("taskList");
//        }
//    }
}
