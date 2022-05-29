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

        if(numP == 0){
            System.out.println("Debe crear el juego para acceder a esta funcion");
            return jugadores;
        }

        Scanner in = new Scanner(System.in);
        int largo = jugadores.size();

        if(largo == numP){
            System.out.println("Ya se ha registrado la cantidad maxima de jugadores");
            return jugadores;
        }

        System.out.println("Ingrese el nombre del jugador: ");
        String nombre = in.nextLine();

        int i = 0;
        int j = 0;
        while(i < largo){
            String nombre2 = jugadores.get(i);
            if(equals(nombre, nombre2) == true){
                System.out.println("Ya existe un jugador con ese nombre");
                i = largo;
                j = 1;
            }
            else{
                i = i + 1;
            }
        }

        if(largo < numP && j == 0){
            jugadores.add(nombre);
            System.out.println("Registro exitoso");
        }

        return jugadores;
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

    public void scorePersonal(String nombre, List<String> jugadores, List<Integer> puntajes){

        int i = 0;
        int largo = jugadores.size();
        String jugador;
        while(i < largo) {
            jugador = jugadores.get(i);
            if (equals(nombre, jugador) == true){
                System.out.println("Su puntaje es: " + puntajes.get(i));
                i = largo;
            } else {
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
