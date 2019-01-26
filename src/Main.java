public class Main {

    public static void main(String[] args) {

        replaceArrayValue();
        setArrayValue();
        mulArrayValue();
        setArrayDiagonal();
        findMinMaxArrayValue ();
        //int[] ar = {2, 2, 2, 1, 2, 2, 10,20};
        //int[] ar = {1};
        int[] ar = {1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14};
        System.out.println(checkBalance(ar)? "Баланс": "Нет баланса");

        offsetArrayValue(ar,3);


    }

    // 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
    private static void replaceArrayValue() {
        int[] ar = {1,1,1,0,0,0,1,0,1,0,0,1,1};
        for (int i=0; i<ar.length; i++) {
            ar[i]=(ar[i]==1? 0 :1);
        }
    }

    // 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    private static void setArrayValue () {
        int[] ar=new int[8];
        for (int i=0, x=0; i<ar.length; i++) {
            ar[i]=x;
            x+=3;
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }

    //  3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    private static void mulArrayValue() {
        int[] ar={1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i=0; i<ar.length; i++) {
            if (ar[i]<6) ar[i]*=2;
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }

    // 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    private static void setArrayDiagonal() {
        int[][] ar = new int[5][5];
        for (int i=0; i<ar.length; i++) {
            ar[i][i]=1;             //Диагональ слева-направо
            ar[i][ar.length-i-1]=1; //Диагональ справо-налево
        }
    }

    // 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета)
    private static void findMinMaxArrayValue () {
        int[] ar = {3,56,67,23,5,6,-8,9,4,456,34,67,8,9,4,6,5,3,2,0,3,6};
        int minelement=0, maxelement=0; // для хранения номеров элементов массива
        int min=ar[0], max=ar[0];       // для хранения самих значений
        for (int i=1; i<ar.length; i++) {
            if (ar[i]>max) { max=ar[i]; maxelement=i; } // Можно не запоминать и не выводить сами значения - тогда сравнивали бы со значением сохранённого номера элемента, а не с min и max
            if (ar[i]<min) { min=ar[i]; minelement=i; }
        }
        System.out.println("Минимальный элемент: " + minelement + "-й. Его значение: " + min);
        System.out.println("Максимальный элемент: " + maxelement + "-й. Его значение: " + max);
    }

    // 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
    // checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
    private static boolean checkBalance(int ar[]) {
        int sumleft=0;
        int sum=0;
        for (int a : ar) {
            sum=+a;
        }
        for (int i=0; i<ar.length; i++) {
            sumleft=+ar[i];
            if (sum-sumleft==sumleft) return true;
        }
        return false;
    }

    // 7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
    // при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    private static void offsetArrayValue (int[] ar, int n) {
        int s=ar.length;
        n=n%s;
        for (int j=0; j< (n<0? -n :n); j++){ // изворот чтобы не использовать Math.abs
            int f=ar[0],l=ar[s-1];
            for (int i=0; i<s-1; i++) {
                if (n>0) {
                    ar[i]=ar[i+1];
                }
                else {
                    ar[s-1-i]=ar[s-1-i-1];
                }
            }
            if (n>0) ar[s-1]=f;
            else ar[0]=l;
        }
        System.out.println("Массив после сдвига:");
        for (int i=0; i<ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
        /*int a=n, b=s;
        while (a!=0) {
            b=b%a;
            int c=a;
            a=b;
            b=c;
        }
        int d=b;
        for (int i=0; i<d; ++i) {
            int tmp=ar[i];
            int id=(i+n)%s;
            while (id!=i) {
                int nxt=ar[id];
                ar[id]=tmp;
                tmp=nxt;
                id=(id+n)%s;
            }
            ar[i]=tmp;
        }*/

    }
}
