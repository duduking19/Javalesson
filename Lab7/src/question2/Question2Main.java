package question2;

/**
 * Demonstrates a generic Pair class with different type arguments.
 */
public class Question2Main {

    public static void main(String[] args) {
        // Pair<String, Integer> can model a name-score pair.
        Pair<String, Integer> student = new Pair<>("Alice", 95);
        // Pair<Double, String> shows that the same class works for another type combination.
        Pair<Double, String> measurement = new Pair<>(36.5, "Celsius");

        System.out.println("Initial pairs:");
        System.out.println("student = " + student);
        System.out.println("measurement = " + measurement);

        // Update both pair objects to verify the setter methods.
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

/**
 * Stores two related values whose types are decided by the caller.
 *
 * @param <F> type of the first value
 * @param <S> type of the second value
 */
class Pair<F, S> {
    // The first and second positions are independent, so each gets its own type parameter.
    private F first;
    private S second;

    /**
     * Initializes the pair with two values.
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first element of the pair.
     */
    public F getFirst() {
        return first;
    }

    /**
     * Replaces the first element with a new value of the same declared type.
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * Returns the second element of the pair.
     */
    public S getSecond() {
        return second;
    }

    /**
     * Replaces the second element with a new value of the same declared type.
     */
    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        // Format the pair for concise console display in the demo program.
        return "(" + first + ", " + second + ")";
    }
}
