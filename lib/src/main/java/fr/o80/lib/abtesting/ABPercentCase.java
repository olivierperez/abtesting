package fr.o80.lib.abtesting;

/**
 * @author Olivier Perez
 */
class ABPercentCase {

    private final int lower;
    private final int upper;
    private final String value;

    ABPercentCase(int lower, int upper, String value) {
        this.lower = lower;
        this.upper = upper;
        this.value = value;
    }

    boolean matches(int value) {
        return lower <= value && value <= upper;
    }

    public String value() {
        return value;
    }
}
