public class Practical02 {
    static class Student {
        int roll;
        String name;

        Student() {
            roll = 0;
            name = "Unknown";
        }

        Student(int roll, String name) {
            this.roll = roll;
            this.name = name;
        }

        Student(Student other) {
            this.roll = other.roll;
            this.name = other.name;
        }

        void display() {
            System.out.println("Roll: " + roll + ", Name: " + name);
        }
    }

    public static void main(String[] args) {
        Student first = new Student();
        Student second = new Student(1, "Amit");
        Student third = new Student(second);
        first.display();
        second.display();
        third.display();
    }
}
