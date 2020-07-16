package danielbatchford;

import danielbatchford.ai.AIPlayer;
import processing.core.PApplet;

public class Renderer extends PApplet implements Constants {

    Game game;
    AIPlayer aiPlayer;

    boolean paused;

    public static void main(String[] args) {
        PApplet.main("danielbatchford.Renderer");
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {

        frameRate(60);

        surface.setTitle("2048 AI");

        game = new Game();
        aiPlayer = new AIPlayer();
        paused = false;

        stroke(STROKE_COL[0], STROKE_COL[1], STROKE_COL[2]);
        strokeWeight(STROKE_WEIGHT);

        textFont(createFont("Arial", TEXT_SCALE * WIDTH / BOARD_X), TEXT_SCALE * WIDTH / BOARD_X);
        textAlign(CENTER, CENTER);
    }

    // Called once every frame by Processing
    @Override
    public void draw() {

        background(BG_COL[0], BG_COL[1], BG_COL[2]);

        // The pixel dimensions of each tile on screen
        int[] boxSize = new int[]{WIDTH / BOARD_X, HEIGHT / BOARD_Y};

        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {
                Tile t = game.tiles[x][y];

                // Skip rendering of blank tiles
                if (t == null) continue;

                // Retrieve the correct color from the mapping based on the tile value
                int[] fillArr = COLOR_MAP.get(t.getValue());

                // If colour out of defined color mappings (to allow for "infinite" games)
                if (fillArr == null) {
                    fillArr = new int[]{147, 200, 193};
                }

                // Set the fill to this color and render a tile based on x1, y1, width, height. (In pixels)
                fill(fillArr[0], fillArr[1], fillArr[2]);
                rect(x * boxSize[0], y * boxSize[1], boxSize[0], boxSize[1]);


                // Render text in the center of a tile
                fill(255, 255, 255);
                float[] textCenter = new float[]{x * boxSize[0] + (float) (boxSize[0]) / 2, y * boxSize[1] + (float) (boxSize[1]) / 2};
                text(Integer.toString(t.getValue()), textCenter[0], textCenter[1]);
            }
        }

        // Move a step in the game based on the returned ai AIPlayer move
        game.step(aiPlayer.nextMove(game));
        game.addNewTile();
    }

    @Override
    public void keyPressed(){
        switch(key){
            case 'r':
                game = new Game();
                break;
            case 'p':
                if (paused){
                    loop();
                }
                else{
                    noLoop();
                }
                paused = !paused;
                break;
            default:

                // If user pushes a number key, change the speed of the simulation (framerate  = 2^selected number)
                try{
                    int mapNo = Integer.parseInt(String.valueOf(key));
                    frameRate((float) Math.pow(2,mapNo));
                }

                // To ignore non number keys
                catch(NumberFormatException ignored){
                }

        }
    }
}
