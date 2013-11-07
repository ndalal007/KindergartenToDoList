package com.mobilesig.kindergartentodolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tlee on 11/6/13.
 */
public class ToDoItemsArrayAdapter extends ArrayAdapter<WorkItem> {

    Context context;
    int layoutResourceId;
    List<WorkItem> data = null;
    WorkItem SelectedItem = null;

    public ToDoItemsArrayAdapter(Context context, int layoutResourceId, List<WorkItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        try
        {
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.txtDescription = (TextView)row.findViewById(R.id.txtView_rowDescription);
            holder.txtDueDate = (TextView)row.findViewById(R.id.txtView_rowDueDate);
            holder.txtPriority = (TextView)row.findViewById(R.id.txtView_rowPriority);
            holder.txtStatus = (TextView)row.findViewById(R.id.txtView_rowStatus);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        final WorkItem workItem = data.get(position);
        holder.txtDescription.setText(workItem.Id);
        holder.txtDueDate.setText(workItem.DueDate.toString());
        holder.txtPriority.setText(workItem.Priority.toString());
        holder.txtStatus.setText(workItem.Status.toString());

        }
        catch (Exception ex)
        {

        }
        return row;
    }

    static class ViewHolder
    {
        TextView txtDescription;
        TextView txtDueDate;
        TextView txtPriority;
        TextView txtStatus;
    }
}
