package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Card {

    private List<String> carta;

    public Card(List<String> elementos) {
        this.carta = elementos;
    }

    public List<String> getCarta() {
        return carta;
    }

    public void setCarta(List<String> carta) {
        this.carta = carta;
    }

    public int calculo (int numC){
        int resultado = ((numC - 1) * (numC - 1)) + (numC - 1) + 1;

        return resultado;
    }

}