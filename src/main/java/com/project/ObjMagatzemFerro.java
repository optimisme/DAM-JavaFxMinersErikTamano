package com.project;


import java.util.concurrent.locks.ReentrantLock;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjMagatzemFerro implements DrawingObj {
    // Definir atributs
    public double x = 300;
    public double y = 128;
    public int ironAmount = 0;
    public int futureIronAmount = 0;
    public int ironToBeUsed = 0;
    public Image img = new Image("/assets/imgs/magatzem/Magatzem.png");
    public Image imgIron = new Image("/assets/imgs/ferro/Ingot.png");
    public static ReentrantLock mutex = new ReentrantLock();

    // Animar la posició del cotxe cap al destí
    public void run(Canvas cnv, double fps) {
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y, 128, 128);
        if (ironAmount >= 10) {
            gc.drawImage(new Image("/assets/imgs/numeros/1.png"), x+40, y+60, 64, 64);
            gc.drawImage(new Image("/assets/imgs/numeros/0.png"), x+68, y+60, 64, 64);
        } else {
            gc.drawImage(new Image("/assets/imgs/numeros/" + ironAmount + ".png"), x+52, y+60, 64, 64);
        }
        for (int i = 0; i < ironAmount; i++) {
            gc.drawImage(imgIron, x+25+(5*i), y+30, 32, 32);
        }
    }
}
