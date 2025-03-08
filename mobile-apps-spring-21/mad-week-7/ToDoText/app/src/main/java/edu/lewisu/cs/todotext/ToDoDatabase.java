package edu.lewisu.cs.todotext;

import android.content.Context;
import android.security.FileIntegrityManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ToDoDatabase {

    private static int sNextId = 1; // will make a id
    private static ToDoDatabase sToDoDatabase;
    public static final String FILENAME = "todolist.txt";
    // going to be working with a text file
    private List<ToDo> mToDoList;


    public static ToDoDatabase getInstance(Context context) {
        if (sToDoDatabase == null) {
            sToDoDatabase = new ToDoDatabase(context);
        }
        return sToDoDatabase;
    }


    private ToDoDatabase(Context context) {
        mToDoList = new ArrayList<>();
    }

    public List<ToDo> getToDos() {
        return mToDoList;
    }

    public ToDo getToDo(int toDoId) {
        for (ToDo toDo : mToDoList) {
            if (toDo.getId() == toDoId) {
                return toDo;
            }
        }
        return null;
    }

    public void addToDo(ToDo toDo){
        toDo.setId(sNextId);
        sNextId++;
        mToDoList.add(toDo);
    }

    public void deleteToDo(int id){
        // this is a object the can loop can is better to handle when deleting something
        Iterator<ToDo> itr = mToDoList.iterator();

        while(itr.hasNext()){
            ToDo toDo = itr.next();
            if (toDo.getId() == id){
                itr.remove();
            }
        }//end of loop
    }

    public void saveToFile(Context context) throws IOException {
        FileOutputStream outputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        PrintWriter writer =  new PrintWriter(outputStream);
        writer.println(sNextId);
        //wrinte to a file  each object  getting a line
        for (ToDo toDo : mToDoList){
            writer.println(toDo.toString());
        }
        writer.close();
    }

    public void readFromFile(Context context) throws IOException{
        BufferedReader reader = null;

        try{
            FileInputStream inputStream = context.openFileInput(FILENAME);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            mToDoList.clear();

            String line;
            ToDo toDo;

            if((line = reader.readLine()) != null ){
                sNextId = Integer.parseInt(line);
            }
            //processing by line
            while ((line = reader.readLine()) != null){
                //putting date into a object <_ToDo>
                toDo = new ToDo(); // making a empty object
                String[] tokens = line.split(","); // delimiter ??

                toDo.setId(Integer.parseInt(tokens[0]));
                toDo.setTitle(tokens[1]);
                toDo.setPriority(Integer.parseInt(tokens[2]));
                toDo.setComplete(Boolean.parseBoolean(tokens[3]));
                mToDoList.add(toDo);
            }//end of loop

        }catch (FileNotFoundException ex){
            //ignore
        }finally {
            if(reader != null){
                reader.close();
            }
        }// end of the try...catch...finally block
    }



}//end of class
