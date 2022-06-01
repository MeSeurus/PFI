package ru.gb.lesson_1;

public class Main {
    public static void main(String[] args) {

        //Task1

        Person person = new PersonBuilder("Mike")
                .setLastName("Myers")
                .setCountry("USA")
                .build();
        System.out.println(person);

        //Task3

        Figure [] figures = new Figure[3];

        figures[0] = new Circle(1);
        figures[1] = new Rectangle(2,2);
        figures[2] = new RightAngledTriangle(2,2);

        for (int i = 0; i < figures.length; i++) {
            figures[i].printArea();
        }
    }
}
