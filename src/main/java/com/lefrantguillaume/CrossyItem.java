package com.lefrantguillaume;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Created by Guillaume on 10/02/2015.
 */
public class CrossyItem {

    private static final Integer NBCARS = 4;
    private Animation item;
    protected Integer _speed;
    protected Integer _x;
    private Boolean _reverse;

    CrossyItem(Random random, CrossyLine.e_type type, Integer speed, Boolean reverse) {
        this._speed = speed;
        this._reverse = reverse;
        System.out.println("New Item");
        if (this._reverse) {
            this._x = -200;
        } else {
            this._x = 1800;
        }
        if (type == CrossyLine.e_type.ROAD) {
            try {
                Image[] back = {new Image("/media/img/items/car"+(1+random.nextInt(NBCARS))+".png")};
                int[] duration = {200};
                this.item = new Animation(back, duration, true);
            } catch (SlickException e) {
                e.printStackTrace();
            }

        } else if (type == CrossyLine.e_type.GROUND) {

        }
    }

    public void aff(Integer y) {
        if (this._reverse) {
            this.item.getCurrentFrame().getFlippedCopy(true, false).draw(this._x, y, 90, 90);
            this._x += this._speed / 30;
        } else {
            this.item.getCurrentFrame().getFlippedCopy(false, false).draw(this._x, y, 90, 90);
            this._x -= this._speed / 30;
        }
    }
}
