package com.mobilesig.kindergartentodolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity implements IToDoItemsArrayAdapterListener{

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

        Button btnNukeIt = (Button) findViewById(R.id.buttonNukeDB);
        btnNukeIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    vm.ResetDatabase();
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
        if(arrayAdapter != null)
        {
            arrayAdapter.addListener(this);
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        if(arrayAdapter != null)
        {
            arrayAdapter.removeListener(this);
        }

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

    @Override
    public void OnRequestDelete(WorkItem workItem) {
        try
        {
            vm.DeleteWorkItem(workItem.Id);
            TransferFromDBToGUI();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnRequestEdit(WorkItem workItem) {
        try
        {
            vm.UpdateWorkItem(workItem);
            TransferFromDBToGUI();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnRequestAccept(WorkItem workItem) {
        try
        {
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
