package dobblegame;

import java.util.List;

/**
 * Clase que simula una Card, que contiene una lista de elementos
 * @version 11.0.2
 * @autor: Jean Lucas Rivera
 */
public class Card {

    private List<String> carta;

    public Card(List<String> elementos) {
        this.carta = elementos;
    }

    /**
     * Obtiene la carta (List<String>) con la cantidad de elementos indicado
     * @return List<String> Si se obtiene la carta
     */
    public List<String> getCarta() {
        return carta;
    }

    /**
     * Modifica la carta (List<String>) por otra
     * @param carta (List<String>). Corresponde a una nueva carta
     */
    public void setCarta(List<String> carta) {
        this.carta = carta;
    }

    /**
     * Transforma todo el contenido de una Card a String
     * @return String Si se convierte todo el contenido de una Card a String
     */
    @Override
    public String toString() {
        return "Card{" +
                "carta=" + carta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getCarta().equals(card.getCarta());
    }
}