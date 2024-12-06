package org.example;

public enum Density {
    LOW(10), MEDIUM(30), HIGH(50);

    private final int percentage;

    Density(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public static Density fromString(String text) {
        switch (text.toLowerCase()) {
            case "mały":
                return LOW;
            case "średni":
                return MEDIUM;
            case "duży":
                return HIGH;
            default:
                return LOW;
        }
    }
}
