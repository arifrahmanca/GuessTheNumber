package e.arif.guessthenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GuessNumber g;
    Message msg = new Message();
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g = new GuessNumber();
        setContentsOfTextView(R.id.iniMsg, g.getInitialMsg());
        setContentsOfTextView(R.id.scoreMsg, g.getScoreMsg());
        setContentsOfTextView(R.id.limitMsg, g.getGuessLimitMsg());
    }

    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    public void playButtonClicked(View view) {
        String inputNum = getInputOfTextField(R.id.inputNum);
        counter++;
        int limit = g.getMaxGuesses();
        g.decrementGuessCounter();
        int loopCount = 0;
        int num = -1;
        if (inputNum.isEmpty()){
            setContentsOfTextView(R.id.resultMsg, msg.getErrorNum());
        }
        else{
            num = Integer.parseInt(inputNum);
        }

        if (num < 0){
            setContentsOfTextView(R.id.resultMsg, msg.getErrorNum());
        }
        else if (num < g.getLower() || num > g.getUpper()) {
            setContentsOfTextView(R.id.resultMsg, msg.getErrorRange());
        }
        else {
            while (loopCount <= limit) {
                if (num == g.getAnswer()){
                    setContentsOfTextView(R.id.resultMsg, msg.getWinningMsg());
                    if (!g.gameOver) {
                        g.incrementScore();
                        g.isGameOver();
                    }
                }
                else if (num < g.getAnswer()){
                    setContentsOfTextView(R.id.resultMsg, msg.getLowGuessMsg());
                }
                else {
                    setContentsOfTextView(R.id.resultMsg, msg.getHighGuessMsg());
                }
                loopCount++;
            }
        }

        if (g.showAnswer){
            setContentsOfTextView(R.id.resultMsg, msg.getShowAnswerMsg());
            setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
            g.isGameOver();
        }

        else if (counter > limit) {
            if (!g.gameOver) {
                setContentsOfTextView(R.id.resultMsg, msg.getMaxAttemptMsg());
                g.isGameOver();
            }
            else {
                setContentsOfTextView(R.id.resultMsg, msg.getGameOverMsg());
                setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
            }
        }
        else {
            if (g.getGuessCounter() == 1) {
                setContentsOfTextView(R.id.limitMsg, msg.getLastGuessMsg());
            }
            else if (g.getGuessCounter() <= 0) {
                setContentsOfTextView(R.id.limitMsg, msg.getAllGuessesMsg());
                setContentsOfTextView(R.id.resultMsg, msg.getGameOverMsg());
            }
            else {
                if (!g.showAnswer) {
                    setContentsOfTextView(R.id.limitMsg, g.getGuessLimitMsg());
                }
            }
        }

        setContentsOfTextView(R.id.scoreMsg, g.getScoreMsg());
    }

    public void showAnswwerButtonClicked(View view) {
        g.isShownAnswer();
        g.isGameOver();
        setContentsOfTextView(R.id.showAnswer, g.getAnswerMsg());
        setContentsOfTextView(R.id.resultMsg, msg.getEmptyMsg());
        setContentsOfTextView(R.id.scoreMsg, g.getScoreMsg());
        setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
    }

    public void resetButtonClicked(View view) {
        g = new GuessNumber();
        counter = 0;
        setContentsOfTextView(R.id.iniMsg, g.getInitialMsg());
        setContentsOfTextView(R.id.resultMsg, msg.getEmptyMsg());
        setContentsOfTextView(R.id.showAnswer, msg.getEmptyMsg());
        setContentsOfTextView(R.id.inputNum, msg.getEmptyMsg());
        setContentsOfTextView(R.id.limitMsg, g.getGuessLimitMsg());
        setContentsOfTextView(R.id.scoreMsg, g.getScoreMsg());
    }
}
