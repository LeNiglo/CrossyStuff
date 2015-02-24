package com.lefrantguillaume.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CrossyChar {
    private Animation anim;
    private Animation move;
    private Boolean walking;
    private Integer score;

    public CrossyChar() {
        try {
            this.score = 0;
            this.walking = false;
            Image[] perso = {new Image("/media/img/character/perso1.png"), new Image("/media/img/character/perso2.png"), new Image("/media/img/character/perso3.png")};
            Image[] moves = {new Image("/media/img/character/move1.png"), new Image("/media/img/character/move2.png"), new Image("/media/img/character/move3.png"), new Image("/media/img/character/move4.png")};
            int[] idleDuration = {750, 750, 500};
            int[] moveDuration = {50, 50, 50, 50};
            this.anim = new Animation(perso, idleDuration, true);
            this.move = new Animation(moves, moveDuration, true);
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    public void aff() {
        if (walking)
            this.move.draw((1600 - 90) / 2, 90, 90, 90);
        else
            this.anim.draw((1600 - 90) / 2, 90, 90, 90);
    }

    public void setWalk(Boolean state) {
        this.walking = state;
        if (!state) {
            ++this.score;
        }
    }

    public Integer getScore() {
        return this.score;
    }

    public boolean checkColide(int x1, int x2) {
        int center = ((1600 - 30) / 2 + (1600 + 30) / 2) / 2;
        return center >= x1 && center <= x2;
    }
}
