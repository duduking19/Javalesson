package question2;

public class Question2Main {

    public static void main(String[] args) {
        Pair<String, Integer> student = new Pair<>("Alice", 95);
        Pair<Double, String> measurement = new Pair<>(36.5, "Celsius");

        System.out.println("Initial pairs:");
        System.out.println("student = " + student);
        System.out.println("measurement = " + measurement);

        student.setFirst("Bob");
        student.setSecond(88);
        measurement.setFirst(98.6);
        measurement.setSecond("Fahrenheit");

        System.out.println();
        System.out.println("After set operations:");
        System.out.println("student first  = " + student.getFirst());
        System.out.println("student second = " + student.getSecond());
        System.out.println("measurement    = " + measurement);
    }
}

class Pair<F, S> {
    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
