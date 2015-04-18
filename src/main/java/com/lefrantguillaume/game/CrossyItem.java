package com.lefrantguillaume.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;

public class CrossyItem {

    private static final Integer NBCARS = 4;
    protected Integer _speed;
    protected Integer _x;
    private Animation item;
    private Boolean _reverse;

    CrossyItem(Random random, CrossyLine.e_type type, Integer speed, Boolean reverse) {
        this._speed = speed;
        this._reverse = reverse;
        if (this._reverse) {
            this._x = -100;
        } else {
            this._x = 1700;
        }
        if (type == CrossyLine.e_type.ROAD) {
            try {
                Image[] back = {new Image("/media/img/items/car" + (1 + random.nextInt(NBCARS)) + ".png")};
                int[] duration = {200};
                this.item = new Animation(back, duration, true);
            } catch (SlickException e) {
                e.printStackTrace();
            }

        }
    }

    public void aff(Integer y) {
        if (this._reverse) {
            this.item.getCurrentFrame().getFlippedCopy(true, false).draw(this._x, y, 90, 90);
        } else {
            this.item.getCurrentFrame().getFlippedCopy(false, false).draw(this._x, y, 90, 90);
        }
    }

    public void move() {
        if (this._reverse) {
            this._x += this._speed / 10;
        } else {
            this._x -= this._speed / 10;
        }
    }

    public Integer getX() {
        return this._x;
    }
}
