package com.example.todoapplnroomdatabase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplnroomdatabase.R;
import com.example.todoapplnroomdatabase.db.Task;
import com.example.todoapplnroomdatabase.ui.UpdateTaskActivity;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
    private Context mCtx;
    private List<Task> taskList;

    public TasksAdapter(Context mCtx, List<Task> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks,parent,false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
       Task t = taskList.get(position);
       holder.textViewTask.setText(t.getTask());
       holder.textViewDesc.setText(t.getDesc());
       holder.textViewFinishBy.setText(t.getFinishBy());

       if (t.isFinished())
           holder.textViewStatus.setText("Completed");
       else
           holder.textViewStatus.setText("Not Completed");

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
           Task task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("task",task);

            mCtx.startActivity(intent);
        }
    }
}
