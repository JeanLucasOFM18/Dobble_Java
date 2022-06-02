package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dobblegame.*;

public class Menu {

    Dobble datosMazo;
    DobbleGame datosJuego;

    public Menu() {
        this.datosMazo = new Dobble();
        this.datosJuego = new DobbleGame();
    }

    public Dobble getDatosMazo() {
        return datosMazo;
    }

    public void setDatosMazo(Dobble datosMazo) {
        this.datosMazo = datosMazo;
    }

    public DobbleGame getDatosJuego() {
        return datosJuego;
    }

    public void setDatosJuego(DobbleGame datosJuego) {
        this.datosJuego = datosJuego;
    }

    public void ejecutarMenu() {

        Scanner in = new Scanner(System.in);
        int cantElementos;
        int habilitador;
        int numC;
        int maxC;
        int numP;
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
                        getDatosMazo().setCantElementos(cantElementos);
                        getDatosMazo().generarLista();
                        System.out.println("Ingrese cantidad de elementos por carta: ");
                        numC = in.nextInt();
                        getDatosMazo().setNumC(numC);
                        if (datosMazo.comprobarDatos() == true) {
                            System.out.println("Ingrese cantidad de cartas que quiere generar: ");
                            maxC = in.nextInt();
                            getDatosMazo().setMaxC(maxC);
                            System.out.println("Cuantos jugadores tendra la partida?");
                            numP = in.nextInt();
                            getDatosJuego().setNumP(numP);
                            getDatosMazo().generarMazo(1);
                            getDatosJuego().setMazo(getDatosMazo());
                            System.out.println("Mazo creado con exito");
                        }
                        else {
                            getDatosMazo().senalarError();
                            getDatosMazo().setNumC(0);
                            System.out.println("Favor crear el juego nuevamente con los datos correctos");
                            getDatosMazo().getLis_elementos().removeAll(getDatosMazo().getLis_elementos());
                        }
                        break;
                case 2: getDatosJuego().registrarJugador();
                        break;
                case 3: datosJuego();
                        break;
                case 4: if(getDatosMazo().getNumC() == 0) {
                            System.out.println("Debe crear el juego para acceder a esta funcion");
                        }
                        else {
                            habilitador = getDatosMazo().calculo(getDatosMazo().getNumC());
                            if (getDatosJuego().comprobarDatos() == 0) {
                                jugar(mazo, jugadores, puntajes, getDatosMazo().getNumC(), getDatosJuego().getNumP());
                            }
                            if (getDatosJuego().comprobarDatos() == 1) {
                                System.out.println("No posee la cantidad necesaria de cartas para jugar");
                                System.out.println("Desea generar el maximo numero de cartas para poder jugar?");
                                System.out.println("1. SI");
                                System.out.println("2. NO");
                                int eleccion = in.nextInt();
                                if (eleccion == 1) {
                                    getDatosMazo().setMaxC(habilitador);
                                    getDatosMazo().generarMazo(2);
                                    getDatosJuego().setMazo(getDatosMazo());
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

    public void datosJuego() {

        if (getDatosMazo().getNumC() == 0) {
            System.out.println("Debe crear el juego para acceder a esta funcion");
        }

        else {
            Scanner in = new Scanner(System.in);
            int i = 0;
            while (i == 0) {
                System.out.println("### DATOS DEL JUEGO ###");
                System.out.println("Escoja su opcion:");
                System.out.println("1. Mazo");
                System.out.println("2. Juego");
                System.out.println("3. Volver atras");
                int opcion = in.nextInt();
                switch (opcion) {
                    case 1: opcionMazo();
                            break;
                    case 2: opcionJuego();
                            break;
                    case 3: i = 1;
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
                case 1: getDatosJuego().status();
                    break;
                case 2: getDatosJuego().whoseTurnIsIt();
                    break;
                case 3: getDatosJuego().score();
                    break;
                case 4: if(getDatosMazo().getMazo().size() < 2){
                            System.out.println("Ya no se puede seguir jugando, favor terminar el juego");
                        }
                        else{
                            getDatosJuego().voltearCartas();
                            getDatosJuego().setEstado("En juego");
                        }
                        break;
                case 5: resultado = getDatosJuego().senalarIgualdad();
                        if (resultado == 0) {
                            System.out.println(getDatosJuego().getJugadores().get(getDatosJuego().getTurno()).getNombre() + " se lleva las 2 cartas volteadas");
                            getDatosJuego().sumaPuntaje();
                            getDatosJuego().passTurn();
                            getDatosJuego().eliminarCartas();
                        }
                        if (resultado == 1) {
                            getDatosJuego().passTurn();
                            getDatosJuego().devolverAlMazo();
                            System.out.println("Cartas devueltas al mazo");
                        }
                        break;
                case 6: getDatosJuego().passTurn();
                        getDatosJuego().devolverAlMazo();
                        break;
                case 7: getDatosJuego().gameToString(jugadores, puntajes, turno, estado, mesa, numP, numC);
                        break;
                case 8: getDatosJuego().endGame();
                        getDatosJuego().setEstado("Finalizado");
                        break;
                case 9: i = 1;
                        break;
            }
        }
    }

    public void opcionMazo(){

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
                case 1: getDatosMazo().mostrarMazo();
                    break;
                case 2: getDatosMazo().dobbleGame();
                    break;
                case 3: getDatosMazo().numCards();
                    break;
                case 4: getDatosMazo().nthCard();
                    break;
                case 5: getDatosMazo().findTotalCards();
                        break;
                case 6: getDatosMazo().requiredElements();
                        break;
                case 7: getDatosMazo().missingCards();
                    break;
                case 8: getDatosMazo().cardsSetToString();
                    break;
                case 9: i = 1;
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
            System.out.println("1. Consultar lista de jugadores");
            System.out.println("2. Consultar puntajes");
            System.out.println("2. Consultar orden de los turnos");
            System.out.println("3. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: getDatosJuego().listaJugadores();
                        break;
                case 2: getDatosJuego().listaPuntajes();
                        break;
                case 3: getDatosJuego().turnos();
                        break;
                case 4: i = 1;
                        break;
            }
        }
    }
}