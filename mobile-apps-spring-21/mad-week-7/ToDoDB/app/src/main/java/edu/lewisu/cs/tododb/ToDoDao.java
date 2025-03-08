package edu.lewisu.cs.tododb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
//Data Access Object (DAO)
public interface ToDoDao {
    //note the room key word are not case sensitive

    @Insert
    void insert(ToDo... todo); // the '...' it can take one or more 'todo_'

    @Update
    void updateToDos (ToDo... toDos);

    @Delete
    void deleteToDos(ToDo... toDos);

    //sql query
    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("SELECT * from todo_table WHERE id = :id")
    ToDo getToDo(int id);

    @Query("SELECT * FROM todo_table ORDER BY task_title ASC")
    List<ToDo> getAllToDos ();

}
