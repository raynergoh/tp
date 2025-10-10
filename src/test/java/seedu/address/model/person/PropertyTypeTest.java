package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PropertyTypeTest {

    @Test
    public void validPropertyTypeStrings() {
        assertTrue(PropertyType.isValidPropertyType("HDB"));
        assertTrue(PropertyType.isValidPropertyType("Condo"));
        assertTrue(PropertyType.isValidPropertyType("landed"));
        assertTrue(PropertyType.isValidPropertyType("Hdb")); // case insensitive check
    }

    @Test
    public void invalidPropertyTypeStrings() {
        assertFalse(PropertyType.isValidPropertyType("Apartment"));
        assertFalse(PropertyType.isValidPropertyType(""));
        assertFalse(PropertyType.isValidPropertyType(null));
        assertFalse(PropertyType.isValidPropertyType("house"));
    }

    @Test
    public void enumValueOf() {
        assertEquals(PropertyType.HDB, PropertyType.valueOf("HDB"));
        assertEquals(PropertyType.CONDO, PropertyType.valueOf("CONDO"));
        assertEquals(PropertyType.LANDED, PropertyType.valueOf("LANDED"));
    }
}
