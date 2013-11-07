package com.mobilesig.kindergartentodolist;

import android.content.Context;

import java.util.List;

/**
 * Created by tlee on 11/3/13.
 */
public class ViewModel implements IViewModel{

    /* FIELDS */
    private Model model = null;

    public ViewModel(Context ctx)
    {
        model = new Model(ctx);
    }

    @Override
    public List<WorkItem> GetAllWorkItems() {
        return model.GetAllWorkItems();
    }

    @Override
    public void AddWorkItem(WorkItem newWorkItem) throws Exception {
        model.AddWorkItem(newWorkItem);
    }

    @Override
    public int UpdateWorkItem(WorkItem workItem) {
        return model.UpdateWorkItem(workItem);
    }

    @Override
    public int DeleteWorkItem(int id) {
        return model.DeleteWorkItem(id);
    }

    @Override
    public void ResetDatabase() {
        model.ResetDatabase();
    }
}
