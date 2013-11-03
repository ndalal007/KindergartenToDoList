package com.mobilesig.kindergartentodolist;

import java.util.List;

/**
 * Created by tlee on 11/3/13.
 */
public class ViewModel implements IViewModel{

    @Override
    public List<WorkItem> getAllWorkItems() {
        return null;
    }

    @Override
    public void addNewWorkItem(WorkItem newWorkItem) throws Exception {
        throw new Exception("Not implemented.");
    }

    @Override
    public void updateWorkItem(WorkItem workItem) throws Exception{
        throw new Exception("Not implemented.");
    }

    @Override
    public void deleteWorkItem(int id) throws Exception {
        throw new Exception("Not implemented.");
    }
}
