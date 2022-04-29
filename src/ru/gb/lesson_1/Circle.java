package ru.gb.lesson_1;

public class Circle extends Figure {
    int r;

    public Circle (int x) {
        r = x;
    }

    public double Area() {
        return 3.14*r*r;
    }

    public void printArea() {
        System.out.println(Area());
    }
}
