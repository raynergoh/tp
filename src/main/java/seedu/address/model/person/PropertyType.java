package seedu.address.model.person;

/**
 * Represents the type of property associated with a person.
 * This is an enum type with fixed values:
 * HDB, CONDO, and LANDED.
 * Ensures type safety and validation of property types.
 */
public enum PropertyType {
    HDB,
    CONDO,
    LANDED;


    /**
     * Returns true if the given string is a valid property type.
     * Comparison is case-insensitive.
     *
     * @param test String to test.
     * @return true if valid property type, false otherwise.
     */
    public static boolean isValidPropertyType(String test) {
        if (test == null) {
            return false;
        }
        try {
            PropertyType.valueOf(test.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return name();
    }
}
