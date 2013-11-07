package com.mobilesig.kindergartentodolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    /* FIELDS */
    ViewModel vm = null;

    ToDoItemsArrayAdapter arrayAdapter = null;

    List<WorkItem> ToDoItems = null;

    ListView listViewToDo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewToDo = (ListView) findViewById(R.id.listViewToDoItems);

        Button btnNew = (Button) findViewById(R.id.buttonNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    WorkItem newWorkItem = new WorkItem("blah", Calendar.getInstance().getTime(), WorkItem.PriorityEnum.HIGH, WorkItem.StatusEnum.PENDING);
                    vm.AddWorkItem(newWorkItem);
                    TransferFromDBToGUI();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        vm = new ViewModel(getApplicationContext());
        ToDoItems = vm.GetAllWorkItems();

        arrayAdapter = new ToDoItemsArrayAdapter(this, R.layout.row_todo, ToDoItems);
        listViewToDo.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (vm == null)
            vm = new ViewModel(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void TransferFromDBToGUI() {
        List<WorkItem> todoList = vm.GetAllWorkItems();
        ToDoItems.clear();
        if (todoList != null) {
            for (WorkItem wi : todoList) {
                ToDoItems.add(wi);
            }
            synchronized (arrayAdapter) {
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }

    private void TestMethod() {
        try {
            /* Test Add */
            WorkItem newWorkItem = new WorkItem("description1", Calendar.getInstance().getTime(), WorkItem.PriorityEnum.NORMAL, WorkItem.StatusEnum.PENDING);
            vm.AddWorkItem(newWorkItem);

            // Test GetAll
            List<WorkItem> allWorkItems = vm.GetAllWorkItems();
            int count = allWorkItems.size();

            // Test Edit
            WorkItem currentItem = allWorkItems.get(0);
            currentItem.Status = WorkItem.StatusEnum.DONE;
            vm.UpdateWorkItem(currentItem);

            allWorkItems = vm.GetAllWorkItems();
            currentItem = allWorkItems.get(0);

            // Test Delete
            vm.DeleteWorkItem(allWorkItems.get(0).Id);

            count = allWorkItems.size();
        } catch (Exception ex) {
        }
    }
}
