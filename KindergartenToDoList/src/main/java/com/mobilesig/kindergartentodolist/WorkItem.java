package com.mobilesig.kindergartentodolist;

import java.util.Date;

/**
 * Created by tlee on 11/3/13.
 */
public class WorkItem {

    public enum StatusEnum { PENDING, DONE,}

    public enum PriorityEnum { LOW, NORMAL, HIGH}

    public int Id;

    public String Description;

    public StatusEnum Status;

    public Date DueDate;

    public PriorityEnum Priority;

    /* Constructor when creating a new workitem. */
    public WorkItem(String description, Date duedate, PriorityEnum priority, StatusEnum status)
    {
        Description = description;
        DueDate = duedate;
        Priority = priority;
        Status = status;
    }

    /* Constructor when creating from an existing workitem. */
    public WorkItem(int id, String description, Date duedate, PriorityEnum priority, StatusEnum status)
    {
        Description = description;
        DueDate = duedate;
        Priority = priority;
        Status = status;
        Id = id;
    }

}
