package danielbatchford;

import java.util.HashMap;

public interface Constants {

    int WIDTH = 1500;
    int HEIGHT = 1500;
    boolean FULLSCREEN = false;
    int BOARD_X = 5;
    int BOARD_Y = 5;

    int[] BG_COL = new int[]{44, 45, 58};
    int[] STROKE_COL = new int[]{44, 45, 58};
    int STROKE_WEIGHT = 20;

    HashMap<Integer, int[]> COLOR_MAP = new HashMap<Integer, int[]>() {{
        put(2, new int[]{149, 163, 179});
        put(4, new int[]{141, 192, 189});
        put(8, new int[]{132, 220, 198});
        put(16, new int[]{104, 149, 154});
        put(32, new int[]{90, 114, 132});
        put(64, new int[]{83, 96, 121});
        put(128, new int[]{75, 78, 109});
        put(256, new int[]{55, 56, 72});
        put(512, new int[]{82, 118, 122});
        put(1024, new int[]{147, 200, 193});
        put(2048, new int[]{80, 206, 174});
        put(4096, new int[]{141, 192, 189});
        put(8192, new int[]{132, 220, 198});
        put(16384, new int[]{104, 149, 154});
        put(32768, new int[]{90, 114, 132});
    }};

    int searchDepth = 4;
}
