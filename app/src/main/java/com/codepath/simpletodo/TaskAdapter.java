package com.codepath.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.codepath.simpletodo.Task;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.bottom;
import static android.R.attr.left;
import static android.R.attr.right;
import static android.R.attr.top;
import static android.webkit.WebSettings.RenderPriority.HIGH;

/**
 * Created by Saranu on 2/6/17.
 */

public class TaskAdapter extends ArrayAdapter {


        public TaskAdapter(Context context, List<Task> task) {
            super(context, 0, task);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Task taskObject = (Task) getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_layout, parent, false);
            }
            // Lookup view for data population
            TextView taskN = (TextView) convertView.findViewById(R.id.taskName);
            TextView taskP = (TextView) convertView.findViewById(R.id.taskPriority);
            // Populate the data into the template view using the data object
            if(taskObject.getTaskPriority().equalsIgnoreCase("HIGH")){
                taskP.setTextColor(Color.RED);
            }
            else if(taskObject.getTaskPriority().equalsIgnoreCase("MEDIUM")){
                taskP.setTextColor(Color.BLUE);
            }else  taskP.setTextColor(Color.GREEN);

         //   LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
          //  lp.setMargins(400,0,0,0);
          //  taskP.setLayoutParams(lp);

            taskN.setMaxLines(3);

            taskN.setText(taskObject.getTaskName());
            taskP.setText(taskObject.getTaskPriority());

            // Return the completed view to render on screen
            return convertView;
        }
    }
