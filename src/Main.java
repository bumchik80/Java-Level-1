
import java.util.Scanner;

public class Main {

    public final static int SIZE=3; //Размер поля
    public final static int WIN_SUM=3; //Длина цепочки для победы
    public final static int CELL_SIZE=5; //Размер ячейки - это менять не надо, не относится к правилам игры, нужно для отрисовки
    public final static int VALUE_X=1; // значение, фиксирующее ход человека (Крестик)
    public final static int VALUE_O=2; // значение, фиксирующее ход AI (Нолик)
    public final static int VALUE_S=0; // значение, фиксирующее пустую ячейку
    public static int[] map= new int[SIZE*SIZE]; // Игровое поле
    public static final String COLOR_DEFAULT = "\u001B[0m"; //Цвет поля по умолчанию
    public static final String COLOR_BLUE = "\u001B[34m";   //Цвет крестика
    public static final String COLOR_YELLOW = "\u001B[33m"; //Цвет нолика

    public final static char[] CELL_VOID = {'.','_','_','_','.',
                                            '|',' ',' ',' ','|',
                                            '|',' ','$','&','|',
                                            '|',' ',' ',' ','|',
                                            '.','=','=','=','.'};
    public final static char[] CELL_0 = {' ','0','0','0',' ',
                                         '0',' ',' ',' ','0',
                                         '0',' ',' ',' ','0',
                                         '0',' ',' ',' ','0',
                                         ' ','0','0','0',' '};
    public final static char[] CELL_X = {'X',' ',' ',' ','X',
                                         ' ','X',' ','X',' ',
                                         ' ',' ','X',' ',' ',
                                         ' ','X',' ','X',' ',
                                         'X',' ',' ',' ','X'};

