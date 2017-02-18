package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.R.id.edit;
import static com.raizlabs.android.dbflow.sql.language.property.PropertyFactory.from;

public class MainActivity extends AppCompatActivity implements AddTaskDialogFragment.AddTaskDialogListener {
    List<Task> arrayOfTasks;
    TaskAdapter tAdapter;
    ListView lvItems;
    int REQUEST_CODE=400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct the data source
         arrayOfTasks = new ArrayList<Task>();

        arrayOfTasks = SQLite.select().
                from(Task.class).queryList();

        tAdapter = new TaskAdapter(this, arrayOfTasks);

        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(tAdapter);

        tAdapter.notifyDataSetChanged();

         setupListViewListener();
    }

    private void setupListViewListener(){

        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                Task tDel =  arrayOfTasks.get(pos);
                tAdapter.remove(tDel);
                tDel.delete();

            //    writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                Intent i = new Intent(MainActivity.this, ViewItemActivity.class);
                i.putExtra("taskObject", arrayOfTasks.get(pos));
                i.putExtra("taskPosition", pos);
                 startActivityForResult(i, REQUEST_CODE);

            }
        });
    }


    @Override
         protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
                int pos = data.getExtras().getInt("taskPosition");
                Task to = (Task) data.getExtras().getSerializable("editedTaskObj");

                arrayOfTasks.set(pos,to);
                arrayOfTasks.get(pos).update();
                /*SQLite.update(Task.class)
                        .set(Task_Table.taskName.eq(to.getTaskName()),Task_Table.taskDate.eq(to.getTaskDate()),Task_Table.taskDetail.eq(to.getTaskDetail()),
                                Task_Table.taskPriority.eq(to.getTaskPriority()),Task_Table.taskStatus.eq(to.getTaskStatus()))
                        .where(Task_Table.id.is(to.getId()))
                        .async()
                        .execute(); // non-UI blocking */

                tAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onFinishAddDialog(Task tOb) {

         //   int pos =p;
      //      Task to = tOb;
          tOb.setId(arrayOfTasks.size());
           arrayOfTasks.add(tOb);
            tOb.save();
            tAdapter.notifyDataSetChanged();

    }


    public void onAddTask(View v) {

        FragmentManager fm = getSupportFragmentManager();

        AddTaskDialogFragment addTaskDialogFragment =
                AddTaskDialogFragment.newInstance();

                        //.newInstance(arrayOfTasks.size());
        addTaskDialogFragment.show(fm, "activity_add_item");

    }


    /*File persist changed to DBFlow
        public void readItems() {
        File filesDir = getFilesDir();
        Log.d("File is ******", filesDir.getPath());
        File todoFile = new File(filesDir, "todo.txt");
        try{
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e){
            items = new ArrayList<String>();
        }
    }

    public void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile,items);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

     */

}
