package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.Characters.*;
import pt.isec.pa.tinypac.model.data.Elements.*;
import pt.isec.pa.tinypac.utils.Utils;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This class represents the Board
 */
public class Board {
    public record Position(int y, int x) {};
    public TinyPac tinyPac;
    private int hp, points, fruitPoints, ballCount,lvl, ghostCount, ghostPoints, ballCountTotal, mapBalls;
    private int powerCounter = 0;
    Position fruitPos;
    int height, width;
    Maze maze;
    List<PlayerScore> playerList;
    public boolean POWER_UP = false;
    /**
     * Constructs a Board, initializes some variables as well as the board itself and loads top scores
     */
    public Board() {
        innit();
        this.points = 0;
        this.fruitPoints = 25;
        this.ghostPoints = 50;
        this.lvl = 1;
        this.hp = 1;
        this.playerList = new ArrayList<>();
        loadScores(playerList);
    }

    /**
     * Method responsible for initializing the game
     */
    public void innit(){
        checkSize();
        this.ballCount = 0;
        this.ballCountTotal = 0;
        mapBalls = 0;
        this.maze = new Maze(height,width);
        fillBoard(height, width);
        fillCharacters();
    }

    /**
     * Method responsible for checking board size
     */
    private void checkSize(){
        File file = new File("Level01.txt");
        int rows=0, columns=0;
        try {
            FileReader fr=new FileReader(file);   //Creation of File Reader object
            BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
            int c;
            while((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
                if(character == '\r')
                    continue;
                if (character == '\n') {
                    width = columns;
                    columns = 0;
                    rows++;
                    continue;
                }
                columns++;
            }
            height = rows + 1;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method responsible for filling the board with elements
     * @param y0 board height
     * @param x0 board width
     */
    private void fillBoard(int y0, int x0) {
        File file = new File("Level01.txt");
        int k=0;
        //String text = null;
        char[] text = new char[y0*x0];
        Element aux;
        try {
            FileReader fr=new FileReader(file);   //Creation of File Reader object
            BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
            int c = 0;
            int j = 0;
            while((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
                if(character == '\r')
                    continue;
                if (character == '\n')
                    continue;
                text[j] = character;
                j++;
            }
            //System.out.println(text);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int y1 = 0; y1 < height; y1++){
            for (int x1 = 0; x1 < width; x1++){
                aux = selectElement(text[k]);
                if(aux instanceof Ball || aux instanceof PowerBall)
                    mapBalls++;
                if(aux instanceof Fruit){
                    fruitPos = new Position(y1,x1);
                    addElement(null,y1,x1);
                }
                else{
                    addElement(aux,y1,x1);
                }
                k++;
            }
        }
    }

    /**
     * Method responsible for choosing the correct element given the char present in the text file
     * @param c text character
     * @return new instance of desired element
     */
    Element selectElement(char c){
        return switch(c){
            case 'x' -> new Wall();
            case 'W' -> new Warp();
            case 'o' -> new Ball();
            case 'F' -> new Fruit();
            //case 'F' -> null;
            case 'Y' -> new PhantomPortal();
            case 'y' -> new PhantomCave();
            case 'M' -> new PacSpawn();
            case 'O' -> new PowerBall();
            default -> null;
        };
    }
    /**
     * Method responsible for adding the characters (Ghosts and TinyPac) to the board
     */
    private void fillCharacters() {
        int countCave = 0, countRand = 0, countPhantom = 0;
        IMazeElement spawn = null;
        char[][] aux = getBoard();
        tinyPac = new TinyPac(this);
        for (int y0 = 0; y0 < aux.length; y0++){
            for (int x0 = 0; x0 < aux[y0].length; x0++){
                if(aux[y0][x0] == 'M')
                    addPac(tinyPac, y0,x0 );
                if (aux[y0][x0] == 'Y'){
                    spawn = get(y0,x0);
                }
                else if(aux[y0][x0]== 'y'){
                    countCave++;
                    if(21 - countCave >= 4 - countPhantom){
                        if(countPhantom == 4)
                            break;
                        if(Utils.randBool()){
                            addCharacter(selectPhantom(countPhantom, spawn), y0,x0);
                            countPhantom++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Method responsible for selecting a ghost given a counter and the spawn position
     * @param count number of ghosts already in the board
     * @param spawn position of the ghost portal
     * @return new instance of the desired ghost
     */
    private Generic selectPhantom(int count, IMazeElement spawn ){
        return switch (count){
            case 0 -> new Blinky(this, spawn);
            case 1 -> new Clyde( this, spawn);
            case 2 -> new Inky(this,spawn);
            case 3 -> new Pinky(this, spawn);
            default -> throw new IllegalStateException("Unexpected value: " + count);
        };
    }
    /**
     * Method responsible for loading top scores from txt file
     * @param playerList variable to save the scores
     */
    public void loadScores(List<PlayerScore> playerList) {
        File file = new File("top_5.txt");
        if (file.length() == 0) {
            // The file is empty, no scores saved yet
            return;
        }
        try (FileInputStream fileIn = new FileInputStream("top_5.txt");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            // Read the serialized object from the file
            List<PlayerScore> savedScores = (List<PlayerScore>) objectIn.readObject();
            // Update the existing playerList with the retrieved scores
            playerList.clear();
            playerList.addAll(savedScores);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds points
     * @param count amount of points to add
     */
    public void addPoints (int count){
        points += count;
    }

    /**
     * Returns points
     * @return points
     */
    public int getPoints(){
        return points;
    }

    /**
     * Method responsible for checking if the current score is higher than the last one saved
     * @return true or false
     */
    public boolean checkTop5() {
        if(playerList.size()==5)
            return points > playerList.get(playerList.size() - 1).getScore();
        else return true;
    }

    /**
     * Method responsible for saving the score
     * @param playerName name of the player
     */
    public void saveScore(String playerName) {
        int playerScore = getPoints();
        PlayerScore newPlayer = new PlayerScore(playerName, playerScore);
        // Add the new score to the topScores list
        playerList.add(newPlayer);
        // Sort the topScores list in descending order based on score
        playerList.sort(Collections.reverseOrder());
        // Keep only the top 5 scores
        if (playerList.size() > 5) {
            playerList = playerList.subList(0, 5);
        }
        try (FileOutputStream fileOut = new FileOutputStream("top_5.txt");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            // Serialize the playerList and write it to the file
            objectOut.writeObject(playerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for adding points when the ball gets eaten and
     * increasing counter for balls (to spawn fruit) and total balls (win condition)
     */
    public void countBall (){
        points += 1;
        ballCount ++;
        ballCountTotal++;
    }

    /**
     * Method responsible for adding points when fruit gets eaten and increase fruit point reward
     */
    public void addFruitPoints() {
        points = points + fruitPoints;
        fruitPoints = fruitPoints + 25;
    }
    /**
     * Counts PowerBall for win condition
     */
    public void countPowerBall(){
        ballCountTotal++;
    }

    /**
     * Method responsible for adding points for eating a ghost, increase ghost point reward
     * and reset those values when all the ghosts are gone
     */
    public void eatGhost(){
        ghostCount++;
        points += ghostPoints;
        ghostPoints += 50;
        if(ghostCount == 4){
            ghostCount = 0;
            ghostPoints = 25;
        }
    }
    /**
     * Returns board height
     * @return height
     */
    public int getHeight (){return height;}
    /**
     * Returns board width
     * @return width
     */
    public int getWidth (){return width;}
    /**
     * Adds element to the board given the specified element and coordinates
     * @param aux is the element
     * @param y is the y coordinate
     * @param x is the x coordinate
     */
    public void add(IMazeElement aux, int y, int x){maze.set(y,x,aux);}

    /**
     * Adds ghost to the board given the specified ghost and coordinates
     * @param character is the ghost
     * @param y is the y coordinate
     * @param x is the x coordinate
     */
    public void addCharacter(Generic character, int y, int x) {
        maze.set(y,x,character);
    }

    /**
     * Adds TinyPac to the board given the specified coordinates
     * @param pac is the TinyPac
     * @param y is the y coordinate
     * @param x is the x coordinate
     */
    public void addPac(TinyPac pac, int y, int x) {
        maze.set(y,x,pac);
    }
    /**
     * Adds element to the board given the specified element and coordinates
     * @param element is the element
     * @param y is the y coordinate
     * @param x is the x coordinate
     */
    public void addElement(Element element, int y, int x){maze.set(y,x,element);}
    /**
     * Removes hp when TinyPac dies and resets board
     */
    public void die(){
        hp--;
        innit();
    }
    /**
     * Returns an IMazeElement given the coordinates
     * @param y is the y coordinate
     * @param x is the x coordinate
     * @return Object depending on type
     */
    public IMazeElement get(int y,int x) {
       IMazeElement element = maze.get(y,x);
        if (element instanceof Generic character)
            return character;
        if(element instanceof TinyPac pac)
            return pac;
        return (Element) element;
    }

    /**
     * Returns an instance of Generic given the coordinates
     * @param y is the y coordinate
     * @param x is the x coordinate
     * @return Generic
     */
    public Generic getCharacter(int y,int x) {
        IMazeElement element = maze.get(y,x);
        if (element instanceof Generic character)
            return character;
        return null;
    }
    /**
     * Returns an instance of Element given the coordinates
     * @param y is the y coordinate
     * @param x is the x coordinate
     * @return Element
     */
    public Element getElement(int y,int x) {
        IMazeElement aux = maze.get(y,x);
        if (aux instanceof Element element)
            return element;
        return null;
    }

    /**
     * Returns ghost position given the ghost
     * @param character is the ghost
     * @return ghost position or null if it doesn't exist
     */
    public Position getPositionOf(Generic character) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == character)
                    return new Position(y,x);
        return null;
    }
    /**
     * Returns TinyPac position
     * @param tiny is the TinyPac
     * @return TinyPac position or null if it doesn't exist
     */
    public Position getPacPos(TinyPac tiny) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == tiny)
                    return new Position(y,x);
        return null;
    }
    /**
     * Returns IMazeElement position
     * @param element is the TinyPac
     * @return element position or null if it doesn't exist
     */
    public Position getPosition(IMazeElement element) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == element)
                    return new Position(y,x);
        return null;
    }
    /**
     * Returns Warp position
     * @param elementType is the Warp class
     * @return warp position or null if it doesn't exist
     */
    public Position getPositionOfWarp(Class<Warp> elementType) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                IMazeElement mazeElement = maze.get(y, x);
                if (mazeElement != null && elementType.isInstance(mazeElement)) {
                    return new Position(y, x);
                }
            }
        }
        return null;
    }
    /**
     * Returns a list of available cells
     * @param yo is the cuurent y coordinate
     * @param xo is the current x coordinate
     * @return list of available cells
     */
    public List<Position> getAdjacentEmptyCells(int yo, int xo) {
        List<Position> lst = new ArrayList<>();
        if(!(maze.get(yo,xo-1) instanceof Wall) && !(maze.get(yo,xo-1) instanceof Generic) && !(maze.get(yo,xo-1) instanceof PhantomCave) && !(maze.get(yo,xo-1) instanceof PhantomPortal))
            lst.add(new Position(yo,xo-1));
        if(!(maze.get(yo,xo+1) instanceof Wall) && !(maze.get(yo,xo+1) instanceof Generic) && !(maze.get(yo,xo+1) instanceof PhantomCave) && !(maze.get(yo,xo+1) instanceof PhantomPortal))
            lst.add(new Position(yo,xo+1));
        if(!(maze.get(yo-1,xo) instanceof Wall) && !(maze.get(yo-1,xo) instanceof Generic) && !(maze.get(yo-1,xo) instanceof PhantomCave) && !(maze.get(yo-1,xo) instanceof PhantomPortal))
            lst.add(new Position(yo-1,xo));
        if(!(maze.get(yo+1,xo) instanceof Wall) && !(maze.get(yo+1,xo) instanceof Generic) && !(maze.get(yo+1,xo) instanceof PhantomCave) && !(maze.get(yo+1,xo) instanceof PhantomPortal))
            lst.add(new Position(yo+1,xo));
        return lst;
    }

    /**
     * Method responsible for the evolution of the board and it's elements
     * @return true or false
     */
    public boolean evolve(){
        boolean aux =true;
        if(ballCount == 20){
            ballCount = 0;
            addFruit();
        }
        List<Generic> lst = new ArrayList<>();
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Generic character) {
                    lst.add(character);
                }
        for(var character : lst){
            character.evolve();
            try {
                Thread.sleep(120); // Adjust the delay duration as needed (in milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!tinyPac.evolve())
            die();
        return aux;
    }
    /**
     * Adds fruit to the board
     */
    private void addFruit() {
        addElement(new Fruit(), fruitPos.y(), fruitPos.x());
    }

    /**
     * Method responsible for the evolution of the board when POWER UP is active
     * @return true or false
     */
    public boolean evolveInvert() {
        boolean aux = true;
        if(powerCounter++ == 20) // game engine interval is .5 sec -> 10 sec powerup = 20 iterations
            POWER_UP = false;
        List<Generic> lst = new ArrayList<>();
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Generic character) {
                    lst.add(character);
                }
        if(!tinyPac.evolveInvert())
            die();
        for(var character : lst){
            character.evolveInvert();
            try {
                Thread.sleep(120); // Adjust the delay duration as needed (in milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return aux;
    }
    /**
     * Returns the board
     * @return the board
     */
    public char[][] getBoard() {
        return maze.getMaze();
    }

    /**
     * Returns hp
     * @return hp
     */
    public int getHP(){return hp;}
    /**
     * Returns total number of balls in the board in the beggining of the game
     * @return total number of balls
     */
    public int getMapBalls(){return mapBalls;}
    /**
     * Returns total of balls eaten by TinyPac (including PowerBalls)
     * @return total balls eaten
     */
    public int getBallCountTotal(){return ballCountTotal;}
    /**
     * Returns list of players in the top 5
     * @return list of players
     */
    public List<PlayerScore> getPlayerList(){
        return playerList;
    }

}
