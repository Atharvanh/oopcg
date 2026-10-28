public class Practical05 {
    static int add(int a, int b) { return a + b; }
    static double add(double a, double b) { return a + b; }
    static int add(int a, int b, int c) { return a + b + c; }

    static class Animal {
        void sound() { System.out.println("Animal makes a sound"); }
    }

    static class Dog extends Animal {
        @Override
        void sound() { System.out.println("Dog barks"); }
    }

    public static void main(String[] args) {
        System.out.println("Method overloading:");
        System.out.println(add(2, 3));
        System.out.println(add(2.5, 3.5));
        System.out.println(add(1, 2, 3));
        System.out.println("Method overriding:");
        Animal animal = new Dog();
        animal.sound();
        System.out.println("Built-in + with numbers: " + (2 + 3));
        System.out.println("Built-in + with strings: " + ("Hello " + "Java"));
        System.out.println("Java does not allow user-defined operator overloading");
    }
}
