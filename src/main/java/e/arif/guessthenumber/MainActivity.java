package e.arif.guessthenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    GuessNumber g;
    Message msg = new Message();
    int counter = 0;
    int winClick = 0;
    boolean win = false;
    Set<Integer> inputHistory = new HashSet<>();

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
        int limit = g.getMaxGuesses();
//        counter++;
//        g.decrementGuessCounter();
        int loopCount = 0;
        int num = -1;
        if (inputNum.isEmpty()){
            setContentsOfTextView(R.id.resultMsg, msg.getErrorNumMsg());
        }
        else{
            num = Integer.parseInt(inputNum);
        }

        if (num < 0){
            setContentsOfTextView(R.id.resultMsg, msg.getErrorNumMsg());
        }
        else if (num < g.getLower() || num > g.getUpper()) {
            setContentsOfTextView(R.id.resultMsg, msg.getErrorRangeMsg());
        }
        else if (inputHistory.contains(num)){
            setContentsOfTextView(R.id.resultMsg, msg.getInputHistoryMsg());
        }
        else {
            counter++;
            g.decrementGuessCounter();
            inputHistory.add(num);
            while (loopCount <= limit && !g.gameOver) {
                if (num == g.getAnswer()){
                    winClick++;
                    g.incrementScore();
                    g.isGameOver();
                    setContentsOfTextView(R.id.resultMsg, msg.getWinningMsg());
                    setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
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
            setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
            if (winClick > 1){
                setContentsOfTextView(R.id.resultMsg, msg.getAlreadyWonMsg());
            }
            else {
                setContentsOfTextView(R.id.resultMsg, msg.getShowAnswerMsg());
            }
        }
        else if (winClick > 1){
            setContentsOfTextView(R.id.resultMsg, msg.getAlreadyWonMsg());
            setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
        }
        else if (counter > limit && winClick == 0){
            setContentsOfTextView(R.id.resultMsg, msg.getMaxAttemptMsg());
            setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
        }
        else {
            if (g.getGuessCounter() == 1 && !g.gameOver && winClick == 0) {
                setContentsOfTextView(R.id.limitMsg, msg.getLastGuessMsg());
            }
            else if (g.getGuessCounter() == 0 && !g.gameOver && winClick == 0) {
                setContentsOfTextView(R.id.limitMsg, msg.getAllGuessesMsg());
                setContentsOfTextView(R.id.resultMsg, msg.getMaxAttemptMsg());
            }
            else if (g.getGuessCounter() < 0) {
                setContentsOfTextView(R.id.resultMsg, msg.getGameOverMsg());
                setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
            }
            else {
                if (!g.gameOver) {
                    setContentsOfTextView(R.id.limitMsg, g.getGuessLimitMsg());
                }
            }
        }
        setContentsOfTextView(R.id.scoreMsg, g.getScoreMsg());
    }

    public void showAnswerButtonClicked(View view) {
        g.isShownAnswer();
        g.isGameOver();
        inputHistory.clear();
        if (winClick == 1){
            winClick++;
        }
        setContentsOfTextView(R.id.showAnswer, g.getAnswerMsg());
        setContentsOfTextView(R.id.scoreMsg, g.getScoreMsg());
        setContentsOfTextView(R.id.limitMsg, msg.getEmptyMsg());
        setContentsOfTextView(R.id.resultMsg, msg.getGameOverMsg());
    }

    public void resetButtonClicked(View view) {
        g = new GuessNumber();
        counter = 0;
        winClick = 0;
        inputHistory.clear();
        setContentsOfTextView(R.id.iniMsg, g.getInitialMsg());
        setContentsOfTextView(R.id.resultMsg, msg.getEmptyMsg());
        setContentsOfTextView(R.id.showAnswer, msg.getEmptyMsg());
        setContentsOfTextView(R.id.inputNum, msg.getEmptyMsg());
        setContentsOfTextView(R.id.limitMsg, g.getGuessLimitMsg());
        setContentsOfTextView(R.id.scoreMsg, g.getScoreMsg());
    }
}
