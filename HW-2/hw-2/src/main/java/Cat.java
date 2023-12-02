public class Cat extends Animal {
    String color;
    boolean wild;

    public Cat(String name, int age, String color, boolean wild) {
        super(name, age);
        this.color = color;
        this.wild = wild;
    }

    public void Speak() {
        System.out.println("Mrrrr...");
    }
}
