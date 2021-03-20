package com.example.todoapplnroomdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.todoapplnroomdatabase.Adapter.TasksAdapter;
import com.example.todoapplnroomdatabase.R;
import com.example.todoapplnroomdatabase.db.DatabaseClient;
import com.example.todoapplnroomdatabase.db.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyeclerview_task);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(intent);
            }
        });

        getTasks();

    }

    private void getTasks()
    {
        class GetTasks extends AsyncTask<Void, Void, List<Task>>
        {

            @Override
            protected List doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();

                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(MainActivity.this,tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();

        }
    }


