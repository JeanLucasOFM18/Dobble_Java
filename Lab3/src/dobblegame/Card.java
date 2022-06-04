package dobblegame;

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

    @Override
    public String toString() {
        return "Card{" +
                "carta=" + carta +
                '}';
    }

}