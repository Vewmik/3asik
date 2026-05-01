package org.example;

public class MyTestingClass {
    private int identifier;
    private String description;

    public MyTestingClass(int identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + identifier;
        if (description != null) {
            for (int i = 0; i < description.length(); i++) {
                result = 37 * result + description.charAt(i);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        MyTestingClass that = (MyTestingClass) other;
        return identifier == that.identifier &&
                (description == null ? that.description == null : description.equals(that.description));
    }
}