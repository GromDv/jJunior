public class Dog extends Animal {
    String kind;
    boolean dressed;

    public Dog(String name, int age, String kind, boolean dressed) {
        super(name, age);
        this.kind = kind;
        this.dressed = dressed;
    }

    public void makeSound() {
        System.out.println("Sounds: Gav!");
    }
}
