package com.lefrantguillaume;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Guillaume on 09/02/2015.
 */
public class CrossyLine {

    public enum e_type {
        GROUND,
        ROAD/*,
        WATER*/
    }

    ;

    private Integer _speed;
    private e_type _type;
    private Animation background;

    public CrossyLine(Random random) {
        //TODO: Generate randomly the Crossy Line.
        this._speed = random.nextInt((100 - 1) + 1);
        this._type = e_type.values()[random.nextInt(e_type.values().length)];
        try {
            Image[] back = {new Image("/media/img/grounds/" + this._type.name() + ".png"), new Image("/media/img/grounds/" + this._type.name() + ".png")};
            int[] duration = {20, 20};
            this.background = new Animation(back, duration, false);
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    public void affLine(int pos) {
        //System.out.println("[CrossyLine] Type is : " + this._type.name() + " and speed is : " + this._speed + ".");
        this.background.draw(0, ((900 / 10)*pos), 1600, 900 / 10);
    }

    public void update(int delta) {
        this.background.update(delta);
    }
}
