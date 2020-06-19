package e.arif.guessthenumber;

public class Message {

    public String getWinningMsg(){
        String s = "You guessed the number correctly.\n" +
                "Hit RESET Button to PLAY again!";
        return s;
    }
    public String getAlreadyWonMsg(){
        String s = "You have already won this game.\n" +
                "Hit RESET Button to PLAY again!";
        return s;
    }
    public String getLowGuessMsg(){
        String s = "Your guess is too low! \n" +
                "Try a bigger number.";
        return s;
    }
    public String getHighGuessMsg(){
        String s = "Your guess is too high! \n" +
                "Try a smaller number.";
        return s;
    }
    public String getErrorNumMsg(){
        String s = "Error: You did not enter any number!";
        return s;
    }
    public String getErrorRangeMsg(){
        String s = "Error: The number is out of range!";
        return s;
    }
    public String getShowAnswerMsg(){
        String s = "The answer is already shown to you. \n" +
                "Hit RESET Button to PLAY again!";
        return s;
    }
    public String getMaxAttemptMsg(){
        String s = "You have used maximum attempts!\n" +
                "Hit RESET Button to PLAY again!";
        return s;
    }
    public String getGameOverMsg(){
        String s = "The game is over!\n" +
                "Hit RESET Button to PLAY again!";
        return s;
    }
    public String getLastGuessMsg(){
        String s = "You have the last guess left!";
        return s;
    }
    public String getAllGuessesMsg(){
        String s = "You have used all the guesses!";
        return s;
    }
    public String getEmptyMsg(){
        String s = "";
        return s;
    }
}
