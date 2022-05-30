package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dobblegame.*;

public class Menu {

    Dobble datosMazo = new Dobble();
    Player datosJugadores = new Player();
    Card datosCarta = new Card();
    DobbleGame datosJuego = new DobbleGame();

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
                case 4: jugar(mazo, jugadores, puntajes, datosMazo.getNumC(), datosJugadores.getNumP());
                        break;
                case 5: i = 1;
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
                    case 4: opcionJuego(mazo, jugadores, puntajes);
                            break;
                    case 5: i = 1;
                            break;
                }
            }
        }

    }

    public void jugar(List<String> mazo, List<String> jugadores, List<Integer> puntajes, int numC, int numP) {

        Scanner in = new Scanner(System.in);
        int turno = 0;
        int resultado;
        String estado = "No iniciado";
        List mesa = new ArrayList();

        int i = 0;
        while (i == 0) {
            System.out.println("### MENU JUEGO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Estado del juego");
            System.out.println("2. Consultar turno");
            System.out.println("3. Consultar puntajes");
            System.out.println("4. Ver cartas volteadas");
            System.out.println("5. Senalar igualdad");
            System.out.println("6. Pasar de turno");
            System.out.println("7. Juego a String");
            System.out.println("8. Finalizar juego");
            System.out.println("9. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: datosJuego.status(estado);
                        break;
                case 2: datosJuego.whoseTurnIsIt2(jugadores, turno);
                        break;
                case 3: datosJuego.score(jugadores, puntajes);
                        break;
                case 4: mesa = datosJuego.voltearCartas(mazo, numC);
                        estado = "Iniciado";
                        break;
                case 5: resultado = datosJuego.senalarIgualdad(mazo, turno, jugadores, puntajes, numC);
                        if(resultado == 0){
                            System.out.println(jugadores.get(turno) + " se lleva las 2 cartas volteadas");
                            puntajes = datosJuego.sumaPuntaje(turno, puntajes);
                            turno = datosJuego.passTurn(turno, numP);
                            mazo = datosJuego.eliminarCartas(mazo, numC);
                        }
                        else{
                            turno = datosJuego.passTurn(turno, numP);
                            mazo = datosJuego.devolverAlMazo(mazo, numC);
                            System.out.println("Cartas devueltas al mazo");
                        }
                        break;
                case 6: turno = datosJuego.passTurn(turno, numP);
                        System.out.println("Turno saltado con exito");
                        break;
                case 7: datosJuego.gameToString(jugadores, puntajes, turno, estado, mesa, numP, numC);
                        break;
                case 8: // datosJuego.endGame(jugadores, puntajes);
                        estado = "Finalizado";
                        break;
                case 9: i = 1;
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
            System.out.println("### DATOS DEL JUGADOR ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Consultar turno");
            System.out.println("3. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: datosJugadores.whoseTurnIsIt(nombre, jugadores);
                        break;
                case 2: i = 1;
                        break;
            }
        }
    }

    public void opcionJuego(List<String> mazo, List<String> jugadores, List<Integer> puntajes){

        Scanner in = new Scanner(System.in);
        int i = 0;
        while(i == 0){
            System.out.println("### DATOS DEL JUEGO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Lista de jugadores");
            System.out.println("2. Consultar orden de los turnos");
            System.out.println("3. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: datosJuego.listaJugadores(jugadores);
                        break;
                case 2: System.out.println("El turno actual es de Juan");
                        break;
                case 3: i = 1;
                        break;
            }
        }
    }
}
