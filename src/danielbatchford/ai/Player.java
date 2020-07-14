package danielbatchford.ai;

import danielbatchford.Constants;
import danielbatchford.Direction;
import danielbatchford.Game;
import processing.core.PApplet;

import javax.swing.tree.TreeModel;
import java.util.Arrays;
import java.util.Collections;

public class Player implements Constants {

    Game game;
    int searchDepth;
    int[][] gridHeuristic;

    public Player(Game game, int searchDepth){
        this.game = game;
        this.searchDepth = searchDepth;

        gridHeuristic = new int[BOARD_X][BOARD_Y];


        int[][] gridHeuristic = new int[BOARD_Y][BOARD_X];
        for (int y = 0; y < BOARD_Y; y++) {
            int[] subarray = new int[BOARD_X];
            for (int x = 0; x < BOARD_X; x++) {
                subarray[y % 2 == 0 ? x : BOARD_X - x - 1] = x + BOARD_X * y;
            }
            gridHeuristic[BOARD_Y - y - 1] = subarray;
        }
        for(int i = 0 ; i < gridHeuristic.length; i++){
            System.out.println(Arrays.toString(gridHeuristic[i]));
        }
    }

    public void nextMove(){

        // Make the final move
        game.step(Direction.UP);
    }
}
