package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class DobbleGame {

    public void listaJugadores(List<String> jugadores){

        int i = 0;
        int j = 1;
        int largo = jugadores.size();
        String nombre;

        while(i < largo){
            nombre = jugadores.get(i);
            System.out.println("Jugador " + j + ": " + nombre);
            i = i + 1;
            j = j + 1;
        }

    }

    public void status(String estado){

        System.out.println("El estado del juego es: " + estado);
    }

    public void score(List<String> jugadores, List<Integer> puntajes){

        int i = 0;
        int j = 1;
        int largo = jugadores.size();
        String nombre;
        int puntaje;

        while(i < largo){
            nombre = jugadores.get(i);
            puntaje = puntajes.get(i);
            System.out.println("El puntaje de " + nombre + " es: " + puntaje);
            i = i + 1;
            j = j + 1;
        }

    }

    public void whoseTurnIsIt2(List<String> jugadores, int turno) {

        int i = 0;
        int largo = jugadores.size();
        String jugador;
        while (i < largo) {
            if (i == turno) {
                jugador = jugadores.get(i);
                System.out.println("El turno es de: " + jugador);
                i = largo;
            } else {
                i = i + 1;
            }
        }
    }

    public List<String> voltearCartas(List<String> mazo, int numC){

        int i = mazo.size();

        List sublista = mazo.subList(i - numC, i);
        i = i - numC;
        List sublista2 = mazo.subList(i - numC, i);

        System.out.println("Carta 1: " + sublista + " | Carta 2: " + sublista2);

        sublista.addAll(sublista2);

        return sublista;

    }

    public int passTurn(int turno, int numP){

        int turnoNuevo;

        if(turno == numP){
            turnoNuevo = 0;
        }
        else{
            turnoNuevo = turno + 1;
        }

        return turnoNuevo;
    }

    public int senalarIgualdad(List<String> mazo, int turno, List<String> jugadores, List<Integer> puntajes, int numC){

        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre = in.nextLine();
        String nombreTurno = jugadores.get(turno);
        int comparacion;

        if(equals(nombre, nombreTurno) == true){
            System.out.println("Ingrese la coincidencia encontrada: ");
            String coincidencia = in.nextLine();
            comparacion = validarcoincidencia(coincidencia, mazo, numC);
            if(comparacion == 0){
                System.out.println("Coincidencia correcta");
                return 0;
            }
            else{
                System.out.println("Coincidencia incorrecta");
                return 1;
            }
        }
        else{
            System.out.println("No es el turno de " + nombre);
            return 1;
        }
    }

    public int validarcoincidencia(String coincidencia, List<String> mazo, int numC){

        int i = mazo.size();

        List sublista = mazo.subList(i - numC, i);
        i = i - numC;
        List sublista2 = mazo.subList(i - numC, i);

        i = 0;
        int j = 0;
        String coincidenciaCorrecta = "";
        String elemento;
        String elemento2;
        while(i < numC){
            elemento = (String) sublista.get(i);
            while(j < numC){
                elemento2 = (String) sublista2.get(j);
                if(equals(elemento, elemento2) == true){
                    coincidenciaCorrecta = elemento2;
                    j = j + 1;
                }
                else{
                    j = j + 1;
                }
            }
            j = 0;
            i = i + 1;
        }

        if(equals(coincidencia, coincidenciaCorrecta)){
            return 0;
        }
        else{
            return 1;
        }
    }

    public List<Integer> sumaPuntaje(int turno, List<Integer> puntajes){

        int i = 0;
        int largo = puntajes.size();

        while(i < largo){
            if(i == turno){
                int puntos = puntajes.get(i);
                puntajes.remove(i);
                puntajes.add(i, puntos + 2);
                i = largo;
            }
            else{
                i = i + 1;
            }
        }

        return puntajes;
    }

    public List<String> eliminarCartas(List<String> mazo, int numC){

        int i = mazo.size() - 1;
        int j = 0;
        int k = numC * 2;

        while(j < k){
            mazo.remove(i);
            i = i - 1;
            j = j + 1;
        }

        return mazo;
    }

    public List<String> devolverAlMazo(List<String> mazo, int numC){

        int i = mazo.size() - 1;
        int j = 0;
        int k = numC * 2;
        List<String> cartas = new ArrayList();

        while(j < k){
            String elemento = mazo.get(i);
            cartas.add(elemento);
            mazo.remove(i);
            i = i - 1;
            j = j + 1;
        }

        j = 0;
        i = cartas.size() - 1;
        while(j < k){
            String elemento2 = cartas.get(i);
            mazo.add(j, elemento2);
            cartas.remove(i);
            i = i - 1;
            j = j + 1;
        }

        return mazo;
    }

    public void gameToString(List<String> jugadores, List<Integer> puntajes, int turno, String estado, List<String> mesa, int numP, int numC){

        int i = 0;
        int j = 1;
        String nombre;

        System.out.println("# ESTADO DEL JUEGO #");
        System.out.println(estado);

        System.out.println("# JUGADORES #");
        while(i < numP){
            int puntaje = puntajes.get(i);
            nombre = jugadores.get(i);
            System.out.println("Jugador " + j + ": " + nombre + " tiene " + puntaje + " en su poder");
            j = j + 1;
            i = i + 1;
        }

        System.out.println("# TURNO #");
        System.out.println("El turno es de: " + jugadores.get(turno));

        System.out.println("# MESA DE JUEGO #");
        List sublista = mesa.subList(i, i + numC);
        i = i + numC;
        List sublista2 = mesa.subList(i, i + numC);
        System.out.println("Carta 1: " + sublista + " ! Carta 2: " + sublista2);

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
