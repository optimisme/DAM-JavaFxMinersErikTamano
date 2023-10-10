package com.project;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjMiner implements DrawingObj {
    // Definir atributs
    public double x = 94;
    public double y = 192;
    public double destiX = 94;
    public String state = "waiting";
    public String facing = "left";
    public int framesPerSprite = 5;
    public int frameCounter = framesPerSprite;
    public int currentSprite = 1;
    public Image img = new Image("/assets/imgs/miner/Miner.png");
    public Image[] minersMoving;
    public Image[] minersMining;

    // Constructor
    public ObjMiner() {
        minersMoving = new Image[8];
        minersMining = new Image[8];
        for (int cnt = 0; cnt < 8; cnt = cnt + 1) {
            minersMoving[cnt] = new Image("/assets/imgs/miner/Miner_moving_" + (cnt + 1) + ".png");
            minersMining[cnt] = new Image("/assets/imgs/miner/Miner_mining_" + (cnt + 1) + ".png");
        }
    }

    // Animar la posició del cotxe cap al destí
    public void run(Canvas cnv, double fps) {
        if (x < destiX) {
            x = x + 100.0 / fps;
        }
        if (x > destiX) {
            x = x - 100.0 / fps;
        }
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        frameCounter--;
        if (frameCounter == 0) {
            if (currentSprite == 8) {
                currentSprite = 1;
            } else {
                currentSprite++;
            }
            frameCounter = framesPerSprite;
        }
        if (state.equals("walking_to_mine")) {
            gc.drawImage(minersMoving[currentSprite - 1], x + 40, y, -64, 64);
        } else if (state.equals("walking_to_storage")) {
            gc.drawImage(minersMoving[currentSprite - 1], x, y, 64, 64);
            gc.drawImage(new Image("/assets/imgs/ferro/Ingot.png"), x+5, y-10, 32, 32);
        } else if (state.equals("mining")) {
            gc.drawImage(minersMining[currentSprite - 1], x + 40, y, -64, 64);
        } else {
            gc.drawImage(img, x, y, 64, 64);
        }
    }
}
