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
        int k = 0;

        int i = 0;
        while (i == 0) {
            System.out.println("### MENU PRINCIPAL ###");
            System.out.println("Escoja su opcion:");
            System.out.println("1. Crear juego");
            System.out.println("2. Registrar jugador");
            System.out.println("3. Datos del juego");
            System.out.println("4. Jugar");
            System.out.println("5. Probar método toString");
            System.out.println("6. Salir");
            System.out.println("Ingrese su opcion:");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: // CREA UN JUEGO
                        if(k == 1){
                            System.out.println("Ya se ha creado un juego");
                        }
                        else{
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
                            // Se pide al usuario ingresar la cantidad de cartas que quiere generar
                            System.out.println("Ingrese cantidad de cartas que quiere generar: ");
                            maxC = in.nextInt();
                            getDatosMazo().setMaxC(maxC);
                            // Si con los datos entregados se puede obtener un mazo se procede con la creación del juego
                            if(datosMazo.comprobarDatos()) {
                                // Se pide seleccionar el modo de juego a crear
                                // 1. StackMode por turnos con jugadores reales
                                // 2. StackMode contra la CPU, donde se necesita solo 1 jugador real
                                System.out.println("Que modo quiere jugar?");
                                System.out.println("1. StackMode con amigos");
                                System.out.println("2. StackMode vs CPU");
                                System.out.println("Ingrese su opcion:");
                                modoJuego = in.nextInt();
                                getDatosJuego().setGameMode(modoJuego);
                                in.nextLine();
                                // Si selecciona el modo 1, se pide ingresar la cantidad de jugadores que tendrá el juego
                                if(getDatosJuego().getGameMode() == 1){
                                    int aux = 0;
                                    while(aux == 0){
                                        System.out.println("Ingrese cantidad de jugadores en la partida:");
                                        numP = in.nextInt();
                                        if(numP > 1){
                                            getDatosJuego().setNumP(numP);
                                            aux = 1;
                                        }
                                        // El juego debe tener como mínimo 2 jugadores para crearse
                                        else{
                                            System.out.println("No puede crearse un juego con 1 jugador");
                                        }
                                    }
                                }
                                // Si selecciona el modo 2, se pide ingresar los datos del único jugador real
                                else{
                                    numP = 1;
                                    getDatosJuego().setNumP(numP);
                                    System.out.println("Ingrese el nombre del jugador:");
                                    String nombre = in.nextLine();
                                    getDatosJuego().register(nombre);
                                }
                                // Se crea el mazo con los datos dados
                                getDatosMazo().generarMazo(1);
                                getDatosJuego().setMazo(getDatosMazo());
                                System.out.println("Juego creado");
                                k = 1;
                            }
                            // En caso de no ser posible crear el mazo con la cantidad de datos dados
                            // Se debe crear el juego nuevamente, ingresando datos correctos
                            else {
                                // Se indica la cantidad de elementos que hacen falta para crear un mazo
                                getDatosMazo().senalarError();
                                getDatosMazo().setNumC(0);
                                getDatosMazo().setMaxC(0);
                                System.out.println("Debe crear el juego nuevamente con los datos correctos");
                                getDatosMazo().getLis_elementos().removeAll(getDatosMazo().getLis_elementos());
                            }
                        }
                        break;
                case 2: // REGISTER
                        in.nextLine();
                        if(getDatosJuego().getNumP() == 0){
                            System.out.println("Debe crear el juego para acceder a esta funcion");
                        }
                        else if(getDatosJuego().getNumP() == getDatosJuego().getJugadores().size()){
                            System.out.println("Ya se ha registrado la cantidad maxima de jugadores");
                        }
                        else{
                            System.out.println("Ingrese el nombre del jugador:");
                            String nombre = in.nextLine();
                            getDatosJuego().register(nombre);
                        }
                        break;
                case 3: // ACCEDER AL SUBMENU DONDE ESTÁN LOS DATOS DEL JUEGO
                        datosGame();
                        break;
                case 4: // Se debe crear el juego para comenzar a jugar
                        if(getDatosMazo().getNumC() == 0) {
                            System.out.println("Debe crear el juego para acceder a esta funcion");
                        }
                        else if(getDatosJuego().getEstado().equals("Finalizado")){
                            System.out.println("El juego ha terminado");
                        }
                        else {
                            // Se obtiene la cantidad de cartas necesarias para empezar el juego
                            habilitador = getDatosMazo().calculo(getDatosMazo().getNumC());
                            // En caso de tener un set válido se accede a jugar
                            if (getDatosJuego().comprobarDatos() == 0) {
                                jugar();
                                int aux = 0;
                                while(aux == 0){
                                    System.out.println("El juego ha terminado, presione 1 para salir");
                                    int salida = in.nextInt();
                                    if(salida == 1){
                                        i = 1;
                                        aux = 1;
                                    }
                                    else{
                                        System.out.println("Ingrese el numero solicitado");
                                    }
                                }
                            }
                            // El mazo no es válido para iniciar el juego y se le da la opción al usuario de crear un set completo
                            else if (getDatosJuego().comprobarDatos() == 1) {
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
                            else{
                                System.out.println("No ha registrado a la totalidad de jugadores para jugar");
                            }
                        }
                        break;

                case 5: // Se prueba la implementación del método toString
                        if(getDatosMazo().getNumC() == 0) {
                            System.out.println("Debe crear el juego para acceder a esta funcion");
                        }
                        else{
                            System.out.println("Que desea ver?");
                            System.out.println("1. DobbleGame");
                            System.out.println("2. Dobble");
                            System.out.println("3. Card");
                            System.out.println("4. Player");
                            int decision = in.nextInt();
                            if(decision == 1){
                                String juego = datosJuego.toString();
                                System.out.println(juego);
                            }
                            else if(decision == 2){
                                String mazo = datosMazo.toString();
                                System.out.println(mazo);
                            }
                            else if(decision == 3){
                                int j = 0;
                                int largo = datosMazo.getMazo().size();
                                while(j < largo){
                                    String carta = datosMazo.getMazo().get(j).toString();
                                    System.out.println(carta);
                                    j = j + 1;
                                }
                            }
                            else{
                                int j = 0;
                                int largo = datosJuego.getJugadores().size();
                                while(j < largo){
                                    String jugador = datosJuego.getJugadores().get(j).toString();
                                    System.out.println(jugador);
                                    j = j + 1;
                                }
                            }
                        }
                        break;
                case 6: // SALIR
                        i = 1;
                        break;
            }
        }
    }

    /**
     * Permite ejecutar las distintas funcionalidades del submenu dedicado a dar los datos del juego
     */
    public void datosGame() {

        // En caso de no crear el juego no se puede acceder a esta sección
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
                System.out.println("Ingrese su opcion:");
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
        int j = 0;
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
            System.out.println("Ingrese su opcion:");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: // STATUS
                        // Se obtiene el estado del juego, puede ser "No iniciado", "En juego" y "Finalizado"
                        getDatosJuego().status();
                        break;
                case 2: // WHOSETURNISIT
                        // Si el juego ya finalizó, no se puede acceder a esta función
                        if(j == 1){
                            System.out.println("Ya no se puede seguir jugando, favor terminar el juego");
                        }
                        else{
                            // Se obtiene el nombre del jugador al que le pertenece el turno actual
                            getDatosJuego().whoseTurnIsIt();
                        }
                        break;
                case 3: // SCORE
                        // Obtiene el puntaje de un jugador
                        getDatosJuego().score();
                        break;
                case 4: // PASAR TURNO
                        if(j == 1){
                            System.out.println("Ya no se puede seguir jugando, favor terminar el juego");
                        }
                        else{
                            if(getDatosJuego().getJugadores().get(getDatosJuego().getTurno()).getNombre().equals("CPU")){
                                System.out.println("No se puede saltar el turno de la CPU");
                            }
                            else{
                                getDatosJuego().passTurn();
                                getDatosJuego().devolverAlMazo();
                                System.out.println("Turno saltado con exito");
                            }
                        }
                        break;
                case 5: // PLAY
                        // Se comprueban si quedan como mínimo 2 cartas en el mazo para jugar
                        if(getDatosMazo().getMazo().size() < 2){
                            // Se le solicita terminar el juego al usuario
                            System.out.println("Ya no se puede seguir jugando, favor terminar el juego");
                            j = 1;
                        }
                        else{
                            getDatosJuego().play(getDatosJuego().getGameMode());
                            // Se cambia el estado del juego
                            getDatosJuego().setEstado("En juego");
                        }
                        break;
                case 6: // JUEGO A BASE DE STRINGS
                        if(j == 1){
                            System.out.println("Ya no se puede seguir jugando, favor terminar el juego");
                        }
                        else{
                            getDatosJuego().gameToString();
                        }
                        break;
                case 7: // FINALIZAR JUEGO
                        getDatosJuego().endGame();
                        // Se cambia el estado del juego
                        getDatosJuego().setEstado("Finalizado");
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
            System.out.println("Ingrese su opcion:");
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
            System.out.println("2. Consultar orden de los turnos");
            System.out.println("3. Volver atras");
            System.out.println("Ingrese su opcion:");
            int opcion = in.nextInt();
            switch (opcion) {
                case 1: // MUESTRA LISTA DE JUGADORES
                        getDatosJuego().listaJugadores();
                        break;
                case 2: // MUESTRA ORDEN DE LOS TURNOS
                        getDatosJuego().turnos();
                        break;
                case 3: // VOLVER ATRAS
                        i = 1;
                        break;
            }
        }
    }
}