package com.project;


import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


// Codi executat pels processos paral·lels
// en una clase a part, per organitzar el codi
public class Futures {

    // Definir atributs
    private boolean running = true;
    private static ArrayList<CompletableFuture<Void>> threadsList = new ArrayList<>();

    // Getters i setters
    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    // Inicia els processos paral·lels
    public void start() {
        running = true;
        threadsList.add(CompletableFuture.runAsync(getRunnableTren()));
        for (ObjMiner o : Main.miners) {
            threadsList.add(CompletableFuture.runAsync(getRunnableMiner(o)));
        }
        for (ObjHerrer o : Main.herrers) {
            threadsList.add(CompletableFuture.runAsync(getRunnableHerrer(o)));
        }
    }

    // Atura els processos paral·lels (quan acaben el bucle)
    public void stop() {
        System.out.println("Esperant threads ...");
        if (running) {
            running = false;
            threadsList.forEach(CompletableFuture::join);
            threadsList.clear();
            System.out.println("Threads acabats");
        }
    }

    public Runnable getRunnableMiner(ObjMiner o) {
        return new Runnable() {

            @Override
            public void run() {
                while (running) {
                    if (o.state.equals("waiting")) {
                        try {
                            ObjMagatzemFerro.mutex.lock();
                            if (Main.magatzemFerro.futureIronAmount < 10) {
                                o.destiX = 65;
                                o.state = "walking_to_mine";
                                o.currentSprite = 1;
                                o.frameCounter = o.framesPerSprite;
                                Main.magatzemFerro.futureIronAmount++;
                            }
                        } finally {
                            ObjMagatzemFerro.mutex.unlock();
                        }

                    } else if (o.state.equals("walking_to_mine") && o.x < 70) {
                        o.state = "mining";
                        o.currentSprite = 1;
                        o.frameCounter = o.framesPerSprite;
                        int sleepyTime = ((int) (Math.random() * 900)) + 100;
                        try {
                            Thread.sleep(sleepyTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        o.state = "walking_to_storage";
                        o.currentSprite = 1;
                        o.frameCounter = o.framesPerSprite;
                        o.destiX = 250;
                    } else if (o.state.equals("walking_to_storage") && o.x > 245) {
                        try {
                            ObjMagatzemFerro.mutex.lock();
                            Main.magatzemFerro.ironAmount++;
                            o.state = "waiting";
                            o.currentSprite = 1;
                            o.frameCounter = o.framesPerSprite;
                        } finally {
                            ObjMagatzemFerro.mutex.unlock();
                        }
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

    }

    public Runnable getRunnableHerrer(ObjHerrer o) {
        return new Runnable() {

            @Override
            public void run() {
                while (running) {
                    if (o.state.equals("waiting")) {
                        try {
                            ObjMagatzemFerro.mutex.lock();
                            ObjMagatzemEines.mutex.lock();
                            if (Main.magatzemFerro.ironAmount - Main.magatzemFerro.ironToBeUsed >= 2
                                    && Main.magatzemEines.futureToolsAmount < 10) {
                                o.destiX = 430;
                                o.state = "walking_to_iron_storage";
                                o.currentSprite = 1;
                                o.frameCounter = o.framesPerSprite;
                                Main.magatzemEines.futureToolsAmount++;
                                Main.magatzemFerro.ironToBeUsed += 2;
                            }
                        } finally {
                            ObjMagatzemFerro.mutex.unlock();
                            ObjMagatzemEines.mutex.unlock();
                        }

                    } else if (o.state.equals("walking_to_iron_storage") && o.x < 435) {
                        try {
                            ObjMagatzemFerro.mutex.lock();
                            o.state = "walking_to_forge";
                            o.currentSprite = 1;
                            o.frameCounter = o.framesPerSprite;
                            o.destiX = 570;
                            Main.magatzemFerro.ironAmount -= 2;
                            Main.magatzemFerro.futureIronAmount -= 2;
                            Main.magatzemFerro.ironToBeUsed -= 2;
                        } finally {
                            ObjMagatzemFerro.mutex.unlock();
                        }
                    } else if (o.state.equals("walking_to_forge") && o.x > 565) {
                        o.state = "forging";
                        o.currentSprite = 1;
                        o.frameCounter = o.framesPerSprite;
                        int sleepyTime = ((int) (Math.random() * 1800)) + 200;
                        try {
                            Thread.sleep(sleepyTime);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        o.state = "walking_to_tool_storage";
                        o.destiX = 720;
                    } else if (o.state.equals("walking_to_tool_storage") && o.x > 715) {
                        try {
                            ObjMagatzemEines.mutex.lock();
                            Main.magatzemEines.toolsAmount++;
                            o.state = "waiting";
                        } finally {
                            ObjMagatzemEines.mutex.unlock();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
    }

    public Runnable getRunnableTren() {
        return new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                while (running) {
                    if (Main.tren.status.equals("waiting")) {
                        try {
                            ObjMagatzemEines.mutex.lock();

                            if (Main.magatzemEines.toolsAmount > 0) {
                                Main.magatzemEines.futureToolsAmount--;
                                Main.magatzemEines.toolsAmount--;
                                Main.tren.toolsAmount++;
                            }
                        } finally {
                            ObjMagatzemEines.mutex.unlock();
                        }
                        if (Main.tren.toolsAmount == 5) {
                            Main.tren.status = "going";
                            Main.tren.destiX = 1170;
                        }
                    } else if (Main.tren.status.equals("going") && Main.tren.x > 1165) {
                        try {
                            Thread.sleep(((int) (Math.random() * 4500)) + 500);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Main.tren.toolsAmount = 0;
                        Main.tren.status = "returning";
                        Main.tren.destiX = 860;
                    } else if (Main.tren.status.equals("returning") && Main.tren.x < 865) {
                        Main.tren.status = "waiting";
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
    }
}