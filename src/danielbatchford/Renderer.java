package danielbatchford;

import processing.core.PApplet;

public class Renderer extends PApplet implements Constants {

    Game game;

    public static void main(String[] args) {
        PApplet.main("danielbatchford.Renderer");
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        if (FULLSCREEN) {
            fullScreen();
        }

        noSmooth();
    }

    @Override
    public void setup() {
        game = new Game();
        stroke(STROKE_COL[0], STROKE_COL[1], STROKE_COL[2]);
        strokeWeight(STROKE_WEIGHT);
    }

    @Override
    public void draw() {

        background(BG_COL[0], BG_COL[1], BG_COL[2]);

        int[] boxSize = new int[]{WIDTH / BOARD_X, HEIGHT / BOARD_Y};
        for (Tile t : game.tiles) {

            int[] pos = t.getPos();

            int[] fillArr = COLOR_MAP.get(t.getValue());
            fill(fillArr[0], fillArr[1], fillArr[2]);

            rect(pos[0] * boxSize[0], pos[1] * boxSize[1], boxSize[0], boxSize[1]);
        }
    }

    @Override
    public void keyPressed() {

        if (key == 'r') {
            game = new Game();
            return;
        }

        //TODO Clean this all up
        if (key == CODED) {
            Direction direction;
            switch (keyCode) {
                case UP:
                    direction = Direction.UP;
                    break;
                case DOWN:
                    direction = Direction.DOWN;
                    break;
                case LEFT:
                    direction = Direction.LEFT;
                    break;
                case RIGHT:
                    direction = Direction.RIGHT;
                    break;
                default:
                    return;
            }
            game.step(direction);
        }
    }


}
