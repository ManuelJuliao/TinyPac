package pt.isec.pa.tinypac.model.data;

import java.io.Serializable;

/**
 * This class represents the PlayerScore
 */
public class PlayerScore implements Serializable {
    private String name;
    private int score;
    /**
     * Constructs a new PlayerScore object with the given name and score.
     * @param name  the name of the player
     * @param score the score of the player
     */
    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * Returns player name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * returns player score
     * @return score
     */
    public int getScore() {
        return score;
    }
}