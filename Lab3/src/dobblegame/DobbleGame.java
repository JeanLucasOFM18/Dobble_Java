package dobblegame;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import dobblegame.Dobble;

public class DobbleGame {

    private Dobble mazo;
    private List<Player> jugadores;
    private int numP;
    private String estado;
    private int turno;

    public DobbleGame() {
        this.jugadores = new ArrayList<Player>();
        this.numP = 0;
        this.estado = "No iniciado";
        this.turno = 0;
        this.mazo = new Dobble();
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Dobble getMazo() {
        return mazo;
    }

    public void setMazo(Dobble mazo) {
        this.mazo = mazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Player> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Player> jugadores) {
        this.jugadores = jugadores;
    }

    public int getNumP() {
        return numP;
    }

    public void setNumP(int numP) {
        this.numP = numP;
    }

    public void registrarJugador(){

        int k = 0;
        if(getNumP() == 0 && k == 0){
            System.out.println("Debe crear el juego para acceder a esta funcion");
            k = 1;
        }

        Scanner in = new Scanner(System.in);
        int largo = getJugadores().size();

        if(largo == getNumP() && k == 0){
            System.out.println("Ya se ha registrado la cantidad maxima de jugadores");
            k = 1;
        }

        if(largo < getNumP() && k == 0){
            System.out.println("Ingrese el nombre del jugador: ");
            String nombre = in.nextLine();
            int i = 0;
            int j = 0;
            while(i < largo){
                String nombre2 = getJugadores().get(i).getNombre();
                if(equals(nombre, nombre2) == true){
                    System.out.println("Ya existe un jugador con ese nombre");
                    i = largo;
                    j = 1;
                }
                else{
                    i = i + 1;
                }
            }
            if(j == 0){
                Player jugador = new Player(nombre, 0);
                List<Player> listaJugadores = getJugadores();
                listaJugadores.add(jugador);
                setJugadores(listaJugadores);
                System.out.println("Registro exitoso");
            }
        }
    }

    public int comprobarDatos(){

        int largo = getJugadores().size();
        int numC = getMazo().getNumC();
        int cartasNecesarias = getMazo().calculo(numC);

        if(getMazo().getMaxC() == cartasNecesarias && getNumP() == largo){
            return 0;
        }
        else{
            if(getMazo().getMaxC() != cartasNecesarias){
                return 1;
            }
            else{
                return 2;
            }
        }
    }

    public void listaJugadores(){

        int i = 0;
        int j = 1;
        int largo = getJugadores().size();


        while(i < largo){
            System.out.println("Jugador " + j + ": " + getJugadores().get(i).getNombre());
            i = i + 1;
            j = j + 1;
        }

    }

    public void listaPuntajes(){

        int i = 0;
        int j = 1;
        int largo = getJugadores().size();


        while(i < largo){
            System.out.println(getJugadores().get(i).getNombre() + ": " + getJugadores().get(i).getPuntaje());
            i = i + 1;
            j = j + 1;
        }

    }

    public void turnos(){

        int largo = getJugadores().size();
        int i = 0;
        int j = 1;
        while(i < largo){
            System.out.println(j + ". " + getJugadores().get(i).getNombre());
            j = j + 1;
            i = i + 1;
        }

    }

    public void status(){

        System.out.println("El estado del juego es: " + getEstado());
    }

    public void score(){

        int i = 0;
        int j = 1;
        int largo = getJugadores().size();

        while(i < largo){
            System.out.println("El puntaje de " + getJugadores().get(i).getNombre() + " es: " + getJugadores().get(i).getPuntaje());
            i = i + 1;
            j = j + 1;
        }

    }

    public void whoseTurnIsIt() {

        int i = 0;
        int largo = getJugadores().size();
        while (i < largo) {
            if (i == getTurno()) {
                System.out.println("El turno es de: " + getJugadores().get(i).getNombre());
                i = largo;
            }
            else {
                i = i + 1;
            }
        }
    }

    public void voltearCartas(){

        int i = getMazo().getMazo().size() - 1;
        int j = i - 1;

        System.out.println("Carta 1: " + getMazo().getMazo().get(i).getCarta() + " | Carta 2: " + getMazo().getMazo().get(j).getCarta());

    }

    public void passTurn(){

        int turnoNuevo;

        if(getTurno() == getNumP() - 1){
            turnoNuevo = 0;
        }

        else{
            turnoNuevo = getTurno() + 1;
        }

        setTurno(turnoNuevo);
        System.out.println("Turno saltado con exito");

    }

    public int senalarIgualdad(){

        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre = in.nextLine();
        String nombreTurno = getJugadores().get(getTurno()).getNombre();
        int comparacion;

        if(equals(nombre, nombreTurno) == true){
            System.out.println("Ingrese la coincidencia encontrada: ");
            String coincidencia = in.nextLine();
            comparacion = validarcoincidencia(coincidencia);
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
            return 2;
        }
    }

    public int validarcoincidencia(String coincidencia){

        int k = getMazo().getMazo().size() - 1;
        int m = k - 1;


        int i = 0;
        int j = 0;
        String coincidenciaCorrecta = "";
        String elemento;
        String elemento2;
        while(i < getMazo().getNumC()){
            elemento = getMazo().getMazo().get(k).getCarta().get(i);
            while(j < getMazo().getNumC()){
                elemento2 = getMazo().getMazo().get(m).getCarta().get(j);
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

        if(equals(coincidencia, coincidenciaCorrecta) == true){
            return 0;
        }
        else{
            return 1;
        }
    }

    public void sumaPuntaje(){

        int i = 0;
        int largo = getJugadores().size();

        while(i < largo){
            if(i == getTurno()){
                int puntos = getJugadores().get(i).getPuntaje();
                puntos = puntos + 2;
                getJugadores().get(i).setPuntaje(puntos);
                i = largo;
            }
            else{
                i = i + 1;
            }
        }

    }

    public void eliminarCartas(){

        int i = getMazo().getMazo().size() - 1;
        int j = 0;

        while(j < 2){
            getMazo().getMazo().remove(i);
            i = i - 1;
            j = j + 1;
        }

    }

    public void devolverAlMazo(){

        int i = getMazo().getMazo().size() - 1;

        Card carta = getMazo().getMazo().get(i);
        Card carta2 = getMazo().getMazo().get(i-1);

        getMazo().getMazo().remove(i);
        getMazo().getMazo().remove(i-1);

        getMazo().getMazo().add(0, carta);
        getMazo().getMazo().add(1, carta2);

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

        i = 0;
        System.out.println("# MESA DE JUEGO #");
        List sublista = mesa.subList(i, i + numC);
        i = i + numC;
        List sublista2 = mesa.subList(i, i + numC);
        System.out.println("Carta 1: " + sublista + " | Carta 2: " + sublista2);

    }

    public void endGame(){

        List<Integer> puntajes = new ArrayList<>();
        int largo = getJugadores().size();
        int i = 0;
        while(i < largo){
            puntajes.add(getJugadores().get(i).getPuntaje());
            i = i + 1;
        }

        int puntajeMax = Collections.max(puntajes);

        int contador = 0;
        int posicion = 0;
        i = 0;
        while(i < getNumP()){
            if(getJugadores().get(i).getPuntaje() == puntajeMax){
                contador = contador + 1;
                posicion = i;
            }
            i = i + 1;
        }

        if(contador != 1){
            System.out.println("La partida terminó en empate");
        }
        else{
            System.out.println("El ganador es: " + getJugadores().get(posicion).getNombre());
        }

        System.out.println("# POSICIONES FINALES #");

        i = 0;
        int j = 0;
        while(i < getNumP()){
            int k = i + 1;
            while(j < getNumP()){
                if(getJugadores().get(j).getPuntaje() == puntajeMax){
                    System.out.println(k + ". " + getJugadores().get(j).getNombre() + " con " + getJugadores().get(j).getPuntaje() + " puntos");
                }
                j = j + 1;
            }
            j = 0;
            i = i + 1;
            if(puntajeMax == 0){
                i = getNumP();
            }
            else{
                puntajeMax = puntajeMax - 2;
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