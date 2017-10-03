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
    private int[] answer;
    private Button btn_next, btn_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        text_question = (TextView) findViewById(R.id.text_question);
        group = (RadioGroup) findViewById(R.id.answer_group);
        btn_next = (Button) findViewById(R.id.btn_check);
        btn_prev = (Button) findViewById(R.id.btn_prev);
        all_questions = getResources().getStringArray(R.array.all_questions);
        answer_is_correct = new boolean[all_questions.length];
        answer = new int[all_questions.length];

        for (int i = 0; i < answer.length;i++){
            answer[i] =-1;
        }

        current_question = 0;
        showQuestion();

//TODO: cuando se clica el boton deberia pasar a la siguiente pregunta


             btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /* CON ESTE TROZO, CREAMOS UN METODO PARA QUE COMPRUEBE LA RESPUESTA Y LA GUARDE EN EL OTRO BOTON
                    int id = group.getCheckedRadioButtonId();
                    int ans = -1;
                    for (int i = 0; i < ids_answers.length; i++) {
                        if (ids_answers[i] == id) {
                            ans = i;
                        }
                    }
                    answer_is_correct[current_question] = (ans == correct_answer);
                    answer[current_question]= ans;*/
                    /*
                if (answer == correct_answer) {
                    Toast.makeText(Quiz.this, R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Quiz.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                }
                */
                    checkAnswer();

//TODO: hacer que function el método --> no funcionava perq no estava dins els corchetes corresponents
                    if (current_question < all_questions.length - 1) {
                        current_question++;
                        showQuestion();
                    }
                    else {
                        int correctas = 0, incorrectas = 0;
                        for (boolean b : answer_is_correct) {
                            if (b) correctas++;
                            else incorrectas++;
                        }
                        String resultado =
                                String.format("Correctas: %d -- Incorrectas: %d", correctas, incorrectas);
                        Toast.makeText(Quiz.this, resultado, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                     /*for (int i = 0; i<answer_is_correct.length; i++){
                    Log.i("Andrea",String.format("Respuesta %d:%b",i,answer_is_correct[i]));
                }

                /*Log.i ("pauek", String.format("Id:%d",id));*/
            });
            btn_prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer();
                    if (current_question>0){
                        current_question - -;
                        showQuestion();
                    }
                }

            });


    }

    private void checkAnswer() {
        int id = group.getCheckedRadioButtonId();
        int ans = -1;
        for (int i = 0; i < ids_answers.length; i++) {
            if (ids_answers[i] == id) {
                ans = i;
            }
        }
        answer_is_correct[current_question] = (ans == correct_answer);
        answer[current_question]= ans;
    }

    private void showQuestion() {

        String q = all_questions[current_question];
        String [] parts = q.split(";");

        group.clearCheck();
        text_question.setText(parts [0]);

        for (int i=0; i < ids_answers.length; i++) {
            RadioButton rb = (RadioButton) findViewById(ids_answers[i] );
            String ans = parts[i+1];
            if (ans.charAt(0) =='*') {
                correct_answer = i;
                ans = ans.substring(1);
            }
            rb.setText (ans);
            if (answer[current_question] == i){
                rb.setChecked(true);
            }

            if (current_question==0){
                btn_prev.setVisibility(View.GONE);
            }else{
                btn_prev.setVisibility(View.VISIBLE);
            }
            if(current_question== all_questions.length-1){
                btn_next.setText(R.string.finish);
            }else{
                btn_next.setText(R.string.next);
            }
        }
    }
}
