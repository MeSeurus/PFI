package ru.gb.lesson_1;

public class Rectangle extends Figure {
    int a;
    int b;

    public Rectangle (int x, int y) {
        a = x;
        b = y;
    }

    public double Area() {
        return a*b;
    }

    public void printArea(){
        System.out.println(Area());
    };
}