    public static Scanner scan;
    //Игра крестики-нолики
    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        scan=new Scanner(System.in);
        initMap();
        printMap();
        while (true) {
            putHuman();
            printMap();
            if (isWin( VALUE_X )) {
                System.out.println("Поздравляем! Вы победили!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
            System.out.println("Ход искусственного интеллекта");
            putAI();
            printMap();
            if (isWin( VALUE_O )) {
                System.out.println("Победил искусственный интеллект!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }

        }
        scan.close();
    }

    private static void initMap() {
        for (int i=0; i<map.length; i++) map[i]=VALUE_S;
    }

    private static void printMap() {
        for (int y = 0; y < SIZE; y++) {
            for (int cy = 0; cy < CELL_SIZE; cy++) {
                for (int x = 0; x < SIZE; x++) {
                    for (int cx = 0; cx < CELL_SIZE; cx++) {
                        switch (map[y*SIZE+x]) {

                            case 1:
                                System.out.print( COLOR_BLUE + (CELL_X[cy*CELL_SIZE+cx] == '$' ? ""+(y*SIZE+x) : CELL_X[cy*CELL_SIZE+cx] ) + COLOR_DEFAULT);
                                break;
                            case 2:
                                System.out.print( COLOR_YELLOW + CELL_0[cy*CELL_SIZE+cx] + COLOR_DEFAULT);
                                break;
                            default: {
                                if (y * SIZE + x + 1>9) {
                                    System.out.print(COLOR_DEFAULT + (CELL_VOID[cy * CELL_SIZE + cx] == '$' ? "" + (y * SIZE + x + 1)/10 : (CELL_VOID[cy * CELL_SIZE + cx] == '&' ?  "" + (y * SIZE + x + 1)%10 : CELL_VOID[cy * CELL_SIZE + cx])) + COLOR_DEFAULT);
                                }
                                else System.out.print(COLOR_DEFAULT + (CELL_VOID[cy * CELL_SIZE + cx] == '$' ? "" + (y * SIZE + x + 1) : ( CELL_VOID[cy * CELL_SIZE + cx] == '&' ? ' ' : CELL_VOID[cy * CELL_SIZE + cx])) + COLOR_DEFAULT);
                            }
                        }
                    }
                }
                System.out.printf("\n");
            }
        }
    }


    private static void putHuman () {
        int x ;
        do {
            try {
                System.out.printf ("Введите номер ячейки от 1 до %d ", SIZE*SIZE);
                 x = scan.nextInt();
                }
            catch (Exception ex) {
                x = -1;
                System.out.println("Вводить можно только числа");
                }
            }
        while  (!isNumberValid(x));
        map[x-1] = VALUE_X;
    }

    private static boolean isNumberValid (int number) {
        if (number < 1 || number > SIZE*SIZE)       return false;
        return (map[number-1] == 0);
    }

    private static boolean isWin (int number) {
        for (int i = 0; i < map.length; i++) {
            int horizotnal=0, vertical=0; int diagonal=0;
            //Проверка вертикали
            for (int j = i; j < map.length; j+=SIZE) {
                if (map[j] == number) vertical++;
                if (vertical>=WIN_SUM) return true;
            }
            //Проверка горизонтали
            for (int j = 0; j < SIZE-(i%SIZE) ; j++) {
                if (map[i+j] == number) horizotnal++;
             if (horizotnal >= WIN_SUM) return true;
            }
            //Проверка 1-й диагонали
            for (int j=i; j<map.length; j+=(SIZE+1)) {
                if (map[j] == number) diagonal++;
                if (diagonal >= WIN_SUM) return true;
            }
            diagonal=0;
            //Проверка 2-й диагонали
            int x = i%SIZE;
            int y = i/SIZE;
            while (x>=0 && y<SIZE) {
                if (map[y*SIZE+x] == number) diagonal++;
                if (diagonal >= WIN_SUM) return true;
                x--; y++;
            }
        }
        return false;
    }

    private static boolean isMapFull () {
        for (int i=0; i<map.length; i++) {
            if (map[i] == VALUE_S) return false;
        }
        return true;
    }

    private static void putAI() {
        int[] m=new int[SIZE*SIZE];
        for (int i=0; i<map.length; i++) m[i]=0;
        for (int i=0; i<map.length; i++) {
            //Вычисляем вес ячейки
            if (map[i]!=VALUE_S) continue; //Занятая ячейка - ценности не имеет
            m[i]++; // Ячейка непустая - за это один балл
            //Определим, вес ячейки по вертикали
            int value_x=0, value_o=0;
            for (int v = (i+SIZE)%SIZE; v < map.length; v += SIZE) {
                if ((map[v] != VALUE_S) && ( v!=i )) {
                    if (map[v] == VALUE_O) value_o++;
                    if (map[v] == VALUE_X) value_x++;
                }
            }
            int value=(value_o > value_x ? value_o : (value_x > value_o ? value_x: value_x));
            m[i]+= (value==2 ? value*2 : value);
            // Определим вес ячейки по горизонтали
            value_x=0; value_o=0;
            for (int h=0 ; h < SIZE; h++) {
                if (map[(i-i%SIZE)+h]!=VALUE_S && h!=i) {
                    if (map[h] == VALUE_O) value_o++;
                    if (map[h] == VALUE_X) value_x++;
                }
            }
            value=(value_o > value_x ? value_o : (value_x > value_o ? value_x: value_x));
            m[i]+= (value==2 ? value*2 : value);
            // Определим вес ячейки по диагонали 1
            value_x=0; value_o=0;
            int x = i%SIZE;
            int y = i/SIZE;
            int sum=0;
                while (x > 0 && y > 0) {
                    x--;
                    y--;
                    sum++;
                }
            if ( (SIZE-y) >= WIN_SUM) {
                for (int d = (y * SIZE + x); d < map.length; d += (SIZE+1)) {
                    if (map[d] != VALUE_S && (y * SIZE + x) != i) {
                        if (map[d] == VALUE_O) value_o++;
                        if (map[d] == VALUE_X) value_x++;
                    }
                }
                value=(value_o > value_x ? value_o : (value_x > value_o ? value_x: value_x));
                m[i]+= (value==2 ? value*2 : value);
            }


            // Определим вес ячейки по диагонали 2
            value_x=0; value_o=0;
            x = i%SIZE;
            y = i/SIZE;
            sum=0;
            while (x < SIZE && y > 0) {
                x++;
                y--;
                sum++;
            }
            if ( (x+1) >= WIN_SUM) {
                while (x>=0 && y<SIZE) {
                    if (map[y*SIZE+x] != VALUE_S && (y * SIZE + x) != i) {
                        if (map[y*SIZE+x] == VALUE_O) value_o++;
                        if (map[y*SIZE+x] == VALUE_X) value_x++;
                    }
                    x--; y++;
                }
                value=(value_o > value_x ? value_o : (value_x > value_o ? value_x: value_x));
                m[i]+= (value==2 ? value*2 : value);
            }

        }
        int max=0;
        for (int i = 0; i<map.length; i++) {
            if (m[i] > m[max]) max=i;
        }
        map[max]=VALUE_O;
    }

}
