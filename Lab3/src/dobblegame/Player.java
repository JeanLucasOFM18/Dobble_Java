package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private int numP;

    public int getNumP() {
        return numP;
    }

    public void setNumP(int numP) {
        this.numP = numP;
    }

    public List<String> registrarJugador(List<String> jugadores, int numP){

        Scanner in = new Scanner(System.in);
        String nombre;
        int largo = jugadores.size();

        int i = 0;
        if(largo < numP && i == 0){
            System.out.println("Ingrese el nombre del jugador: ");
            nombre = in.nextLine();
            jugadores.add(nombre);
            i = 1;
        }
        if(largo == numP && i == 0){
            System.out.println("Ya se ha registrado la cantidad maxima de jugadores");
            i = 1;
        }

        if(largo != numP){
            System.out.println("Registro exitoso");
        }

        return jugadores;
    }
}
