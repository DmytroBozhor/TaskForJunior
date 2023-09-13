package org.example.automatization;

public class LoadingCrane {

    public static void main(String[] args) {
        int nDisks = 3;
        load(nDisks, 'A', 'B', 'C');
    }

    public static void load(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Диск 1 от " + from + " до " + to);
        } else {
            load(topN - 1, from, to, inter);
            System.out.println("Диск " + topN + " от " + from + " до " + to);
            load(topN - 1, inter, from, to);
        }
    }


}
