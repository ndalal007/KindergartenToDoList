package com.mobilesig.kindergartentodolist;

import java.util.List;

/**
 * Created by tlee on 11/3/13.
 */
public interface IViewModel {

    List<WorkItem> getAllWorkItems();

    void addNewWorkItem(WorkItem newWorkItem);

    void updateWorkItem(WorkItem workItem);

    void deleteWorkItem(int id);
}
