package com.project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Futures futures;
    private Drawing drawing;
    public static ArrayList<DrawingObj> drawingList;
    public static ObjEscenari escenari;
    public static ArrayList<ObjMiner> miners;
    public static ArrayList<ObjHerrer> herrers;
    public static ObjMagatzemFerro magatzemFerro;
    public static ObjMagatzemEines magatzemEines;
    public static ObjFerro ferro;
    public static ObjForja forja;
    public static ObjTren tren;


    public static void main(String[] args) {
        // Iniciar app JavaFX
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Iniciar objectes
        futures = new Futures();
        drawing = new Drawing();
        drawingList = new ArrayList<>();
        escenari = new ObjEscenari();
        miners = new ArrayList<>();
        herrers = new ArrayList<>();
        magatzemFerro = new ObjMagatzemFerro();
        magatzemEines = new ObjMagatzemEines();
        ferro = new ObjFerro();
        forja = new ObjForja();
        tren = new ObjTren();

        // Construir interficie
        Group root = buildInterface(primaryStage);

        // Definir escena
        Scene scene = new Scene(root);
        primaryStage.setTitle("Carrera");
        primaryStage.onCloseRequestProperty();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setWidth(1162);
        primaryStage.setHeight(350);

        // Afegir objectes a la llista de dibuix
        drawingList.add(escenari);
        drawingList.add(magatzemFerro);
        drawingList.add(magatzemEines);
        drawingList.add(ferro);
        drawingList.add(forja);
        drawingList.add(tren);
        setPersonatges(4); // APJ Generar 4 miners


        futures.start();
    }

    public Group buildInterface(Stage primaryStage) {
                
        // Definir l'arrel de l'escena
        Canvas canvas = new Canvas(1162, 350);
        drawing.start(canvas);
        Group root = new Group();
        VBox vbox = new VBox(8);
        vbox.setPrefWidth(300);
        vbox.getChildren().addAll(canvas);
        vbox.setPrefHeight(320);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
        return root;
    }

    public static void setPersonatges(int cant) {
        for (int i = 0; i < cant; i++) {
            ObjMiner miner = new ObjMiner();
            ObjHerrer herrer1 = new ObjHerrer();
            ObjHerrer herrer2 = new ObjHerrer();
            drawingList.add(miner);
            miners.add(miner);
            herrers.add(herrer1);
            drawingList.add(herrer1);
            herrers.add(herrer2);
            drawingList.add(herrer2);
        }
    }

}
