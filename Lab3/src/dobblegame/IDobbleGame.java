package dobblegame;

/**
 * Interfaz de DobbleGame, las explicaciones de los métodos empleados se ubican en su implementación
 * @version 11.0.2
 * @autor: Jean Lucas Rivera
 */
public interface IDobbleGame {

    void register(String nombre);

    int comprobarDatos();

    void listaJugadores();

    void turnos();

    void status();

    void score();

    void whoseTurnIsIt();

    void voltearCartas();

    void passTurn();

    int senalarIgualdad();

    int validarCoincidencia(String coincidencia);

    void sumaPuntaje();

    void eliminarCartas();

    void devolverAlMazo();

    void endGame();

    void play(int modo);

    int vsCPUMode();

    void gameToString();

    String toString();

    boolean equals(Object o);

}
