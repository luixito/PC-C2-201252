package proj.estacionamiento.Clases;
import java.util.Random;


public class Entrada {

    private String [] bufferCar;
    public static int checkEntrada = 0;
    private Random random;


    public Entrada() {
        bufferCar = new String[20];
        for(int i = 0; i<20; i++){
            bufferCar[i] ="vacio";
        }
        random = new Random(System.currentTimeMillis());
    }

    public synchronized void arriveCar()  {
            for(int i=0; i<bufferCar.length; i++){
                if(bufferCar[i] == "vacio") {
                    System.out.println("Entrada <-- carro "+ i  );
                    bufferCar[i] = Thread.currentThread().getName();
                    checkEntrada = i;
                    break;
                }

            }
    }

    public synchronized void leaveCar(){
            for(int i=0; i<bufferCar.length; i++){
                if(bufferCar[i].compareTo(Thread.currentThread().getName()) == 0){
                    bufferCar[i]="vacio";
                    System.out.println("-->Salida carro "+ i);
                    checkEntrada = i;
                    break;
                }
            }
    }

}
