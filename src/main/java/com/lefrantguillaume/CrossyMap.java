package com.lefrantguillaume;

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
        this.lines = new ArrayList<CrossyLine>(10);
        for (int i = 0; i < 10; i++) {
            lines.add(new CrossyLine(this.rand));
        }

        System.out.println("First Generation over, there is "+lines.size()+ " lines.");
    }

    public Boolean generateNewLine() {

        this.lines.add(new CrossyLine(this.rand));
        this.lines.remove(0);

        System.out.println("New Generation done, there is now "+lines.size()+ " lines.");

        return true;
    }

    public void show() {
        for (int i = 0; i < this.lines.size(); i++) {
            this.lines.get(i).affLine(i);
        }
    }

    public void update(final int delta) {
        for (int i = 0; i < this.lines.size(); i++) {
            this.lines.get(i).update(delta);
        }
    }
}
