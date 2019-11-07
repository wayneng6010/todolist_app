package com.example.wayneng.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.RelativeLayout.CENTER_VERTICAL;
import static android.widget.RelativeLayout.RIGHT_OF;

public class MainActivity extends AppCompatActivity {

    private static final String task_title = "";
    private static final String task_description = "";
    private static final String task_priority = "";
    private List<Tasks> mTaskList = new ArrayList<Tasks>();
    private TextView mEmptyListLabel;
    private RelativeLayout mListLayout;
    private TableLayout mListLayoutOuter;
//    private CheckBox mCheckBox;
//    private TextView mTextView;
//    Context context = this;

    @SuppressLint("ResourceType")
    private void updateTasks(){
        mEmptyListLabel = (TextView) findViewById(R.id.empty_list_label);

        if (mTaskList.isEmpty()){
            mEmptyListLabel.setVisibility(View.VISIBLE); // makes label visible
        } else {
            String title, description, priority;
            mEmptyListLabel.setVisibility(View.GONE); // makes label invisible
            mListLayout =  findViewById(R.id.list_layout);
            mListLayoutOuter = (TableLayout) findViewById(R.id.list_layout_outer);
            mListLayoutOuter.removeAllViews();

            for (int i = 0; i < mTaskList.size(); i++) {
                title = mTaskList.get(i).getTitle();
                description = mTaskList.get(i).getDescription();
                priority = mTaskList.get(i).getPriority();

                CheckBox mCheckBox = new CheckBox(this);
                mCheckBox.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));

                TextView mTitle = new TextView(this);
                mTitle.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
                mTitle.setGravity(Gravity.CENTER_VERTICAL);
                mTitle.setText(title);
                mTitle.setPadding(100,0,0,0);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)mTitle.getLayoutParams();
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                mTitle.setLayoutParams(layoutParams);

                RelativeLayout mNewItem = new RelativeLayout(this);
                mNewItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
                mNewItem.setPadding(20,20,20,20);

                ((RelativeLayout) mNewItem).addView(mCheckBox);
                ((RelativeLayout) mNewItem).addView(mTitle);

                ((LinearLayout) mListLayoutOuter).addView((RelativeLayout) mNewItem, i);
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
            updateTasks();
        }
    }
}
