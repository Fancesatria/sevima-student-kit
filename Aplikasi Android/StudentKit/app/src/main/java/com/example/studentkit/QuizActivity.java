package com.example.studentkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentkit.Quiz.QuestionAndAnswer;
import com.example.studentkit.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0; //score
    int totalQuestion = QuestionAndAnswer.question.length;
    int currentQuestionIndex = 0; //current index of question for answer
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if(clickedButton.getId() == R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAndAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }

            currentQuestionIndex++;
            loadNewQuestion();
        }  else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.DKGRAY);
            clickedButton.setTextColor(Color.WHITE);
        }
    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAndAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAndAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAndAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAndAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAndAnswer.choices[currentQuestionIndex][3]);

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your score is "+ score +" out of "+totalQuestion)
                .setPositiveButton("Restart", (DialogInterface, i)->restartQuiz())
                .setCancelable(false)
                .show();
    }
}