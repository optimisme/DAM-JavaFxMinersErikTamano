package com.project;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjForja implements DrawingObj {
    // Definir atributs
    public double x = 600;
    public double y = 192;
    public boolean hasIron = false;
    public String state = "waiting";
    public String facing = "left";
    public int currentFrame = 0;
    public Image img = new Image("/assets/imgs/forja/Anvil.png");
    
    public void run(Canvas cnv, double fps) {
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y, 64, 64);
    }
}

