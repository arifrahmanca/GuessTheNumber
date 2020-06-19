package e.arif.guessthenumber;

public class GuessNumber {
    private int guessCounter;
    private int maxGuess;
    private int upper;
    private int lower;
    private int answer;
    private static int score = 0;
    private static int gameCount = 0;
    public boolean showAnswer;
    public boolean gameOver;

    public GuessNumber(){
        double rand = Math.random();
        this.answer = (int) (rand*5000);
        setLimit();
        int diff = this.upper - this.lower;
        this.maxGuess = (int) ((Math.log(diff)) / (Math.log(2))) + 2;
        guessCounter = maxGuess;
        gameCount++;
        showAnswer = false;
        gameOver = false;
    }
    public int getAnswer(){
        return this.answer;
    }
    public int getLower(){
        return this.lower;
    }
    public int getUpper(){ 
        return this.upper; 
    }
    public int getMaxGuesses(){
        return this.maxGuess;
    }
    public void incrementScore(){
        score++;
    }
    public void decrementGuessCounter(){
        guessCounter--;
    }
    public int getGuessCounter(){ 
        return this.guessCounter; 
    }
    public void isGameOver(){
        gameOver = true;
    }
    public void isShownAnswer(){
        showAnswer = true;
    }
    public String getInitialMsg(){
        String s = "Guess a number between " + lower + " and " + upper;
        return s;
    }
    public String getScoreMsg(){
        String s = "Your Score: " + score + " / " + gameCount;
        return s;
    }
    public  String getAnswerMsg(){
        String s = "The correct number is " + answer + ".\n" +
                "Try RESET Button to PLAY again!";
        return s;
    }
    public String getGuessLimitMsg(){
        String s = "You have " + guessCounter + " guesses left";
        return s;
    }
    public void setLimit(){
        int num = answer;
        if (num > 0 && num <250){
            this.lower = 0;
            this.upper = 250;
        }
        else if (num >= 250 && num <500){
            this.lower = 250;
            this.upper = 500;
        }
        else if (num >= 500 && num <750){
            this.lower = 500;
            this.upper = 750;
        }
        else if (num >= 750 && num <1000){
            this.lower = 750;
            this.upper = 1000;
        }
        else if (num >= 1000 && num <1500){
            this.lower = 1000;
            this.upper = 1500;
        }
        else if (num >= 1500 && num <2000){
            this.lower = 1500;
            this.upper = 2000;
        }
        else if (num >= 2000 && num <3000){
            this.lower = 2000;
            this.upper = 3000;
        }
        else if (num >= 3000 && num <4000){
            this.lower = 3000;
            this.upper = 4000;
        }
        else {
            this.lower = 4000;
            this.upper = 5000;
        }
    }
}
