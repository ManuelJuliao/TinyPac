package pt.isec.pa.tinypac.model.fsm;

public interface IGameState {

    void start();

    void startGame();

    GameState getState();
}
