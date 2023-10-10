package com.project;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjTren implements DrawingObj {
    // Definir atributs
    public double x = 860;
    public double y = 160;
    public double destiX = 860;
    public int toolsAmount = 0;
    public String status = "waiting";
    public Image img = new Image("/assets/imgs/tren/Tren.png");

    // Animar la posició del tren cap al destí
    public void run(Canvas cnv, double fps) {
        if (x < destiX) {
            x = x + 200.0 / fps;
        }
        if (x > destiX) {
           x = x - 200.0 / fps;
        }
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        if (status.equals("returning")) {
            gc.drawImage(img, x+248, y, -224, 96);
        } else {
            gc.drawImage(img, x, y, 224, 96);
        }

    }
}
