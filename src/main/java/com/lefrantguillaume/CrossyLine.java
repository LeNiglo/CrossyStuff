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
    private Boolean _reverse;
    private e_type _type;
    private Animation background;
    private Integer _y;
    protected List<CrossyItem> items;
    protected Random rand;
    private Integer nextItem;
    private Integer counter;
    private Integer FREQ;
    private Integer SPEED;

    public CrossyLine(Random random, Integer posY, Integer freq, Integer speed) {
        this._y = posY;
        this.rand = random;
        this.FREQ = freq;
        this.SPEED = speed;
        this.counter = 0;
        this._reverse = this.rand.nextBoolean();
        this.nextItem = this.rand.nextInt(this.FREQ) + this.FREQ;
        this._speed = this.rand.nextInt((100 - 1) + this.SPEED);
        this._type = e_type.values()[this.rand.nextInt(e_type.values().length)];
        this.items = new ArrayList<CrossyItem>();
        try {
            Image[] back = {new Image("/media/img/grounds/" + this._type.name() + ".png"), new Image("/media/img/grounds/" + this._type.name() + ".png")};
            int[] duration = {200, 200};
            this.background = new Animation(back, duration, true);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void aff() {
        this.background.draw(0, this._y, 1600, 900 / 10);
        if (this._type == e_type.ROAD) {
            if (this.counter.equals(this.nextItem)) {
                this.items.add(new CrossyItem(this.rand, this._type, this._speed, this._reverse));
                this.counter = 0;
                this.nextItem = this.rand.nextInt(this.FREQ) + this.FREQ;
            }
            for (int i = 0; i < this.items.size(); i++) {
                this.items.get(i).aff(this._y);
            }
        }
        this.counter++;
    }

    public Integer getY() {
        return this._y;
    }

    public void moveY(Integer toMove) {
        this._y -= toMove;
    }

}
