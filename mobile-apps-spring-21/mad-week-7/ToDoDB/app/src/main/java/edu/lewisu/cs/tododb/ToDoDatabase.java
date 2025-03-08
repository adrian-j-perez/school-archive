package edu.lewisu.cs.tododb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
public  abstract class ToDoDatabase extends RoomDatabase {
    public abstract ToDoDao toDoDao();

    private static ToDoDatabase sToDoDatabase;

    static ToDoDatabase getsToDoDatabase(final Context context) {
        // have to check twice to get the lock
        if (sToDoDatabase == null) {
            synchronized (ToDo.class) {
                //making the database
                if (sToDoDatabase == null) {
                    // the process of building
                    sToDoDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoDatabase.class, "todo_database")
                            .allowMainThreadQueries() // good practice is to give it its own thread
                            .build();
                }
            }// end of sync
        }
        return sToDoDatabase;
    }


}
