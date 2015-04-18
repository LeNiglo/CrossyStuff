package com.lefrantguillaume.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossyLine {

    private final static Integer MIN_FREQ = 150;
    protected List<CrossyItem> items;
    protected Random rand;
    private Integer _speed;
    private Boolean _reverse;
    private e_type _type;
    private Animation background;
    private Integer _y;
    private Integer nextItem;
    private Integer counter;
    private Integer FREQ;
    private Integer SPEED;
    public CrossyLine(Random random, Integer posY, Integer freq, Integer speed) {
        this._y = posY;
        this.rand = random;
        this.FREQ = freq;
        this.SPEED = 3*speed;
        this.counter = 0;
        this._reverse = this.rand.nextBoolean();
        this.nextItem = this.rand.nextInt(this.FREQ) + MIN_FREQ;
        this._speed = this.rand.nextInt(this.SPEED) + this.SPEED;
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
        this.background.draw(0, this._y, 1600, 90);
        if (this._type == e_type.ROAD) {
            if (this.counter >= this.nextItem) {
                this.items.add(new CrossyItem(this.rand, this._type, this._speed, this._reverse));
                this.counter = 0;
                this.nextItem = this.rand.nextInt(this.FREQ) + MIN_FREQ;
            }
            for (int i = 0; i < this.items.size(); i++) {
                CrossyItem item = this.items.get(i);
                item.aff(this._y);
                if (item.getX() > 1700 || this.getY() < -100)
                    this.items.remove(i);
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

    public List<CrossyItem> getItems() {
        return items;
    }

    public enum e_type {
        GROUND,
        ROAD/*,
        WATER*/
    }

}
