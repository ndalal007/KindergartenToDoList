package com.mobilesig.kindergartentodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tlee on 11/3/13.
 */
public class SQLLiteHelper extends SQLiteOpenHelper implements IViewModel{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "KindergartenToDo";

    // Contacts table name
    private static final String TABLE_TODOITEMS = "ToDoItems";

    // Contacts Table Columns names
    private static final String KEY_ID = "Id";

    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_DUEDATE = "DueDate";
    private static final String KEY_PRIORITY = "Priority";
    private static final String KEY_STATUS = "Status";

    /* Constructor */
    public SQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PROJECTS_TABLE = "CREATE TABLE " + TABLE_TODOITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DUEDATE + " INTEGER,"
                + KEY_PRIORITY + " TEXT,"
                + KEY_STATUS + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_PROJECTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older table if existed.  Don't support automatic upgrades to DB.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOITEMS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void AddWorkItem(WorkItem newWorkItem) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, newWorkItem.Description);
        // In SQLite the java long value is stored as a int.
        values.put(KEY_DUEDATE, newWorkItem.DueDate.getTime());
        values.put(KEY_PRIORITY, newWorkItem.Priority.toString());
        values.put(KEY_STATUS, newWorkItem.Status.toString());

        // Inserting Row
        db.insert(TABLE_TODOITEMS, null, values);
        db.close(); // Closing database connection
    }

    public int DeleteWorkItem(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowAffected = db.delete(TABLE_TODOITEMS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();

        return rowAffected;
    }

    public int UpdateWorkItem(WorkItem workItem)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, workItem.Description);
        // In SQLite the java long value is stored as a int.
        values.put(KEY_DUEDATE, workItem.DueDate.getTime());
        values.put(KEY_PRIORITY, workItem.Priority.toString());
        values.put(KEY_STATUS, workItem.Status.toString());

        // updating row
        int rowCount = db.update(TABLE_TODOITEMS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(workItem.Id)});
        db.close();

        return rowCount;
    }

    public List<WorkItem> GetAllWorkItems()
    {
        List<WorkItem> workItems = new ArrayList<WorkItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TODOITEMS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int _id = Integer.parseInt(cursor.getString(0));
            String description = cursor.getString(1);
            Date dueDate = new Date(Long.parseLong(cursor.getString(2)));
            String priorityStr = cursor.getString(3);
            String statusStr = cursor.getString(4);

            WorkItem newWorkItem = new WorkItem(_id,
                    description,
                    dueDate,
                    WorkItem.PriorityEnum.valueOf(priorityStr),
                    WorkItem.StatusEnum.valueOf(statusStr));
            workItems.add(newWorkItem);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        db.close();

        return workItems;
    }

    @Override
    public void ResetDatabase() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_TODOITEMS);
        db.close();
    }
}
