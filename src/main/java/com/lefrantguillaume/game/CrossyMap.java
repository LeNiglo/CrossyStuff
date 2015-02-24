package com.lefrantguillaume.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossyMap {

    private static Integer FREQ = 500;
    private static Integer SPEED = 20;
    private final Random rand = new Random();
    protected List<CrossyLine> lines;

    public CrossyMap() {

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
        for (CrossyLine line : this.lines) {
            line.aff();
        }
    }

    public void move(Integer toMove) {
        for (CrossyLine line : this.lines) {
            line.moveY(toMove);
        }
    }

    public void harder() {
        if (FREQ > 150)
            FREQ -= 3;
        if (SPEED < 1250)
            SPEED++;
    }

    public void removeFirst() {
        this.lines.remove(0);
    }

    public Boolean checkColide(CrossyChar character) {
        for (CrossyLine line : this.lines) {
            if (line.getY() >= 70 && line.getY() <= 110) {
                List<CrossyItem> items = line.getItems();
                for (CrossyItem item : items) {
                    if (character.checkColide(item.getX(), item.getX() + 90)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
