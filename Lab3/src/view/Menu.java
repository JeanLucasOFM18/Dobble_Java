package view;

import java.util.Scanner;
import dobblegame.*;

/**
 * Clase que simula un Menu interactivo.
 * @version 11.0.2
 * @autor: Jean Lucas Rivera
 */
public class Menu {

    private Dobble datosMazo;
    private DobbleGame datosJuego;

    public Menu() {
        this.datosMazo = new Dobble();
        this.datosJuego = new DobbleGame();
    }

    /**
     * Obtiene el datosMazo
     * @return datosMazo Si se obtiene el datosMazo
     */
    public Dobble getDatosMazo() {
        return datosMazo;
    }

    /**
     * Modifica el datosMazo
     * @param datosMazo (Dobble). Corresponde a un Objeto que contiene los atributos y métodos que tienen un mazo
     */
    public void setDatosMazo(Dobble datosMazo) {
        this.datosMazo = datosMazo;
    }

    /**
     * Obtiene el datosJuego
     * @return datosJuego Si se obtiene el datosJuego
     */
    public DobbleGame getDatosJuego() {
        return datosJuego;
    }

    /**
     * Modifica el datosJuego
     * @param datosJuego (DobbleGame). Corresponde a un Objeto que contiene los atributos y métodos que tienen un juego
     */
    public void setDatosJuego(DobbleGame datosJuego) {
        this.datosJuego = datosJuego;
    }

