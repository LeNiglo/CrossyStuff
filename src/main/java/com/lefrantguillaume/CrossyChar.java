package com.lefrantguillaume;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Created by Guillaume on 10/02/2015.
 */
public class CrossyChar {
    private Animation anim;
    private Animation move;
    private Boolean walking;

    public CrossyChar() {
        try {
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
        if (walking == true)
            this.move.draw((1600 - 900 / 10) / 2, 900 / 10, 900 / 10, 900 / 10);
        else
            this.anim.draw((1600 - 900 / 10) / 2, 900 / 10, 900 / 10, 900 / 10);
    }

    public void setWalk(Boolean state) {
        this.walking = state;
    }
}
