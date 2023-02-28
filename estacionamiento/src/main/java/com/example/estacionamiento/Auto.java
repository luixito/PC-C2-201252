package com.example.estacionamiento;

import java.util.Random;
import java.util.Observable;

public class Auto extends Observable implements Runnable{
    private Espacio pos;
    private String color;
    private int tam;
    private int distanciaX;
    private Random random;
    private int distanciaY;
    private boolean status = true;

    public Auto() {
        status = true;
        random = new Random(System.currentTimeMillis());
    }

    public void setPosicion(Espacio pos, int v){
        this.pos = pos;
        distanciaX = v;
        distanciaY = v;
    }

    public Espacio getPos() {
        return pos;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
    @Override
    public void run() {
        while(status) {
            //Notificar cambio

            //Dormir el hilo
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Realizar nuevos cálculos
            pos.setX(pos.getX() + distanciaX);
            pos.setY(pos.getY() + distanciaY);
            System.out.println(pos.getX()+":"+pos.getY());

            if (pos.getX() >= 590)
                distanciaX *= -1;
            if (pos.getX() < 10)
                distanciaX *= -1;
            if (pos.getY() < 10)
                distanciaY *= -1;
            if (pos.getY() >= 490)
                distanciaY *= -1;

            this.setChanged();
            this.notifyObservers(pos);

        }
        System.out.println("GoodBay");
    }
}
