package com.renu.quiz_assignment;

import android.app.Activity;
import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveResult {
    String filename = "result.txt";


    public void saveTask(Activity context, ArrayList<Questions> questions){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(filename, Context.MODE_APPEND);// open file and continue writing
            String storageString = "";
            // I have to write byte array only
            // I have to convert task to string then to byte array
            if(questions != null && questions.size() > 0){

                for(int i=0; i<=questions.size();i++){
                    storageString = storageString + "\n " + questions.get(i).toString();
                }
            }
            fileOutputStream.write((storageString).getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();// print all previous error
        }
        finally {
            // this will run if we have exception or not
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //file outputstream for writing to the file
    }

    public void resetAllTask(Activity context){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);// earas data
            fileOutputStream.write("".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();// print all previous error
        }
        finally {
            // this will run if we have exception or not
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //file outputstream for writing to the file
    }

}
