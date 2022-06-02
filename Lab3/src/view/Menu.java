package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dobblegame.*;

public class Menu {

    Dobble datosMazo = new Dobble();
    //Player datosJugadores = new Player();
    //Card datosCarta = new Card();
    DobbleGame datosJuego = new DobbleGame();

    public void ejecutarMenu() {

        Scanner in = new Scanner(System.in);
        int cantElementos;
        int habilitador;
        int numC;
        int maxC;
        int numP;
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
                        datosMazo.generarLista();
                        System.out.println("Ingrese cantidad de elementos por carta: ");
                        numC = in.nextInt();
                        datosMazo.setNumC(numC);
                        if (datosMazo.comprobarDatos() == true) {
                            System.out.println("Ingrese cantidad de cartas que quiere generar: ");
                            maxC = in.nextInt();
                            datosMazo.setMaxC(maxC);
                            System.out.println("Cuantos jugadores tendra la partida?");
                            numP = in.nextInt();
                            datosJuego.setNumP(numP);
                            datosMazo.generarMazo();
                            System.out.println("Mazo creado con exito");
                        }
                        else {
                            datosMazo.senalarError();
                            datosMazo.setNumC(0);
                            System.out.println("Favor crear el juego nuevamente con los datos correctos");
                        }
                        break;
                case 2: datosJuego.registrarJugador();
                        break;
                case 3: datosJuego(lis_elementos, mazo, datosMazo.getMaxC(), datosMazo.getNumC(), puntajes, jugadores);
                    break;
                case 4: if(datosMazo.getNumC() == 0) {
                    System.out.println("Debe crear el juego para acceder a esta funcion");
                }
                else {
                    habilitador = datosMazo.calculo(datosMazo.getNumC());
                    if (datosJuego.comprobarDatos(datosMazo.getMaxC(), habilitador, datosJuego.getNumP(), jugadores) == 0) {
                        jugar(mazo, jugadores, puntajes, datosMazo.getNumC(), datosJuego.getNumP());
                    }
                    if (datosJuego.comprobarDatos(datosMazo.getMaxC(), habilitador, datosJuego.getNumP(), jugadores) == 1) {
                        System.out.println("No posee la cantidad necesaria de cartas para jugar");
                        System.out.println("Desea generar el maximo numero de cartas para poder jugar?");
                        System.out.println("1. SI");
                        System.out.println("2. NO");
                        int eleccion = in.nextInt();
                        if (eleccion == 1) {
                            datosMazo.setMaxC(habilitador);
                            //mazo = datosMazo.generarMazo(lis_elementos, datosMazo.getNumC(), datosMazo.getMaxC());
                            System.out.println("Mazo generado con exito");
                        }
                    }
                    else {
                        System.out.println("No ha registrado a la totalidad de jugadores para jugar");
                    }
                }
                    break;
                case 5: i = 1;
                    break;
            }
        }
    }

    public void datosJuego(List<String> elementos, List<String> mazo, int maxC, int numC, List<Integer> puntajes, List<String> jugadores) {

        if (datosMazo.getNumC() == 0) {
            System.out.println("Debe crear el juego para acceder a esta funcion");
        }

        else {
            Scanner in = new Scanner(System.in);
            int i = 0;
            while (i == 0) {
                System.out.println("### DATOS DEL JUEGO ###");
                System.out.println("Escoja su opcion:");
                System.out.println("1. Mazo");
                System.out.println("2. Jugador");
                System.out.println("3. Juego");
                System.out.println("4. Volver atras");
                int opcion = in.nextInt();
                switch (opcion) {
                    case 1: opcionMazo(elementos, mazo, maxC, numC);
                        break;
                    case 2: opcionJugador(puntajes, jugadores);
                        break;
                    case 3: opcionJuego(mazo, jugadores, puntajes);
                        break;
                    case 4: i = 1;
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
                case 4: if(mazo.size() < numC * 2){
                    System.out.println("Ya no se puede seguir jugando, favor terminar el juego");
                }
                else{
                    mesa = datosJuego.voltearCartas(mazo, numC);
                    estado = "Iniciado";
                }
                    break;
                case 5: resultado = datosJuego.senalarIgualdad(mazo, turno, jugadores, puntajes, numC);
                    if (resultado == 0) {
                        System.out.println(jugadores.get(turno) + " se lleva las 2 cartas volteadas");
                        puntajes = datosJuego.sumaPuntaje(turno, puntajes);
                        turno = datosJuego.passTurn(turno, numP);
                        mazo = datosJuego.eliminarCartas(mazo, numC);
                    }
                    if (resultado == 1) {
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
                case 8: datosJuego.endGame(jugadores, puntajes, numP);
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
            System.out.println("5. Cantidad necesaria de cartas");
            System.out.println("6. Cantidad necesaria de elementos");
            System.out.println("7. Cartas faltantes en el mazo");
            System.out.println("8. Mazo a string");
            System.out.println("9. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: datosMazo.mostrarMazo();
                    break;
                case 2: datosMazo.dobbleGame();
                    break;
                case 3: datosMazo.numCards();
                    break;
                case 4: datosMazo.nthCard();
                    break;
                case 5: datosMazo.findTotalCards();
                        break;
                case 6: datosMazo.requiredElements();
                        break;
                case 7: datosMazo.missingCards();
                    break;
                case 8: datosMazo.cardsSetToString();
                    break;
                case 9: i = 1;
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
            System.out.println("2. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: //datosJugadores.whoseTurnIsIt(nombre, jugadores);
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
                case 2: datosJuego.turnos(jugadores);
                    break;
                case 3: i = 1;
                    break;
            }
        }
    }
}