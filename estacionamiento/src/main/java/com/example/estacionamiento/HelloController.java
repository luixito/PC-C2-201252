package com.example.estacionamiento;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.estacionamiento.Espacio;

import java.util.Observable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observer;



public class HelloController implements Observer {
    @FXML
    private AnchorPane rootScene;
    private Espacio esp;
    private Circle v2;

    @FXML
    public void setCuadricula(){
        int x =20;
        int y =20;
        int id = 0;
        for (int i=0; i<25; i++) {
            for (int o=0; o<17; o++) {
                esp = new Espacio(id,x,y,false);
                System.out.println("esp x"+esp.getX());
                System.out.println("esp y"+esp.getY());
                System.out.println(x);
                System.out.println(y);
                System.out.println(id);
                id=id+1;
                v2 = new Circle(20, Color.BLACK);
                v2.setLayoutX(x);
                v2.setLayoutY(y);
                rootScene.getChildren().addAll(v2);

                if ( o==16){
                    y=20;
                }else{
                    y = y+40;
                }
            }
            x = x+40;
            new Thread(esp).start();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}