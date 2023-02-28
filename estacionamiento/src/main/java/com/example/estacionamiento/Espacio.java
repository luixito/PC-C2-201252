package com.example.estacionamiento;

public class Espacio {
    private int x;
    private int y;
    private boolean ocupado;
    private int id; 

    public Espacio(int id, int x, int y, boolean ocupado) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.ocupado=ocupado;
    }

    public int getId() {
        return id;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean getOcupado() {
        return ocupado;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }



}
