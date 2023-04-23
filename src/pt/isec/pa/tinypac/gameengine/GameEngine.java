package pt.isec.pa.tinypac.gameengine;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;

import java.util.HashSet;
import java.util.Set;
public final class GameEngine implements IGameEngine {
    private GameEngineState state;
    private GameEngineThread controlThread;
    private Set<IGameEngineEvolve> clients;
    private Maze maze;
    private boolean fruitMaze[][];
    System.Logger logger;

    public GameEngine() {
        logger = System.getLogger("GameEngine");
        clients = new HashSet<>();
        maze = new Maze(20,20);
        this.fruitMaze = new boolean[maze.getMaze().length][maze.getMaze()[0].length];
        setState(GameEngineState.READY);
    }

    private void setState(GameEngineState state) {
        this.state = state;
        logger.log(System.Logger.Level.INFO,state.toString());
    }

    @Override
    public void registerClient(IGameEngineEvolve listener) {
        clients.add(listener);
    }
    @Override
    public void unregisterClient(IGameEngineEvolve listener) {
        clients.remove(listener);
    }

    @Override
    public boolean start(long interval) {
        if (state != GameEngineState.READY)
            return false;
        controlThread = new GameEngineThread(interval);
        setState(GameEngineState.RUNNING);
        controlThread.start();
        return false;
    }
    @Override
    public boolean stop() {
        if (state == GameEngineState.READY)
            return false;
        setState(GameEngineState.READY);
        return true;
    }
    @Override
    public boolean pause() {
        if (state != GameEngineState.RUNNING)
            return false;
        setState(GameEngineState.PAUSED);
        return true;
    }
    @Override
    public boolean resume() {
        if (state != GameEngineState.PAUSED)
            return false;
        setState(GameEngineState.RUNNING);
        return false;
    }
    @Override
    public GameEngineState getCurrentState() {
        return state;
    }
    @Override
    public long getInterval() {
        return controlThread.interval;
    }
    @Override
    public void setInterval(long newInterval) {
        if (controlThread != null)
            controlThread.interval = newInterval;
    }

    @Override
    public void waitForTheEnd() {
        try {
            controlThread.join();
        } catch (InterruptedException e) {}
    }

    //Maze commands
    public void setMazeElement(int y, int x,IMazeElement element) {
        maze.set(y, x, element);
    }

    public IMazeElement getMazeElement(int y, int x) {
        return maze.get(y, x);
    }

    public char[][] getMaze() {
        return maze.getMaze();
    }

    //Fruit maze commands
    public void eatFruit(int y, int x) {
        fruitMaze[y][x] = false;
    }

    public boolean getFruit(int y, int x) {
        return fruitMaze[y][x];
    }

    public boolean[][] getFruitMaze() {
        return fruitMaze;
    }

    //Game engine thread
    private class GameEngineThread extends Thread {
        long interval;
        GameEngineThread(long interval) {
            this.interval = interval;
            this.setDaemon(true);
        }
        @Override
        public void run() {
            int errCounter = 0;
            while (true) {
                if (state == GameEngineState.READY)
                    break;
                if (state == GameEngineState.RUNNING) {
                    new Thread(() -> {
                        long time = System.nanoTime();
                        clients.forEach(
                                client -> client.evolve(GameEngine.this, time) );
                    }).start();
                }
                try {
                    //noinspection BusyWait
                    sleep(interval);
                    errCounter = 0;
                } catch (InterruptedException e) {
                    if (state == GameEngineState.READY || errCounter++ > 10)
                        break;
                }
            }
        }
    }
}
