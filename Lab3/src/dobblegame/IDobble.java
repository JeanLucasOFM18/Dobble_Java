package dobblegame;

import java.util.List;

/**
 * Interfaz de Dobble, las explicaciones de los métodos empleados se ubican en su implementación
 * @version 11.0.2
 * @autor: Jean Lucas Rivera
 */
public interface IDobble {

    void generarLista();

    boolean comprobarDatos();

    void senalarError();

    void generarMazo(int opcion);

    void firstCard();

    void nextCards();

    void lastCards();

    void mostrarMazo();

    void dobbleGame();

    void numCards();

    void nthCard();

    void findTotalCards();

    void requiredElements();

    void missingCards();

    int comparaCartas(List<String> sublista, List<String> sublista2);

    int calculo(int numC);

    String toString();

    boolean equals(Object o);

}
