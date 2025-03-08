package edu.lewisu.cs.tododb;

import android.app.Application;

import java.util.List;

//the repo is the intermediary of the data and the view

public class ToDoRepo {
    private ToDoDao mToDoDao;// this come from the interface class

    public ToDoRepo(Application application){
        ToDoDatabase db = ToDoDatabase.getsToDoDatabase(application);
        mToDoDao = db.toDoDao();
    }

    List<ToDo> getAllToDos(){
        return mToDoDao.getAllToDos();
    }

    ToDo getToDo(int id){
        return  mToDoDao.getToDo(id);
    }

    void  deleteToDo(ToDo toDo){
        mToDoDao.deleteToDos(toDo);
    }


    void insert (ToDo toDo ){
        mToDoDao.insert(toDo);
    }

    void update(ToDo toDo){
        mToDoDao.updateToDos(toDo);
    }

    //this is just to populate the database with something
    public void initialize(){
        mToDoDao.deleteAll(); // making sure database is clear

        //putting data in the database
        ToDo toDo = new ToDo("Finish nap", 2, false);
        mToDoDao.insert(toDo);

        toDo = new ToDo("eat something", 1 , true);
        mToDoDao.insert(toDo);

        //can add more like this
    }


}//end of class
