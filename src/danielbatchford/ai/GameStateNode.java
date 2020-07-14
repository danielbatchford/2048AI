package danielbatchford.ai;

import danielbatchford.Game;

public class GameStateNode {

    Game node;
    Game child1;
    Game child2;
    Game child3;
    Game child4;

    GameStateNode(Game node, Game child1, Game child2, Game child3, Game child4){
        this.node = node;
        this.child1 = child1;
        this.child2 = child2;
        this.child3 = child3;
        this.child4 = child4;
    }

}
