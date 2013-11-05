package com.mobilesig.kindergartentodolist;

import android.content.Context;

import java.util.List;

/**
 * Created by tlee on 11/3/13.
 */
public class Model implements IModel{

    /* FIELDS */
    private SQLLiteHelper dBHelper = null;

    public Model(Context ctx)
    {
        dBHelper = new SQLLiteHelper(ctx);
    }

    @Override
    public List<WorkItem> GetAllWorkItems() {
        return dBHelper.GetAllWorkItems();
    }

    @Override
    public void AddWorkItem(WorkItem newWorkItem) throws Exception {
        dBHelper.AddWorkItem(newWorkItem);
    }

    @Override
    public int UpdateWorkItem(WorkItem workItem) {
        dBHelper.UpdateWorkItem(workItem);
        return 0;
    }

    @Override
    public int DeleteWorkItem(int id) {
        return dBHelper.DeleteWorkItem(id);
    }
}
