package com.lefrantguillaume;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Guillaume on 09/02/2015.
 */
public class CrossyMap {

    protected List<CrossyLine> lines;
    private final Random rand = new Random();
    private static Integer FREQ = 500;
    private static Integer SPEED = 20;
    private Integer count;

    public CrossyMap() {

        this.lines = new ArrayList<CrossyLine>(12);
        this.count = 0;
        for (int i = 0; i < 12; i++) {
            lines.add(new CrossyLine(this.rand, i*90, FREQ, SPEED));
        }
    }

    public Boolean generateNewLine() {

        this.lines.add(new CrossyLine(this.rand, this.lines.get(this.lines.size() - 1).getY() + 90, FREQ, SPEED));
        this.harder();
        return true;
    }

    public void show() {
        for (int i = 0; i < this.lines.size(); i++) {
            this.lines.get(i).aff();
        }
    }

    public void move(Integer toMove) {
        for (int i = 0; i < this.lines.size(); i++) {
            this.lines.get(i).moveY(toMove);
        }
    }

    public void harder() {
        if (this.FREQ > 150)
            this.FREQ -= 3;
        if (this.SPEED < 1250)
            this.SPEED++;
        this.count++;
    }

    public void removeFirst() {
        this.lines.remove(0);
    }

    public Integer getCount() { return this.count; }

}
