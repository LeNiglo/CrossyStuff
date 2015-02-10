package com.lefrantguillaume;

import org.newdawn.slick.AppGameContainer;

public class Main {

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Game("CrossyStuff"));
            app.setDisplayMode(1600, 900, false);
            app.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
