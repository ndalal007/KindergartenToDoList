package com.mobilesig.kindergartentodolist;

/**
 * Created by tlee on 11/7/13.
 */
public interface IToDoItemsArrayAdapterListener {

    public void OnRequestDelete(WorkItem workItem);

    public void OnRequestEdit(WorkItem workItem);

    public void OnRequestAccept(WorkItem workItem);
}
