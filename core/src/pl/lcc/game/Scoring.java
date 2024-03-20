package pl.lcc.game;

import pl.lcc.setup.Konsts;

public class Scoring {

    private int score;
    Konsts.GameState state;
    public Scoring() {
        state = Konsts.GameState.GAME;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public Scoring setScore(int score) {
        this.score = score;
        checkWinCondition();
        return this;
    }

    public void hit(){
        score--;
        checkWinCondition();
    }
    public String getText(){
        return switch(state){
            case WIN -> "Congratulations! you Won";
            case LOST -> "You Lost :)";
            case GAME -> String.format("Not bad. %d to go!", score);
        };
    }

    public boolean isFinished(){
        return state!=Konsts.GameState.GAME;
    }

    public void gameLost(){
        state = Konsts.GameState.LOST;
    }

    private void checkWinCondition() {
        if(score==0){
            state = Konsts.GameState.WIN;
        }
    }
}
