package danielbatchford;

import danielbatchford.ai.Player;
import processing.core.PApplet;

import java.util.Random;

public class Renderer extends PApplet implements Constants {

    Game game;
    Player player;

    public static void main(String[] args) {
        PApplet.main("danielbatchford.Renderer");
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        if (FULLSCREEN) {
            fullScreen();
        }

    }

    @Override
    public void setup() {

        frameRate(5);

        surface.setTitle("2048 AI");

        game = new Game();
        player = new Player();

        stroke(STROKE_COL[0], STROKE_COL[1], STROKE_COL[2]);
        strokeWeight(STROKE_WEIGHT);

        //TODO adaptive text resizing
        textFont(createFont("Arial", 25), 25);
        textAlign(CENTER, CENTER);
    }

    @Override
    public void draw() {

        background(BG_COL[0], BG_COL[1], BG_COL[2]);

        int[] boxSize = new int[]{WIDTH / BOARD_X, HEIGHT / BOARD_Y};

        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {
                Tile t = game.tiles[x][y];
                if (t == null) continue;

                int[] fillArr = COLOR_MAP.get(t.getValue());

                // If colour out of defined color mappings
                if (fillArr == null) {
                    fillArr = new int[]{147, 200, 193};
                }

                fill(fillArr[0], fillArr[1], fillArr[2]);
                rect(x * boxSize[0], y * boxSize[1], boxSize[0], boxSize[1]);


                fill(255, 255, 255);
                float[] textCenter = new float[]{x * boxSize[0] + (float) (boxSize[0]) / 2, y * boxSize[1] + (float) (boxSize[1]) / 2};
                text(Integer.toString(t.getValue()), textCenter[0], textCenter[1]);
            }
        }
        player.nextMove(game);
    }

    @Override
    public void keyPressed() {

        if (key == 'r') {
            game = new Game();
            player = new Player();
            return;
        }

        if (key == CODED) {
            int[] dir;
            switch (keyCode) {
                case UP:
                    dir = new int[]{0, -1};
                    break;
                case DOWN:
                    dir = new int[]{0, 1};
                    break;
                case LEFT:
                    dir = new int[]{-1, 0};
                    break;
                case RIGHT:
                    dir = new int[]{1, 0};
                    break;
                default:
                    return;
            }

            game.step(dir);
        }
    }


}
