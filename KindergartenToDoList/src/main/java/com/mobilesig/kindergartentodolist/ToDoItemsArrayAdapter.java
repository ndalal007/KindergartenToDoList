package com.mobilesig.kindergartentodolist;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
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

    //--------- BEGIN EVENTS-----------------//
    private List<IToDoItemsArrayAdapterListener> _listeners = new ArrayList<IToDoItemsArrayAdapterListener>();

    public synchronized void addListener(IToDoItemsArrayAdapterListener obj) {
        _listeners.add(obj);
    }

    public synchronized void removeListener(IToDoItemsArrayAdapterListener obj) {
        _listeners.remove(obj);
    }

    public synchronized void fireOnDeleteItem(WorkItem workItem) {
        Iterator<IToDoItemsArrayAdapterListener> listeners = _listeners.iterator();
        while (listeners.hasNext()) {
            try {
                ((IToDoItemsArrayAdapterListener) listeners.next()).OnRequestDelete(workItem);
            } catch (Exception ex) {
            }
        }
    }

    //--------- END EVENTS-----------------//

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        try {
            if (row == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new ViewHolder();
                holder.txtDescription = (TextView) row.findViewById(R.id.txtView_rowDescription);
                holder.txtDueDate = (TextView) row.findViewById(R.id.txtView_rowDueDate);
                holder.imgViewPriority = (ImageView) row.findViewById(R.id.imageView_rowPriority);
                holder.imgViewStatus = (ImageView) row.findViewById(R.id.imageView_rowStatus);
                holder.btnDelete = (Button)row.findViewById(R.id.button_rowDelete);
                holder.btnAccept = (Button)row.findViewById(R.id.button_rowAccept);
                holder.btnEdit = (Button)row.findViewById(R.id.button_rowEdit);

                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            // Update the data to the GUI.
            final WorkItem workItem = data.get(position);
            holder.txtDescription.setText(workItem.Description);
            // format the datetime to something easier to read.
            holder.txtDueDate.setText(android.text.format.DateFormat.format("MM-dd", workItem.DueDate));
            holder.imgViewPriority.setImageDrawable(ConvertPriorityToImage(workItem.Priority));
            holder.imgViewStatus.setImageDrawable(ConvertStatusToImage(workItem.Status));

            // Subscribe to the button events.
            row.setClickable(true);
            row.setFocusable(true);

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fireOnDeleteItem(workItem);
                }
            });

            holder.imgViewPriority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(workItem.Priority)
                    {
                        case LOW:
                            workItem.Priority = WorkItem.PriorityEnum.NORMAL;
                            break;
                        case NORMAL:
                            workItem.Priority = WorkItem.PriorityEnum.HIGH;
                            break;
                        case HIGH:
                            workItem.Priority = WorkItem.PriorityEnum.LOW;
                            break;
                    }

                    ((ImageView)view).setImageDrawable(ConvertPriorityToImage(workItem.Priority));
                }
            });

        } catch (Exception ex) {
            Log.v("ToDoItemsArrayAdapter", ex.getMessage());
        }
        return row;
    }

    private Drawable ConvertPriorityToImage(WorkItem.PriorityEnum priority) {
        Drawable drawable = null;
        switch (priority) {
            case LOW:
               drawable = context.getResources().getDrawable(R.drawable.low);
                break;
            case NORMAL:
                drawable = context.getResources().getDrawable(R.drawable.normal);
                break;
            case HIGH:
                drawable = context.getResources().getDrawable(R.drawable.high);
                break;
        }

        return drawable;
    }

    private Drawable ConvertStatusToImage(WorkItem.StatusEnum status) {
        Drawable drawable = null;
        switch (status) {
            case PENDING:
                drawable = context.getResources().getDrawable(R.drawable.notdone);
                break;
            case DONE:
                drawable = context.getResources().getDrawable(R.drawable.done);
                break;
        }

        return drawable;
    }

    static class ViewHolder {
        TextView txtDescription;
        TextView txtDueDate;
        ImageView imgViewPriority;
        ImageView imgViewStatus;

        Button btnAccept;
        Button btnEdit;
        Button btnDelete;
    }
}
