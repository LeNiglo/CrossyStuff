package com.lefrantguillaume;


import org.newdawn.slick.*;

/**
 * Created by Guillaume on 09/02/2015.
 */
public class Game extends BasicGame {

    private final Boolean running = true;
    protected CrossyMap map;
    protected CrossyChar character;
    protected Input keymap;
    private Integer moving;
    private static final Integer TOMOVE = 3;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        this.map = new CrossyMap();
        this.character = new CrossyChar();
        this.keymap = gameContainer.getInput();
        this.keymap.enableKeyRepeat();
        gameContainer.setVSync(true);
        this.moving = 0;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (this.keymap.isKeyPressed(Input.KEY_ESCAPE)) {
            gameContainer.exit();
        }
        if (this.keymap.isKeyDown(Input.KEY_DOWN) && this.moving == 0) {
            this.map.generateNewLine();
            this.character.setWalk(true);
            this.moving = 90;
        }
        if (this.moving != 0) {
            if (this.moving.equals(TOMOVE)) {
                this.character.setWalk(false);
                this.map.removeFirst();
            }
            this.map.move(TOMOVE);
            this.moving -= TOMOVE;
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        this.map.show();
        this.character.aff();
    }
}
