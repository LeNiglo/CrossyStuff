package com.lefrantguillaume.game;


import org.newdawn.slick.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Game extends BasicGame {

    private static final Integer TOMOVE = 6;
    private static final String GAME_DIR = "CrossyStuff";
    private static final String SAVE_FILE = "highscore.dat";
    protected CrossyMap map;
    protected CrossyChar character;
    protected Input keymap;
    private Integer moving;
    private Integer highscore = -1;
    private long runningTime = 0;


    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        Path path = Paths.get(System.getProperty("user.home") + System.getProperty("file.separator") + GAME_DIR);
        if (Files.notExists(path)) {
            File file = path.toFile();
            if (!file.mkdirs())
                return;
        }

        this.map = new CrossyMap();
        this.character = new CrossyChar();
        this.keymap = gameContainer.getInput();
        this.keymap.enableKeyRepeat();
        // gameContainer.setVSync(true);
        this.moving = 0;
        this.loadHighScore();
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        this.runningTime += delta;

        if (this.runningTime > 30) {

            this.map.moveItems();

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
            if (this.map.checkColide(this.character)) {
                if (this.character.getScore() > this.getHighScore())
                    this.saveHighScore(this.character.getScore());
                System.out.println("YOU DIED !\nScore : " + this.character.getScore() + "\nBest : " + this.getHighScore());
                gameContainer.exit();
            }
            this.runningTime = 0;
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.map.show();
        graphics.drawString("Score: " + this.character.getScore(), 10, 30);
        graphics.drawString("Best: " + (this.character.getScore() > this.getHighScore() ? this.character.getScore() : this.getHighScore()), 10, 50);
        this.character.aff();
    }

    private Integer getHighScore() {
        if (this.highscore == -1) {
            this.loadHighScore();
        }
        return this.highscore;
    }

    private void loadHighScore() {
        String homePath = System.getProperty("user.home");
        String delim = System.getProperty("file.separator");

        try {
            Path path = Paths.get(homePath + delim + GAME_DIR + delim + SAVE_FILE);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.substring(0, 9).equals("HIGHSCORE")) {
                    try {
                        this.highscore = Integer.parseInt(line.substring(10));
                        break;
                    } catch (Exception ignore) {

                    }
                }
            }
        } catch (NoSuchFileException e) {
            System.out.println("No Such File or Directory : " + homePath + delim + GAME_DIR + delim + SAVE_FILE);
            this.saveHighScore(0);
            this.loadHighScore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveHighScore(int hs) {
        String homePath = System.getProperty("user.home");
        String delim = System.getProperty("file.separator");

        this.highscore = hs;

        File file = new File(homePath + delim + GAME_DIR + delim + SAVE_FILE);
        Path path = file.toPath();

        if (Files.notExists(path)) {
            try {
                System.out.println("Creating : " + file.toString());
                if (!file.createNewFile())
                    return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.canWrite()) {
            List<String> lines = new ArrayList<String>();
            lines.add("HIGHSCORE=" + hs);
            try {
                Files.write(path, lines, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
