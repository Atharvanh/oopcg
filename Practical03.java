public class Practical03 {
    static class Animal {
        void eat() { System.out.println("Animal eats"); }
    }

    static class Dog extends Animal {
        void bark() { System.out.println("Dog barks"); }
    }

    static class Puppy extends Dog {
        void play() { System.out.println("Puppy plays"); }
    }

    static class Cat extends Animal {
        void meow() { System.out.println("Cat meows"); }
    }

    public static void main(String[] args) {
        System.out.println("Single inheritance: Animal -> Dog");
        Dog dog = new Dog();
        dog.eat();
        dog.bark();
        System.out.println("Multilevel inheritance: Animal -> Dog -> Puppy");
        Puppy puppy = new Puppy();
        puppy.eat();
        puppy.bark();
        puppy.play();
        System.out.println("Hierarchical inheritance: Dog and Cat extend Animal");
        Cat cat = new Cat();
        cat.eat();
        cat.meow();
    }
}
