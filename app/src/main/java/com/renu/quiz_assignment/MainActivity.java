package com.renu.quiz_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    QuestionsBank questionsBank = new QuestionsBank();
    Button true_btu, false_btu;
    private int currentQuestionsIndex = 0;
    ArrayList<Questions> answerList = new ArrayList<>();
    Context context;
    int correctAnswer = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionsBank.createArrayList();

        true_btu = (Button) findViewById(R.id.true_btu);
        false_btu = (Button) findViewById(R.id.false_btu);

      updateFragment();

        //checkAnswer(true);
        // currentQuestionsIndex ++;


    }

    private void updateFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.framelayout);
        QuestionsFragment fragmentDemo = QuestionsFragment.newInstance(questionsBank.questionsList.get(currentQuestionsIndex).questionsid, questionsBank.questionsList.get(currentQuestionsIndex).colorid);
        manager.beginTransaction().add(R.id.framelayout, fragmentDemo, "tag").commit();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.get_the_average:
                Toast.makeText(this, "Clicked Menu 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_the_numberofquestions:
                Toast.makeText(this, "Clicked Menu 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset_the_saved_result:
                Toast.makeText(this, "Clicked Menu 3", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.true_btu:
                Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                updateIndex();
                updateFragment();
                break;


            case R.id.false_btu:
                Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                updateIndex();
                updateFragment();
                break;
        }

    }

    private void showDialoag() {

        Log.d("Current", "Onclick" + currentQuestionsIndex);


        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle("Result");
        adb.setMessage("Your score is " + correctAnswer + " out of " + questionsBank.questionsList.size());

        adb.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                correctAnswer = 0;
                answerList = new ArrayList<>();

            } });


        adb.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                correctAnswer = 0;
                answerList = new ArrayList<>();


            } });
        adb.show();


        }
        public void updateIndex(){
            if(currentQuestionsIndex == (questionsBank.questionsList.size()-1)){
                showDialoag();
                currentQuestionsIndex=0;

            }
            else{
                currentQuestionsIndex++;
            }
        }

        private void checkAnswer ( boolean userChoosenCorrect){
        Questions questions = questionsBank.questionsList.get(currentQuestionsIndex);
            boolean answeIsTrue = questions.answers;

            int toastMessageId = 0;
            if (userChoosenCorrect == answeIsTrue) {
                toastMessageId = R.string.correct_answer;
                questions.setQuestions(this.getResources().getString(questions.questionsid));
                correctAnswer++;
                answerList.add(questions);
            } else {
                toastMessageId = R.string.wrong_answer;
            }
            Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
        }
    }
