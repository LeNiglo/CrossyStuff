package com.lefrantguillaume;

import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by Guillaume on 09/02/2015.
 */
public class CrossyMap {

    protected List<CrossyLine> lines;
    private final Random rand = new Random();

    public CrossyMap() {

        // First Generation
        this.lines = new ArrayList<CrossyLine>(12);
        for (int i = 0; i < 12; i++) {
            lines.add(new CrossyLine(this.rand, i*90));
        }
    }

    public Boolean generateNewLine() {

        this.lines.add(new CrossyLine(this.rand, this.lines.get(this.lines.size() - 1).getY() + 90));
        return true;
    }

    public void show() {
        for (int i = 0; i < this.lines.size(); i++) {
            this.lines.get(i).affLine();
        }
    }

    public void move(Integer toMove) {
        for (int i = 0; i < this.lines.size(); i++) {
            this.lines.get(i).moveY(toMove);
        }
    }

    public void removeFirst() {
        this.lines.remove(0);
    }

}
