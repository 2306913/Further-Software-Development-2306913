class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }
    // Specific method for Cat
    public void meow() {
        System.out.println(name + " is meowing.");
    }
    // Overriding displayInfo method
    @Override
    public void displayInfo() {
        System.out.println("Cat's Name: " + name + ", Age: " + age);
    }
}