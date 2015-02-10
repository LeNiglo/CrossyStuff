package com.lefrantguillaume;


import javafx.animation.Animation;
import org.newdawn.slick.*;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Guillaume on 09/02/2015.
 */
public class Game extends BasicGame {

    private final Boolean running = true;
    private CrossyMap map;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        this.map = new CrossyMap();
        Input a = gameContainer.getInput();
        if (a.isKeyDown(Input.KEY_UP)) {
            this.map.generateNewLine();
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        this.map.update(i);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        this.map.show();
    }
}
