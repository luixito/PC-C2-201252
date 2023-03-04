package proj.estacionamiento.Clases;
import java.util.Random;


public class Entrada {

    private String [] bufferCar;
    private Random random;


    public Entrada() {
        Config.status = false;
        bufferCar = new String[Config.autosTotal];
        for(int i = 0; i<Config.autosTotal; i++){
            bufferCar[i] ="vacio";
        }
        random = new Random(System.currentTimeMillis());
    }

    public synchronized void arriveCar()  {
            for(int i=0; i<bufferCar.length; i++){
                if(bufferCar[i] == "vacio") {
                    System.out.println("Entrada <-- carro "+ i  );
                    bufferCar[i] = Thread.currentThread().getName();
                    Config.checkEntrada = i;
                    break;
                }

            }
    }

    public synchronized void leaveCar(){
            for(int i=0; i<bufferCar.length; i++){
                if(bufferCar[i].compareTo(Thread.currentThread().getName()) == 0){
                    bufferCar[i]="vacio";
                    System.out.println("-->Salida carro "+ i);
                    Config.checkEntrada = i;
                    break;
                }
            }
    }

}
