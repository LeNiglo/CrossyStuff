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

    private Integer _speed;
    private e_type _type;
    private Animation background;
    private Integer y;
    protected List<CrossyItem> items;

    public CrossyLine(Random random, Integer posY) {
        this.y = posY;
        this._speed = random.nextInt((100 - 1) + 1);
        this._type = e_type.values()[random.nextInt(e_type.values().length)];
        try {
            Image[] back = {new Image("/media/img/grounds/" + this._type.name() + ".png"), new Image("/media/img/grounds/" + this._type.name() + ".png")};
            int[] duration = {200, 200};
            this.background = new Animation(back, duration, true);
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    public void affLine() {

        System.out.println("[CrossyLine] Type is : " + this._type.name() + " and speed is : " + this._speed + ".");
        this.background.draw(0, this.y, 1600, 900 / 10);
    }

    public Integer getY() {
        return this.y;
    }
    public void moveY(Integer toMove) {
        this.y -= toMove;
    }
}
