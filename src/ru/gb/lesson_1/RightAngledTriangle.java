package ru.gb.lesson_1;

public class RightAngledTriangle extends Figure {
    int a;
    int b;

    public RightAngledTriangle (int x, int y) {
        a = x;
        b = y;
    }

    public double Area() {
        return a*b/2;
    }

    public void printArea(){
        System.out.println(Area());
    };
}
