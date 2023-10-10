package com.project;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjFerro implements DrawingObj {
    // Definir atributs
    public double x = 10;
    public double y = 224;
    public int ironAmount = 0;
    public Image img = new Image("/assets/imgs/ferro/ore.png");

    public void run(Canvas cnv, double fps) {
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y, 64, 32);
        
    }
}
