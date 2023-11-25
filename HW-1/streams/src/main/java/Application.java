import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class Application {
    /*
    Напишите программу, которая использует Stream API для обработки списка чисел.
    Программа должна вывести на экран среднее значение всех четных чисел в списке.
     */
    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        System.out.print("{");
        Arrays.stream(array).forEach(x -> System.out.printf(" %d", x));
        System.out.println(" }");

        System.out.print("Average of evens = ");
        System.out.println(Arrays.stream(array)
                .filter(x -> x % 2 == 0)
                .mapToInt(x -> x)
                .average().orElse(0));
    }
}
