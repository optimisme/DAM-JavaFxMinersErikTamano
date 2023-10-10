package com.project;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjHerrer implements DrawingObj {
    // Definir atributs
    public double x = 650;
    public double y = 192;
    public double destiX = 650;
    public String state = "waiting";
    public int framesPerSprite = 5;
    public int frameCounter = framesPerSprite;
    public int currentSprite = 1;
    public Image img = new Image("/assets/imgs/herrer/Herrer.png");
    public Image[] herreroMoving;
    public Image[] herreroForging;

    // Constructor
    public ObjHerrer() {
        herreroMoving = new Image[8];
        herreroForging = new Image[8];
        for (int cnt = 0; cnt < 8; cnt = cnt + 1) {
            herreroMoving[cnt] = new Image("/assets/imgs/herrer/Herrer_moving_" + (cnt + 1) + ".png");
            herreroForging[cnt] = new Image("/assets/imgs/herrer/Herrer_forging_" + (cnt + 1) + ".png");
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
        if (state.equals("walking_to_iron_storage")) {
            //gc.drawImage(new Image("/assets/imgs/herrer/Herrer_moving_" + currentSprite + ".png"), x + 40, y, -64, 64);
            gc.drawImage(herreroMoving[currentSprite - 1], x + 40, y, -64, 64);
        } else if (state.equals("walking_to_forge")) {
            //gc.drawImage(new Image("/assets/imgs/herrer/Herrer_moving_" + currentSprite + ".png"), x, y, 64, 64);
            gc.drawImage(herreroMoving[currentSprite - 1], x, y, 64, 64);
            gc.drawImage(new Image("/assets/imgs/ferro/Ingot.png"), x, y-10, 32, 32);
            gc.drawImage(new Image("/assets/imgs/ferro/Ingot.png"), x+15, y-10, 32, 32);
        } else if (state.equals("walking_to_tool_storage")) {
            //gc.drawImage(new Image("/assets/imgs/herrer/Herrer_moving_" + currentSprite + ".png"), x, y, 64, 64);
            gc.drawImage(herreroMoving[currentSprite - 1], x, y, 64, 64);
            gc.drawImage(new Image("/assets/imgs/eines/Tool.png"), x+10, y-10, 32, 32);
        } else if (state.equals("forging")) {
            //gc.drawImage(new Image("/assets/imgs/herrer/Herrer_forging_" + currentSprite + ".png"), x, y, 64, 64);
            gc.drawImage(herreroForging[currentSprite - 1], x, y, 64, 64);
        } else {
            gc.drawImage(img, x+40, y, -64, 64);
        }
    }
}
