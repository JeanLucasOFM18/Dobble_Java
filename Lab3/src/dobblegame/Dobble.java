package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Dobble {

    private int numC;
    private int maxC;
    private List<String> lis_elementos = new ArrayList<>();

    public int getNumC() {
        return numC;
    }

    public void setNumC(int numC) {
        this.numC = numC;
    }

    public int getMaxC() {
        return maxC;
    }

    public void setMaxC(int maxC) {
        this.maxC = maxC;
    }


    public List<String> getLis_elementos() {
        return lis_elementos;
    }

    public void setLis_elementos(List<String> lis_elementos) {
        this.lis_elementos = lis_elementos;
    }

}
