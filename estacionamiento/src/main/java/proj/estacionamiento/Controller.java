package proj.estacionamiento;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import proj.estacionamiento.Clases.*;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;



public class Controller implements Observer {

    private Auto auto;
    private int a,b,c = 0;
    private Image imageCar;
    ImageView [] Autos = new ImageView[20];


    @FXML
    private AnchorPane canvas;


    @FXML
    private File file;


    @FXML
    public void initialize(){
        for(int y=0; y<20; y++){

            Autos[y] = new ImageView();
            if(y<5){
                Autos[y].setLayoutX(136+ (68*y)); Autos[y].setLayoutY(391);
                Autos[y].setFitHeight(66); Autos[y].setFitWidth(46);
                canvas.getChildren().add(Autos[y]);

            }
            if(y>4 && y<10){
                Autos[y].setLayoutX(136+ (68*a)); Autos[y].setLayoutY(266);
                Autos[y].setFitHeight(66); Autos[y].setFitWidth(46);
                canvas.getChildren().add(Autos[y]);
                a++;
            }
            if(y>9 && y <15){
                Autos[y].setLayoutX(136+ (68*b)); Autos[y].setLayoutY(166);
                Autos[y].setFitHeight(66); Autos[y].setFitWidth(46);
                canvas.getChildren().add(Autos[y]);
                b++;
            }
            if(y>14 && y <20){
                Autos[y].setLayoutX(136+ (68*c)); Autos[y].setLayoutY(60);
                Autos[y].setFitHeight(66); Autos[y].setFitWidth(46);
                canvas.getChildren().add(Autos[y]);
                c++;
            }
            Autos[y].setVisible(false);
        }


    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Auto){
            ImageView valor = Autos[Entrada.checkEntrada];
            if((Integer)arg == 1){
                try {
                    Thread.sleep(50);
                    Platform.runLater(()->{
                        valor.setVisible(true);
                        valor.setImage(((Auto)o).getImage());
                    });
                    valor.setVisible(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else{
                Platform.runLater(() ->{
                    valor.setVisible(false);
                });

            }
        }
    }
    @FXML
    void OnMouseClicked(MouseEvent event){
        Semaphore mutex = new Semaphore(0);
        Semaphore Puerta = new Semaphore(1);
        Entrada entrada = new Entrada();
        Auto[] autos = new Auto[100];
        File file = new File("src/main/java/proj/estacionamiento/rec/carro.png");
        imageCar = new Image(file.toURI().toString());
        for (int i = 0; i < 100; i++) {
            auto = new Auto(mutex, Puerta, entrada, imageCar);
            auto.addObserver(this);
            autos[i] = auto;
        }
        Thread autoNuevo = new Thread(new crearAuto(autos));
        autoNuevo.start();

    }


}