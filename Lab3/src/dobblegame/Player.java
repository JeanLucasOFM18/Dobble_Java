package dobblegame;

import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import dobblegame.DobbleGame;

public class Player {

    private String nombre;
    private Integer puntaje;

    public Player(String nombre, Integer puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public void whoseTurnIsIt(String nombre, List<String> jugadores){

        int i = 0;
        int largo = jugadores.size();
        String jugador;
        while(i < largo){
            jugador = jugadores.get(i);
            if(equals(nombre, jugador) == true){
                i = i + 1;
                System.out.println("Su posicion es la numero: " + i);
                i = largo;
            }
            else{
                i = i + 1;
            }
        }
    }

    public boolean equals(String objeto1, String objeto2){

        if(objeto1.compareTo(objeto2) == 0){
            return true;
        }

        else{
            return false;
        }
    }


}