package com.mobilesig.kindergartentodolist;

import java.util.List;

/**
 * Created by tlee on 11/3/13.
 */
public interface IViewModel {

    List<WorkItem> getAllWorkItems();

    void addNewWorkItem(WorkItem newWorkItem) throws Exception;

    void updateWorkItem(WorkItem workItem) throws Exception;

    void deleteWorkItem(int id) throws Exception;
}
