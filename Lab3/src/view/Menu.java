package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dobblegame.Player;
import main.Main;
import dobblegame.Dobble;
import dobblegame.Card;

public class Menu {

    Dobble datosMazo = new Dobble();
    Player datosJugadores = new Player();
    Card datosCarta = new Card();

    public void ejecutarMenu() {

        Scanner in = new Scanner(System.in);
        int cantElementos;
        int numC = 0;
        int maxC = 0;
        int numP = 0;
        List lis_elementos = new ArrayList<>();
        List mazo = new ArrayList();
        List jugadores = new ArrayList();
        List<Integer> puntajes = new ArrayList();


        int i = 0;
        while (i == 0) {
            System.out.println("### MENU PRINCIPAL ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Crear juego");
            System.out.println("2. Registrar jugador");
            System.out.println("3. Datos del juego");
            System.out.println("4. Jugar");
            System.out.println("5. Salir");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: System.out.println("Ingrese cantidad de elementos que quiere agregar: ");
                        cantElementos = in.nextInt();
                        datosMazo.setCantElementos(cantElementos);
                        lis_elementos = datosMazo.generarLista(datosMazo.getCantElementos());
                        System.out.println("Ingrese cantidad de elementos por carta: ");
                        numC = in.nextInt();
                        datosMazo.setNumC(numC);
                        System.out.println("Ingrese cantidad de cartas que quiere generar: ");
                        maxC = in.nextInt();
                        datosMazo.setMaxC(maxC);
                        System.out.println("Cuantos jugadores tendra la partida?");
                        numP = in.nextInt();
                        datosJugadores.setNumP(numP);
                        mazo = datosMazo.generarMazo(lis_elementos, datosMazo.getNumC(), datosMazo.getMaxC());
                        System.out.println("Juego creado con exito");
                        break;
                case 2: jugadores = datosJugadores.registrarJugador(jugadores, datosJugadores.getNumP());
                        puntajes.add(0);
                        break;
                case 3: datosJuego(lis_elementos, mazo, datosMazo.getMaxC(), datosMazo.getNumC(), puntajes, jugadores);
                        break;
                case 4:
                    jugar();
                    break;
                case 5:
                    i = 1;
                    break;
            }
        }
    }

    public void datosJuego(List<String> elementos, List<String> mazo, int maxC, int numC, List<Integer> puntajes, List<String> jugadores){

        if(numC == 0){
            System.out.println("Debe crear el juego para acceder a esta funcion");
        }

        else{
            Scanner in = new Scanner(System.in);
            int i = 0;
            while (i == 0) {
                System.out.println("### DATOS DEL JUEGO ###");
                System.out.println("Escoja su opcion:");
                System.out.println("1. Mazo");
                System.out.println("2. Carta");
                System.out.println("3. Jugador");
                System.out.println("4. Juego");
                System.out.println("5. Volver atras");
                int opcion = in.nextInt();
                switch (opcion) {
                    case 1: opcionMazo(elementos, mazo, maxC, numC);
                            break;
                    case 2: opcionCarta(mazo, numC);
                            break;
                    case 3: opcionJugador(puntajes, jugadores);
                            break;
                    case 4: opcionJuego();
                            break;
                    case 5: i = 1;
                            break;
                }
            }
        }

    }

    public void jugar() {

        Scanner in = new Scanner(System.in);

        int i = 0;
        while (i == 0) {
            System.out.println("### MENU JUEGO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Consultar turno");
            System.out.println("2. Ver cartas volteadas");
            System.out.println("3. Senalar igualdad");
            System.out.println("4. Pasar de turno");
            System.out.println("5. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: System.out.println("El turno es de: Juan");
                        break;
                case 2: System.out.println("Carta 1: [A,B,C] | Carta 2: [A,D,E]");
                        break;
                case 3: System.out.println("Igualdad correcta/incorrecta");
                        break;
                case 4: System.out.println("Turno saltado con exito");
                        break;
                case 5: i = 1;
                        break;
            }
        }
    }

    public void opcionMazo(List<String> elementos, List<String> mazo, int maxC, int numC){

        Scanner in = new Scanner(System.in);
        int i = 0;
        while(i == 0){
            System.out.println("### FUNCIONES SOBRE EL MAZO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Mostrar mazo");
            System.out.println("2. Verificar mazo");
            System.out.println("3. Cantidad de cartas en el mazo");
            System.out.println("4. Obtener una carta del mazo");
            System.out.println("5. Cartas faltantes en el mazo");
            System.out.println("6. Mazo a string");
            System.out.println("7. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: System.out.println("El mazo es: ");
                        datosMazo.mostrarMazo(numC, maxC, mazo);
                        break;
                case 2: datosMazo.dobbleGame(mazo, numC);
                        break;
                case 3: datosMazo.numCards(mazo, numC);
                        break;
                case 4: datosCarta.nthCard(mazo, numC);
                        break;
                case 5: datosMazo.missingCards(elementos, mazo, numC);
                        break;
                case 6: datosMazo.cardsSetToString(mazo, numC);
                        break;
                case 7: i = 1;
                        break;
            }
        }
    }

    public void opcionCarta(List<String> mazo, int numC){

        Scanner in = new Scanner(System.in);
        int i = 0;
        while(i == 0){
            System.out.println("### FUNCIONES SOBRE UNA CARTA ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Obtener una carta del mazo");
            System.out.println("2. Cantidad necesaria de cartas");
            System.out.println("3. Cantidad necesaria de elementos");
            System.out.println("4. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: datosCarta.nthCard(mazo, numC);
                        break;
                case 2: datosCarta.findTotalCards(mazo, numC);
                        break;
                case 3: datosCarta.requiredElements(mazo, numC);
                        break;
                case 4: i = 1;
                        break;
            }
        }
    }

    public void opcionJugador(List<Integer> puntajes, List<String> jugadores){

        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el nombre del jugador: ");
        String nombre = in.nextLine();
        int i = 0;
        while(i == 0){
            System.out.println("### DATOS DEL JUEGO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Consultar turno");
            System.out.println("2. Consultar puntaje");
            System.out.println("3. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: datosJugadores.whoseTurnIsIt(nombre, jugadores);
                        break;
                case 2: datosJugadores.scorePersonal(nombre, jugadores, puntajes);
                        break;
                case 3: i = 1;
                        break;
            }
        }
    }

    public void opcionJuego(){

        Scanner in = new Scanner(System.in);
        int i = 0;
        while(i == 0){
            System.out.println("### DATOS DEL JUEGO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Lista de jugadores");
            System.out.println("2. Consultar turno actual");
            System.out.println("3. Estado del juego");
            System.out.println("4. Lista de puntajes");
            System.out.println("5. Juego a String");
            System.out.println("6. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: System.out.println("Lista de Jugadores: Juan, Emilia...");
                        break;
                case 2: System.out.println("El turno actual es de Juan");
                        break;
                case 3: System.out.println("El estado del juego es: No iniciado");
                        break;
                case 4: System.out.println("Los puntajes son: X, Y, Z");
                        break;
                case 5: System.out.println("Juego visto como String");
                        break;
                case 6: i = 1;
                        break;
            }
        }
    }
}
