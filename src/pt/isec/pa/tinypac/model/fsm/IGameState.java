package pt.isec.pa.tinypac.model.fsm;

public interface IGameState {



    void startGame();

    GameState getState();

    void powerUp();

    void powerDown();

    void win();

    void lose();

    void pause();
}
