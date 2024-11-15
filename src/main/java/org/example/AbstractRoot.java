package org.example;

public abstract class AbstractRoot {

    /*@Override
    public String toString() {
        Field fields[] = this.getClass().getDeclaredFields();
        StringBuilder result = new StringBuilder(this.getClass().getSimpleName() + " {");
        try {
            for (Field field : fields) {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) ||
                java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                result.append(field.getName())
                        .append(": ")
                        .append(field.get(this) == null ? "null" : field.get(this).toString())
                        .append(", ");
            }
        } catch (IllegalAccessException e) {
            result.append("Error accessing fields");
        }
        if (fields.length > 0) {
            result.setLength(result.length() - 2);
        }
        result.append("}");
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) ||
                java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                Object thisFieldValue = field.get(this).toString();
                Object otherFieldValue = field.get(obj).toString();
                if (!thisFieldValue.equals(otherFieldValue)) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (this == null) {
            return 0;
        }
        Field[] fields = this.getClass().getDeclaredFields();
        int result = 17;
        try {
            for (Field field : fields) {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) ||
                java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                result = 31 * result + (field.get(this) == null ? 0 : field.get(this).hashCode());
            }
        } catch (IllegalAccessException e) {
            return 0;
        }
        return result;
    }*/

}
