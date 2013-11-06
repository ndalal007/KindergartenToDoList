package com.mobilesig.kindergartentodolist;

import android.app.Activity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    /* FIELDS */
    ViewModel vm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new ViewModel(getApplicationContext());

        TestMethod();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(vm == null)
            vm = new ViewModel(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void TestMethod()
    {
        try
        {
            /* Test Add */
            WorkItem newWorkItem = new WorkItem("description1", Calendar.getInstance().getTime(), WorkItem.PriorityEnum.NORMAL, WorkItem.StatusEnum.PENDING);
            vm.AddWorkItem(newWorkItem);

            // Test GetAll
            List<WorkItem> allWorkItems = vm.GetAllWorkItems ();
            int count = allWorkItems.size();

            // Test Edit

            // Test Delete
            vm.DeleteWorkItem(allWorkItems.get(0).Id);

            count = allWorkItems.size();
        }
        catch (Exception ex)
        {}
    }
}