    /**
     * Permite ejecutar las distintas funcionalidades del menu principal
     */
    public void ejecutarMenu() {

        Scanner in = new Scanner(System.in);
        int cantElementos;
        int habilitador;
        int numC;
        int maxC;
        int numP;
        int modoJuego;

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
                case 1: // CREA UN JUEGO
                        // Se pide ingresar la cantidad de elementos para la creación del mazo
                        System.out.println("Ingrese cantidad de elementos que quiere agregar: ");
                        cantElementos = in.nextInt();
                        getDatosMazo().setCantElementos(cantElementos);
                        // Se pide al usuario que ingrese los elementos y se crea una lista
                        getDatosMazo().generarLista();
                        // Se pide al usuario ingresar la cantidad de elementos que quiere por carta
                        System.out.println("Ingrese cantidad de elementos por carta: ");
                        numC = in.nextInt();
                        getDatosMazo().setNumC(numC);
                        // Si con la cantidad de elementos por carta se puede obtener con la cantidad de elementos total dado
                        // se procede con la creación del juego
                        if (datosMazo.comprobarDatos() == true) {
                            // Se pide al usuario ingresar la cantidad de cartas que quiere generar
                            System.out.println("Ingrese cantidad de cartas que quiere generar: ");
                            maxC = in.nextInt();
                            getDatosMazo().setMaxC(maxC);
                            // Se pide seleccionar el modo de juego a crear
                            // 1. StackMode por turnos con jugadores reales
                            // 2. StackMode contra la CPU, donde se necesita solo 1 jugador real
                            System.out.println("Que modo quiere jugar?");
                            System.out.println("1. StackMode con amigos");
                            System.out.println("2. StackMode vs CPU");
                            modoJuego = in.nextInt();
                            getDatosJuego().setGameMode(modoJuego);
                            // Si selecciona el modo 1, se pide ingresar la cantidad de jugadores que tendrá el juego
                            if(getDatosJuego().getGameMode() == 1){
                                int aux = 0;
                                while(aux == 0){
                                    System.out.println("Cuantos jugadores tendra la partida?");
                                    numP = in.nextInt();
                                    if(numP > 1){
                                        getDatosJuego().setNumP(numP);
                                        aux = 1;
                                    }
                                    // El juego debe tener como mínimo 2 jugadores para crearse
                                    else{
                                        System.out.println("No puede crearse un juego con 1 jugador, favor volver a ingresar datos");
                                    }
                                }
                            }
                            // Si selecciona el modo 2, se pide ingresar los datos del único jugador real
                            else{
                                numP = 1;
                                getDatosJuego().setNumP(numP);
                                getDatosJuego().registrarJugador();
                            }
                            // Se crea el mazo con los datos dados
                            getDatosMazo().generarMazo(1);
                            getDatosJuego().setMazo(getDatosMazo());
                            System.out.println("Mazo creado con exito");
                        }
                        // En caso de no ser posible crear el mazo con la cantidad de datos dados
                        // Se debe crear el juego nuevamente, ingresando datos correctos
                        else {
                            // Se indica la cantidad de elementos que hacen falta para crear un mazo
                            getDatosMazo().senalarError();
                            getDatosMazo().setNumC(0);
                            System.out.println("Favor crear el juego nuevamente con los datos correctos");
                            getDatosMazo().getLis_elementos().removeAll(getDatosMazo().getLis_elementos());
                        }
                        break;
                case 2: // REGISTER
                        getDatosJuego().registrarJugador();
                        break;
                case 3: // ACCEDER AL SUBMENU DONDE ESTÁN LOS DATOS DEL JUEGO
                        datosJuego();
                        break;
                case 4: // Se debe crear el juego para comenzar a jugar
                        if(getDatosMazo().getNumC() == 0) {
                            System.out.println("Debe crear el juego para acceder a esta funcion");
                        }
                        else {
                            // Se obtiene la cantidad de cartas necesarias para empezar el juego
                            habilitador = getDatosMazo().calculo(getDatosMazo().getNumC());
                            // En caso de tener un set válido se accede a jugar
                            if (getDatosJuego().comprobarDatos() == 0) {
                                jugar();
                            }
                            // El mazo no es válido para iniciar el juego y se le da la opción al usuario de crear un set completo
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
                            // Faltan jugadores por registrar
                            else {
                                System.out.println("No ha registrado a la totalidad de jugadores para jugar");
                            }
                        }
                        break;
                case 5: // SALIR
                        i = 1;
                        break;
            }
        }
    }

    /**
     * Permite ejecutar las distintas funcionalidades del submenu dedicado a dar los datos del juego
     */
    public void datosJuego() {

        // En caso de no crear el juego no se puede acceder a esta seccion
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
                    case 1: // ACCEDER AL SUBMENU DEDICADO A LOS DATOS DEL MAZO
                            opcionMazo();
                            break;
                    case 2: // ACCEDER AL SUBMENU DEDICADO A LOS DATOS DE LOS JUGADORES
                            opcionJugadores();
                            break;
                    case 3: // VOLVER AL MENU PRINCIPAL
                            i = 1;
                            break;
                }
            }
        }

    }

    /**
     * Permite ejecutar las distintas funcionalidades del submenu dedicado a jugar
     */
    public void jugar() {

        Scanner in = new Scanner(System.in);

        int i = 0;
        while (i == 0) {
            System.out.println("### MENU JUEGO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Estado del juego");
            System.out.println("2. Consultar turno");
            System.out.println("3. Consultar puntajes");
            System.out.println("4. Pasar de turno");
            System.out.println("5. Jugar");
            System.out.println("6. Juego a String");
            System.out.println("7. Finalizar juego");
            System.out.println("8. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: // STATUS
                        // Se obtiene el estado del juego, puede ser "No iniciado", "En juego" y "Finalizado"
                        getDatosJuego().status();
                        break;
                case 2: // WHOSETURNISIT
                        // Se obtiene el nombre del jugador al que le pertenece el turno actual
                        getDatosJuego().whoseTurnIsIt();
                        break;
                case 3: // SCORE
                        // Obtiene el puntaje de un jugador
                        getDatosJuego().score();
                        break;
                case 4: // PASAR TURNO
                        getDatosJuego().passTurn();
                        getDatosJuego().devolverAlMazo();
                        break;
                case 5: // PLAY
                        // Se comprueban si quedan como mínimo 2 cartas en el mazo para jugar
                        if(getDatosMazo().getMazo().size() < 2){
                            // Se le solicita terminar el juego al usuario
                            System.out.println("Ya no se puede seguir jugando, favor terminar el juego");
                        }
                        else{
                            getDatosJuego().play();
                            // Se cambia el estado del juego
                            getDatosJuego().setEstado("En juego");
                        }
                        break;
                case 6: //getDatosJuego().gameToString();
                        break;
                case 7: // FINALIZAR JUEGO
                        getDatosJuego().endGame();
                        // Se cambia el estado del juego
                        getDatosJuego().setEstado("Finalizado");
                        break;
                case 8: // VOLVER ATRÁS
                        i = 1;
                        break;
            }
        }
    }

    /**
     * Permite ejecutar las distintas funcionalidades del submenu dedicado a dar los datos del mazo
     */
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
                case 1: // MOSTRAR MAZO
                        getDatosMazo().mostrarMazo();
                        break;
                case 2: // DOBBLE?
                        // Se verifica la validez del mazo
                        getDatosMazo().dobbleGame();
                        break;
                case 3: // NUMCARDS
                        // Se obtiene la cantidad de cartas que tiene el mazo creado
                        getDatosMazo().numCards();
                        break;
                case 4: // NTHCARD
                        // Se obtiene la carta ubicada en la posicion dada por el usuario (inicia en la pos 0)
                        getDatosMazo().nthCard();
                        break;
                case 5: // FINDTOTALCARDS
                        // Se obtiene la cantidad total de cartas que se necesitan a partir de una carta muestra
                        getDatosMazo().findTotalCards();
                        break;
                case 6: // REQUIREDELEMENTS
                        // Se obtiene la cantidad total de elementos que se necesitan a partir de una carta muestra
                        getDatosMazo().requiredElements();
                        break;
                case 7: // MISSINGCARDS
                        // Se obtiene las cartas faltantes para que el mazo sea válido
                        getDatosMazo().missingCards();
                        break;
                case 8: // CARDSSETTOSTRING
                        // Se obtiene una representación en base de strings del mazo
                        getDatosMazo().cardsSetToString();
                        break;
                case 9: // SE VUELVE ATRAS
                        i = 1;
                        break;
            }
        }
    }

    /**
     * Permite ejecutar las distintas funcionalidades del submenu dedicado a dar los datos de los jugadores
     */
    public void opcionJugadores(){

        Scanner in = new Scanner(System.in);
        int i = 0;
        while(i == 0){
            System.out.println("### DATOS DEL JUEGO ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Consultar lista de jugadores");
            System.out.println("2. Consultar puntajes");
            System.out.println("3. Consultar orden de los turnos");
            System.out.println("4. Volver atras");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: // MUESTRA LISTA DE JUGADORES
                        getDatosJuego().listaJugadores();
                        break;
                case 2: // MUESTRA LISTA DE PUNTAJES
                        getDatosJuego().listaPuntajes();
                        break;
                case 3: // MUESTRA ORDEN DE LOS TURNOS
                        getDatosJuego().turnos();
                        break;
                case 4: // VOLVER ATRAS
                        i = 1;
                        break;
            }
        }
    }
}