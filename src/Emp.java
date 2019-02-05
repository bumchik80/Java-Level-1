public class Emp {

    private String name;
    private String post;
    private String email;
    private String phone;
    private int age;
    private double salary;

    public Emp (String name, String post, String email, String phone, int age, double salary ) {
        this.name = name;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.salary = salary;
    }

    public void printEmp () {
        System.out.printf("Сотрудник: %s. Должность: %s, возраст: %d, оклад: %.2f, телефон: %s, email: %s \n", name, post, age, salary, phone, email);
    }

    public int getAge() {
        return age;
    }
}
