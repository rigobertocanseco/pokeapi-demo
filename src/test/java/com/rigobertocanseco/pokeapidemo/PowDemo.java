package com.rigobertocanseco.pokeapidemo;

class PowDemo {
    public static void main(String[] args) {

        System.out.println(potencia(2, 3));
    }

    static int potencia(int base, int exponente) {
        if (exponente > 0)
            return producto(base, potencia(base, exponente - 1));
        else
            return 1;
    }

    static int producto(int a, int b) {
        if (b > 0)
            return (a + producto(a, b - 1));
        else
            return 0;
    }
}
