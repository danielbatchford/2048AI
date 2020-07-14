package danielbatchford;

import java.util.HashMap;

public interface Constants {

    int WIDTH = 1500;
    int HEIGHT = 1500;
    boolean FULLSCREEN = false;
    int BOARD_X = 4;
    int BOARD_Y = 4;

    int[] BG_COL = new int[]{255, 148, 112};
    int[] STROKE_COL = new int[]{255, 221, 210};
    int STROKE_WEIGHT = 20;

    HashMap<Integer, int[]> COLOR_MAP = new HashMap<Integer, int[]>() {{
        put(2, new int[]{244, 215, 205});
        put(4, new int[]{226, 147, 121});
        put(8, new int[]{196, 109, 94});
    }};
}
