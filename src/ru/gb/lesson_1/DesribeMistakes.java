/**
 * package ru.gb.lesson_1;
 * <p>
 * interface Moveable {
 * void move();
 * }
 * interface Stopable {
 * void stop();
 * }
 * <p>
 * //Класс должен реализовывать интерфейсы
 * //Не все геттеры и сеттеры
 * abstract class Car {
 * //Необходимо добавить класс Engine
 * public Engine engine;
 * private String color;
 * private String name;
 * <p>
 * protected void start() {
 * System.out.println("Car starting");
 * }
 * <p>
 * //Можно вынести в качестве интерфейса
 * abstract void open();
 * <p>
 * public Engine getEngine() {
 * return engine;
 * }
 * <p>
 * public void setEngine(Engine engine) {
 * this.engine = engine;
 * }
 * <p>
 * public String getColor() {
 * return color;
 * }
 * <p>
 * public void setColor(String color) {
 * this.color = color;
 * }
 * <p>
 * public String getName() {
 * return name;
 * }
 * <p>
 * public void setName(String name) {
 * this.name = name;
 * }
 * }
 * <p>
 * class LightWeightCar extends Car implements Moveable {
 *
 * @Override void open() {
 * System.out.println("Car is open");
 * }
 * //Можно описать реализацию в интерфейсе, так как действия для обеих машин повторяются
 * @Override public void move() {
 * System.out.println("Car is moving");
 * }
 * }
 * <p>
 * //Extends + Implements
 * class Lorry extends Car, Moveable, Stopable {
 * public void move(){
 * System.out.println("Car is moving");
 * }
 * public void stop(){
 * System.out.println("Car is stop");
 * }
 * }
 **/
