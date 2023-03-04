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
    public static int espacios = 20;
    public static int totalCarros = 0;


    public Auto(Semaphore mutex, Semaphore door, Entrada entrada, Image image) {
        this.mutex = mutex;
        this.entrada = entrada;
        this.door = door;
        this.image = image;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        try {
            door.acquire();
            if(totalCarros == 20){
                espacios=0;
                try {
                    mutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(totalCarros < 20){
                door.release();
                totalCarros++;
                espacios--;
                entrada.arriveCar();
                setChanged();
                notifyObservers(1);
                try {
                    Thread.sleep(random.nextInt(4000+1000)+1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalCarros--;
                entrada.leaveCar();
                door.release();
                mutex.release();
                espacios++;
                mutex.acquire();
                setChanged();
                notifyObservers(0);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Image getImage() {
        return image;
    }
}