package proj.estacionamiento.Clases;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Auto extends Observable implements Runnable {

    private Semaphore mutex;
    private Semaphore door;
    private Entrada entrada;
    private Random random;
    private Color color;
    private Image image;
    public static int totalCarros = 0;


    public Auto(Semaphore mutex, Semaphore door, Entrada entrada, Image image) {
        this.mutex = mutex;
        //this.access_car = access_car;
        this.entrada = entrada;
        this.door = door;
        this.image = image;
        random = new Random(System.currentTimeMillis());
    }

    public String getColorString(){
        String valor = " ";
        if(color.toString().equals("0xd2691eff")){
            valor= "Chocolate";
        }
        if(color.toString().equals("0x000000ff")){
            valor= "Negro";
        }
        if(color.toString().equals("0xffff00ff")){
            valor= "Amarillo";
        }
        if(color.toString().equals("0x008000ff")){
            valor= "Verde";
        }
        if(color.toString().equals("0x0000ffff")){
            valor= "Azul";
        }
        if(color.toString().equals("0x00ffffff")){
            valor= "Aqua";
        }
        if(color.toString().equals("0x8a2be2ff")){
            valor= "Blueviolet";
        }

        return valor;
    }

    @Override
    public void run() {
        try {
            door.acquire();
            //System.out.println("-----Door Bloqueado  =" + door.availablePermits());
            if(totalCarros == Config.autosTotal){
                Config.espacios=0;
                try {
                    mutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(totalCarros < Config.autosTotal){
                door.release();
                totalCarros++;
                Config.espacios--;
                entrada.arriveCar();
                setChanged();
                notifyObservers(1);
                try {
                    Thread.sleep(random.nextInt(3000+1000)+1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalCarros--;
                entrada.leaveCar();
                door.release();
                mutex.release();
                Config.espacios++;
                mutex.acquire();
                setChanged();
                notifyObservers(0);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public Color getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }
}