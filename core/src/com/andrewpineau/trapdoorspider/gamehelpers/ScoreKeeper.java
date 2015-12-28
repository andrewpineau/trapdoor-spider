package com.andrewpineau.trapdoorspider.gamehelpers;


import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreKeeper {
	Preferences prefs;

    public ScoreKeeper() {
        prefs = Gdx.app.getPreferences("TrapdoorSpiderHighScores");
    }

    public int[] readHighScores(){
        //if no scores stored, we create an array with scores of 0
            //return high scores
            return new int[] {
                    prefs.getInteger("1", 0),
                    prefs.getInteger("2", 0),
                    prefs.getInteger("3", 0),
                    prefs.getInteger("4", 0),
                    prefs.getInteger("5", 0),
            };
    }

    public void writeHighScores(int[] scores){
        prefs.putInteger("1", scores[0]);
        prefs.putInteger("2", scores[1]);
        prefs.putInteger("3", scores[2]);
        prefs.putInteger("4", scores[3]);
        prefs.putInteger("5", scores[4]);
        prefs.flush();
    }



    //sorts array of scores and new score in descending order
    private int[] sortScores(int newScore, int[] scores){

        int [] unorderedHighScores = new int [6];
        int [] orderedHighScores = new int [6];

        //add high scores and new score to unordered array
        for(int i = 0; i < scores.length; i ++){
            unorderedHighScores[i] = scores[i];
        }
        unorderedHighScores[5] = newScore;


        //sort array descending
        Arrays.sort(unorderedHighScores);
        for(int i = 0; i < unorderedHighScores.length; i++){
            orderedHighScores[i] = unorderedHighScores[unorderedHighScores.length - (i + 1)];
        }

        //put the first 5 ints in ordered score list in highScores list
        for(int i = 0; i < scores.length; i ++){
            scores[i] = orderedHighScores[i];
        }
        return scores;
    }

    //receives score from game world, checks if it is a new high score and stores it
    public boolean checkScore(int newScore){
        boolean newHighScore;
        int[] scores = readHighScores();

        if(newScore > scores[0]) {
            newHighScore = true;
        } else {
            newHighScore = false;
        }

        //only write to file if new score is within the top 5
        if(newScore > scores[4]){
        	scores = sortScores(newScore, scores);
            writeHighScores(scores);
        }

        return newHighScore;
    }

}
