package com.mobilesig.kindergartentodolist;

import java.util.List;

/**
 * Created by tlee on 11/3/13.
 */
public interface IViewModel {

    List<WorkItem> GetAllWorkItems();

    void AddWorkItem(WorkItem newWorkItem) throws Exception;

    int UpdateWorkItem(WorkItem workItem);

    int DeleteWorkItem(int id);

    void ResetDatabase();
}
