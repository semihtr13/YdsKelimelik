package com.setsoft.ydskelimelik.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.setsoft.ydskelimelik.R;
import com.setsoft.ydskelimelik.model.Word;
import com.setsoft.ydskelimelik.model.dao.WordDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {
    private Random random;
    private Set<Integer> options;
    TextView tvQuestion;
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        tvQuestion = findViewById(R.id.tvQuestion);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        options = new HashSet<>();
        random = new Random();
        WordDAO wordDAO = new WordDAO(this);
        List<Word> allWords = wordDAO.allWordList();
        int question = random.nextInt(allWords.size());
        options.add(question);
       /* while (options.size()==4){
            int optionNumber=random.nextInt(allWords.size());
            options.add(optionNumber);
        }*/

        //Button isimleri
        List<Button> buttons = new ArrayList<>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);

        //Buton indexleri
        List<Integer> btn = new ArrayList<>();
        btn.add(0);
        btn.add(1);
        btn.add(2);
        btn.add(3);
//        doğru cevap index
        int answer = random.nextInt(4);
//        doğru indexi kaldırma
        btn.remove(answer);
        Iterator<Integer> i = options.iterator();
        boolean ans = true;
        int btnCount = 0;
        while (i.hasNext()) {
            if (ans) {
                Log.e("question", "0");
                int next = i.next();
//                tvQuestion.setText("question");
//                buttons.get(answer).setText("A");
                tvQuestion.setText(allWords.get(next).getTurkish());
                buttons.get(answer).setText(allWords.get(next).getEnglish());
                ans = false;
            } else {
                if (btnCount != btn.size()) {
                    buttons.get(btn.get(btnCount)).setText("" + btn.get(btnCount));
                    buttons.get(btnCount).setText(allWords.get(i.next()).getEnglish());
                    btnCount++;
                } else {
                    break;
                }
//
            }

        }

    }
}
