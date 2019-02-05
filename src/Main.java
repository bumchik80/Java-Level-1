public class Main {

    public static void main(String[] args) {
        Emp[] emps= new Emp[] { new Emp("Иванов Иван Иванович", "Бухгалтер", "lgh1@mail.ru", "4954566778", 35, 100000.34 ),
                new Emp("Петров Алексей ", "Экономист", "lgh2@mail.ru", "4954566771", 39, 70000.66 ),
                new Emp("Григорьвев Пётр Петрович", "Директор", "lgh3@mail.ru", "4954566774", 36, 120000.4 ),
                new Emp("Замуруйка Артём Фёдорович", "Заместитель директора", "lgh4@mail.ru", "4954566772", 23, 110000.1 ),
                new Emp("Кочеткова Агнесса Игоревна", "Товаровед", "lgh5@mail.ru", "4954566776", 45, 300000 )};
        for (Emp emp : emps) {
            if (emp.getAge() > 40) {
                emp.printEmp();            }
        }

    }


}
