package proj.estacionamiento.Clases;

import java.util.Random;

public class crearAuto implements Runnable {

    private int Hilos = 100;
    private Auto[] Autos;
    private Random random = new Random(System.currentTimeMillis());

    public crearAuto(Auto[] Autos) {
        this.Autos = Autos;
    }

    @Override
    public void run() {
        Auto auto;
        for (int i = 0; i < Hilos; i++) {
            auto = Autos[i];
            new Thread(auto,"Carro "+ i).start();
            try {
                Thread.sleep(random.nextInt(300+100) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
