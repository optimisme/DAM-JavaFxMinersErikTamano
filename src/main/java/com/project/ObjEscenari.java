package com.project;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjEscenari implements DrawingObj {

    public Image img = new Image("/assets/imgs/background.png");

    @Override
    public void run(Canvas cnv, double fps) {
        // No cal animar res

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, 0, 0, 1162, 320);
    }
}
