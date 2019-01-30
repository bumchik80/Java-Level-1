import java.util.Random;
import java.util.Scanner;

//1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
// При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
// После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
public class Main {

    static Scanner scan;

    public static void main(String[] args) {
        System.out.println("Игра - угадай число");
        scan = new Scanner(System.in);
        int play;
        do {
            playGame();
            System.out.print("Повторить игру ещё раз? 1 - да  / 0 - нет ");
            play = scan.nextInt();
        } while (play == 1);
        scan.close();
    }

    private static void playGame () {
        final int range = 9;
        final int maxtry = 3;
        int guessnumber = getRandomNumber(range);
        int i = 1;
        boolean isWin;
        System.out.printf("Загадано число от 0 до %d \n", range);
        System.out.printf("У Вас %d попытки \n", maxtry);
        do {
            int userNumber;
            System.out.printf("Попытка %d Введите число: ", i);
            userNumber = scan.nextInt();
            isWin = checkWin(guessnumber, userNumber);
            i++;
            if ( !isWin )
                System.out.println("Загаданное число " + getTip(guessnumber,userNumber));
        } while (( i <= maxtry ) && ( !isWin ));
        if ( isWin ) {
            System.out.println("Поздравляем! Вы угадали!");
        }
        else System.out.println("Вы проиграли в этот раз");
        System.out.printf("Загаданное число: %d \n", guessnumber );
    }

    private static int getRandomNumber(int range) {
        Random random = new Random();
        return random.nextInt( range );
    }

    private static boolean checkWin(int guessnumber, int usernumber) {
        return  ( usernumber == guessnumber );
    }

    private static String getTip (int guessnumber, int usernumber) {
        return ( guessnumber < usernumber ? "меньше" : "больше" );
    }



}
