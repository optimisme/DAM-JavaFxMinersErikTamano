package com.project;


import java.util.concurrent.locks.ReentrantLock;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjMagatzemEines implements DrawingObj {
    // Definir atributs
    public double x = 750;
    public double y = 128;
    public int toolsAmount = 0;
    public int futureToolsAmount = 0;
    public Image img = new Image("/assets/imgs/magatzem/Magatzem.png");
    public Image imgTool = new Image("/assets/imgs/eines/Tool.png");
    public static ReentrantLock mutex = new ReentrantLock();

    // Animar la posició del cotxe cap al destí
    public void run(Canvas cnv, double fps) {
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y, 128, 128);
        if (toolsAmount >= 10) {
            gc.drawImage(new Image("/assets/imgs/numeros/1.png"), x+40, y+60, 64, 64);
            gc.drawImage(new Image("/assets/imgs/numeros/0.png"), x+68, y+60, 64, 64);
        } else {
            gc.drawImage(new Image("/assets/imgs/numeros/" + toolsAmount + ".png"), x+52, y+60, 64, 64);
        }
        for (int i = 0; i < toolsAmount; i++) {
            gc.drawImage(imgTool, x+20+(5*i), y+30, 32, 32);
        }

        
    }
}
