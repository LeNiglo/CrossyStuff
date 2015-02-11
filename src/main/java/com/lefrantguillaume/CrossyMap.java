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

    public CrossyMap() {

        // First Generation
        this.lines = new ArrayList<CrossyLine>(12);
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
        if (this.FREQ > 200)
            this.FREQ--;
        if (this.SPEED < 100)
            this.SPEED++;
        System.out.println("Harder : F="+this.FREQ+" S="+this.SPEED);
    }

    public void removeFirst() {
        this.lines.remove(0);
    }

}
