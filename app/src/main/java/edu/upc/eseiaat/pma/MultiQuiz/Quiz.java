package edu.upc.eseiaat.pma.MultiQuiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {

    /*creamos un atributo del quizactivity*/

    private int ids_answers[] = {
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4

    };
    private int correct_answer;
    private int current_question;
    private String[] all_questions;
    private TextView text_question;
    private RadioGroup group;
    private boolean [] answer_is_correct;
    private Button btn_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        text_question = (TextView) findViewById(R.id.text_question);
        group = (RadioGroup) findViewById(R.id.answer_group);
        btn_check = (Button) findViewById(R.id.btn_check);
        all_questions = getResources().getStringArray(R.array.all_questions);
        current_question = 0;
        answer_is_correct = new boolean[all_questions.length];

        showQuestion();

//TODO: cuando se clica el boton deberia pasar a la siguiente pregunta


        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = group.getCheckedRadioButtonId();
                int answer = -1;

                for(int i=0; i< ids_answers.length;i++) {
                    if (ids_answers[i] == id) {
                        answer = i;
                    }
                }
                /*
                if (answer == correct_answer) {
                    Toast.makeText(Quiz.this, R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Quiz.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                }
                */

                answer_is_correct[current_question]= (answer== correct_answer);
//TODO: hacer que function el método --> no funcionava perq no estava dins els corchetes corresponents
                if(current_question<all_questions.length - 1) {
                    current_question++;
                    showQuestion();
                }
                for (int i = 0; i<answer_is_correct.length; i++){
                    Log.i("Andrea",String.format("Respuesta %d:%b",i,answer_is_correct[i]));
                }

                /*Log.i ("pauek", String.format("Id:%d",id));*/
            }
        });

    }

    private void showQuestion() {

        String q = all_questions[current_question];
        String [] parts = q.split(";");

        group.clearCheck();
        text_question.setText(parts [0]);

        for (int i=0; i < ids_answers.length; i++) {
            RadioButton rb = (RadioButton) findViewById(ids_answers[i] );
            String answer = parts[i+1];
            if (answer.charAt(0) =='*') {
                correct_answer = i;
                answer = answer.substring(1);
            }
            rb.setText (answer);
            if(current_question== all_questions.length-1){
                btn_check.setText(R.string.finish);
            }
        }
    }
}
