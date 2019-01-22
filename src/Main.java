public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        initAllTypes();
        System.out.println(calcValue(4,5,6,0));
        System.out.println( "Сумма значений " + (isSumBetween1020(10,7) ? "":"не ") + "лежит в диапазоне между 10 и 20");
        printIsPositiveNumber(-60);
        System.out.println(isNegativeNumber(-34));
        printHello("Алексей");
        printIsLeapYear(2020);
    }

    // Задание 2. Создать переменные всех пройденных типов данных и инициализировать их значения.
    private static void initAllTypes() {
        byte b=127;
        short sh=-13456;
        int i=500;
        long l= 223372036854775807L;
        float f=45.6f;
        double d=12.45;
        boolean bl=true;
        char ch='F';
        String str="Строка";
    }

    // Задание 3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    //где a, b, c, d – входные параметры этого метода
    private static int calcValue (int a, int b, int c, int d) {
        if (d==0) //На ноль делить нельзя
        {
            System.out.println("Вы ввели 0 для переменной d. На ноль делить нельзя.");
            // реализация некрасивая, но обработку исключений мы пока не проходили ))
            return 0;
        }
        else return a*(b+c/d);
    }

    // Задание 4. Написать метод, принимающий на вход два числа и проверяющий, что их сумма лежит
    // в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false
    private static boolean isSumBetween1020(int a, int b) {
        return (((a+b)>=10) && ((a+b)<=20));
    }

    // Задание 5. Написать метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль, положительное ли число передали или отрицательное.
    // Замечание: ноль считаем положительным числом
    private static void printIsPositiveNumber(int i) {
        System.out.println( (i>=0) ? "Число положительное" : "Число отрицательное");
    }

    // Задание 6. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число отрицательное
    private static boolean isNegativeNumber (int i) {
        return (i<0);
    }

    // Задание 7. Написать метод, которому в качестве параметра передается строка,
    // обозначающая имя. Метод должен вывести в консоль сообщение «Привет, указанное_имя!»
    private static void printHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    // Задание 8. Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный
    private static void printIsLeapYear (int i) {
        if ((i%400)==0) System.out.println("Год " + i + " високосный");
        else if ((i%100)==0) System.out.println("Год " + i + " невисокосный");
        else if ((i%4)==0) System.out.println("Год " + i + " високосный");
        else System.out.println("Год " + i + " невисокосный");
    }
}
