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

    public Date CreationTime;

    public PriorityEnum Priority;

    public WorkItem(String description, Date creationtime, PriorityEnum priority)
    {
        Description = description;
        CreationTime = creationtime;
        Priority = priority;
    }
}
