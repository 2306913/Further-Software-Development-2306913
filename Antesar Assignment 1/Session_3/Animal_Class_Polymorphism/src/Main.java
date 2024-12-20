//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Dog spot = new Dog("Spot", 5);
        Cat garfield = new Cat("Garfield", 2);

        spot.displayInfo();
        spot.bark();

        garfield.displayInfo();
        garfield.meow();



    }
}