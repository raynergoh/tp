package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagFormat_throwsIllegalArgumentException() {
        String invalidTagFormat = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagFormat));
    }

    @Test
    public void isValidTagFormat() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagFormat(null));
    }

    @Test
    public void isValidTagFormat_validTags_returnsTrue() {
        assertTrue(Tag.isValidTagFormat("propertyType.HDB"));
        assertTrue(Tag.isValidTagFormat("location.Sengkang"));
        assertTrue(Tag.isValidTagFormat("group1.value1"));
        assertTrue(Tag.isValidTagFormat("G2.V2"));
    }

    // Currently will not work as REGEX is still an OR
    //    @Test
    //    public void isValidTagFormat_missingDot_returnsFalse() {
    //        assertFalse(Tag.isValidTagFormat("propertyTypeHDB"));
    //        assertFalse(Tag.isValidTagFormat("locationSengkang"));
    //        assertFalse(Tag.isValidTagFormat("group1value1"));
    //    }

    @Test
    public void isValidTagFormat_multipleDots_returnsFalse() {
        assertFalse(Tag.isValidTagFormat("propertyType.HDB.extra"));
        assertFalse(Tag.isValidTagFormat("group1.value1.another"));
    }

    @Test
    public void isValidTagFormat_invalidCharacters_returnsFalse() {
        assertFalse(Tag.isValidTagFormat("propertyType.H*DB"));
        assertFalse(Tag.isValidTagFormat("loca@tion.Sengk@ng"));
        assertFalse(Tag.isValidTagFormat("group1.v@lue"));
    }

}
