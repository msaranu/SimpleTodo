 package com.codepath.simpletodo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import static android.R.attr.name;
import static com.codepath.simpletodo.R.id.lvItems;

 /**
 * Created by Saranu on 2/13/17.
 */

public class EditTaskDialogFragment extends DialogFragment {


     private EditText mEditName;
     private EditText mEditDate;
     private EditText mEditDetail;
     private EditText mEditStatus;
     private Spinner mSpinnerPriority;
     private Spinner mSpinnerStatus;
     private  DatePicker simpleDatePicker;
     int HIGH = 0;
     int MEDIUM=2;



     public EditTaskDialogFragment() {
         // Empty constructor is required for DialogFragment
         // Make sure not to add arguments to the constructor
         // Use `newInstance` instead as shown below
     }

     public static EditTaskDialogFragment newInstance(int pos, Task to) {
         EditTaskDialogFragment frag = new EditTaskDialogFragment();
         Bundle args = new Bundle();
         args.putSerializable("TaskObj", to);
         args.putString("title", "Edit Task Item");
         args.putInt("position", pos);
         frag.setArguments(args);
         return frag;
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         return inflater.inflate(R.layout.activity_edit_item, container);
     }

     @Override
     public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);

         Task tobj = (Task) getArguments().getSerializable("TaskObj");

         mEditName = (EditText) view.findViewById(R.id.editTaskName);
         mEditName.setText(tobj.getTaskName());

         simpleDatePicker = (DatePicker) view.findViewById(R.id.editTaskDate);
         String[] dateSplit = tobj.getTaskDate().split("/");
         simpleDatePicker.updateDate(Integer.parseInt(dateSplit[2]),Integer.parseInt(dateSplit[0])-1,Integer.parseInt(dateSplit[1]));

         mEditDetail = (EditText) view.findViewById(R.id.editTaskDescription);
         mEditDetail.setText(tobj.getTaskDetail());

          mSpinnerPriority = (Spinner) view.findViewById(R.id.editTaskPriorityArray);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                 this.getActivity(), R.array.editTaskPriorityArray, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         mSpinnerPriority.setAdapter(adapter);
         String[] array_priority = getResources().getStringArray(R.array.editTaskPriorityArray);
         mSpinnerPriority.setSelection(Arrays.asList(array_priority).indexOf(tobj.getTaskPriority()));

         //mSpinnerPriority.setOnItemSelectedListener(new MySpinnerSelectedListener());

         mSpinnerStatus = (Spinner) view.findViewById(R.id.editTaskStatusArray);
         ArrayAdapter<CharSequence> adapterS = ArrayAdapter.createFromResource(
                 this.getActivity(), R.array.editTaskStatusArray, android.R.layout.simple_spinner_item);
         adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         mSpinnerStatus.setAdapter(adapterS);
         String[] array_status = getResources().getStringArray(R.array.editTaskStatusArray);

         mSpinnerStatus.setSelection(Arrays.asList(array_status).indexOf(tobj.getTaskStatus()));



         // Fetch arguments from bundle and set title
         String title = getArguments().getString("title", "Enter Name");
         getDialog().setTitle(title);
         // Show soft keyboard automatically and request focus to field
         mEditName.requestFocus();
         getDialog().getWindow().setSoftInputMode(
                 WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

         Button button = (Button) view.findViewById(R.id.submit);
         button.setOnClickListener(new AdapterView.OnClickListener() {
             @Override
             public void onClick(View v) {
                 EditNameDialogListener listener = (EditNameDialogListener) getActivity();
                 Task tobj = (Task) getArguments().getSerializable("TaskObj");
                 if( mEditName.getText().toString().trim().equals("")) {
                     mEditName.setError("Task Name is Required.");
                 }else {
                     tobj.setTaskName(mEditName.getText().toString());
                     int day = simpleDatePicker.getDayOfMonth();
                     int month = simpleDatePicker.getMonth() + 1;
                     int year = simpleDatePicker.getYear();
                     String dateString = month + "/" + day + "/" + year;
                     tobj.setTaskDate(dateString);
                     tobj.setTaskDetail(mEditDetail.getText().toString());
                     tobj.setTaskPriority(mSpinnerPriority.getSelectedItem().toString());
                     tobj.setTaskStatus(mSpinnerStatus.getSelectedItem().toString());
                     listener.onFinishEditDialog(tobj, getArguments().getInt("position"));
                     // Close the dialog and return back to the parent activity
                     dismiss();
                 }
             }
         });
     }

     // 1. Defines the listener interface with a method passing back data result.
     public interface EditNameDialogListener {
         void onFinishEditDialog(Task to, int pos);
     }

      class MySpinnerSelectedListener implements OnItemSelectedListener {

         public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
              //selected = parent.getItemAtPosition(pos).toString();
         }

         public void onNothingSelected(AdapterView parent) {
             // Do nothing.
         }
     }
 }



