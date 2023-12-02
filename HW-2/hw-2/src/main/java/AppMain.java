import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppMain {
    /**
     * Создайте абстрактный класс "Animal" с полями "name" и "age".
     * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
     * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
     * Выведите на экран информацию о каждом объекте.
     * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
     */
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Animal[] animals = new Animal[5];
        animals[0] = new Dog("Polkan", 5, "shepherd", true);
        animals[1] = new Cat("Vasyka", 3, "tri-color", false);
        animals[2] = new Dog("Sharik", 2, "dvorteryer", true);
        animals[3] = new Cat("Leshiy", 5, "grey", true);
        animals[4] = new Dog("Dingo", 5, "dingo", false);

        for (Animal animal : animals) {
            Class<?> animalClass = animal.getClass();

            System.out.println("\n==================\nName = " + animal.name);
            System.out.println("Age = " + animal.age);
            Field[] fields = animalClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.printf("%s: %s\n", field.getName(), field.get(animal));
            }

            try {
                Method getSoundsMethod = animalClass.getDeclaredMethod("makeSound");
                getSoundsMethod.invoke(animal);
            } catch (NoSuchMethodException e) {
                System.out.println("There is no makeSound!");
            }

        }

    }
}
