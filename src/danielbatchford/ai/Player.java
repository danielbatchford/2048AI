package danielbatchford.ai;

import danielbatchford.Constants;
import danielbatchford.Direction;
import danielbatchford.Game;
import danielbatchford.Tile;
import processing.core.PApplet;

import javax.swing.tree.TreeModel;
import java.util.*;

public class Player implements Constants {

    int[][] gridHeuristic;

    public Player(){

        gridHeuristic = new int[BOARD_X][BOARD_Y];


        gridHeuristic = new int[BOARD_Y][BOARD_X];
        for (int y = 0; y < BOARD_Y; y++) {
            int[] subarray = new int[BOARD_X];
            for (int x = 0; x < BOARD_X; x++) {
                subarray[y % 2 == 0 ? x : BOARD_X - x - 1] = x + BOARD_X * y;
            }
            gridHeuristic[BOARD_Y - y - 1] = subarray;
        }
    }

    public void nextMove(Game gameState){

        Direction[] directions = Direction.values();
        Game[] states = new Game[4];
        float[] costs = new float[4];
        for(int i = 0; i < 4; i++){
            states[i] = new Game(gameState);
            states[i].step(directions[i]);
            costs[i] = getCost(states[i]);

        }

        int minIndex = 0;
        for(int i = 1; i < 4; i++){
            if (costs[i] < costs[minIndex]){
                    minIndex = i;
            }
        }

        gameState.step(directions[minIndex]);
    }


    private float getCost(Game game){
        float sum = 0;
        Tile[][] tiles = game.tiles;
        for(int x = 0; x < BOARD_X; x++){
            for(int y =0; y < BOARD_Y; y++) {

                Tile tile = tiles[x][y];
                if (tile != null) {
                    sum += tiles[x][y].getValue() * gridHeuristic[x][y];
                }
            }
        }
        return sum;
    }
}
