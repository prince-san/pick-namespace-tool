public class Util {

    public static int inRange(int value, int minValue, int maxValue) {
        if (value >= minValue && value <= maxValue) {
            return value;
        } else {
            throw new IllegalArgumentException("Incorrect node values");
        }
    }
}
