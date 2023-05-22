import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        int c = calc.divide.apply(a, b); // переменная b равна 0

        calc.println.accept(c);
    }
}

class Calculator {
    static Supplier<Calculator> instance = Calculator::new;

    public Calculator() {
    }

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> divide = (x, y) -> {
        try {
            if (y == 0) { // проверка делителя на равенство 0
                throw new ArithmeticException("Делить на ноль нельзя!!!"); // формируем ошибку при y == 0
                }
            return x / y;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage()); // выводим сообщение в случае возникновения ошибки
            return 0;
        }
    };

    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;

    Predicate<Integer> isPositive = x -> x > 0;

    Consumer<Integer> println = System.out::println;
}

@FunctionalInterface
interface Supplier<T> {
    T get();
}