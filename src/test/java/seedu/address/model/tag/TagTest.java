package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }
    //
    //    @Test
    //    public void isValidTagName_validTags_returnsTrue() {
    //        assertTrue(Tag.isValidTagName("propertyType.HDB"));
    //        assertTrue(Tag.isValidTagName("location.Sengkang"));
    //        assertTrue(Tag.isValidTagName("group1.value1"));
    //        assertTrue(Tag.isValidTagName("G2.V2"));
    //    }
    //
    //    @Test
    //    public void isValidTagName_missingDot_returnsFalse() {
    //        assertFalse(Tag.isValidTagName("propertyTypeHDB"));
    //        assertFalse(Tag.isValidTagName("locationSengkang"));
    //        assertFalse(Tag.isValidTagName("group1value1"));
    //    }
    //
    //    @Test
    //    public void isValidTagName_multipleDots_returnsFalse() {
    //        assertFalse(Tag.isValidTagName("propertyType.HDB.extra"));
    //        assertFalse(Tag.isValidTagName("group1.value1.another"));
    //    }
    //
    //    @Test
    //    public void isValidTagName_invalidCharacters_returnsFalse() {
    //        assertFalse(Tag.isValidTagName("propertyType.H*DB"));
    //        assertFalse(Tag.isValidTagName("loca@tion.Sengk@ng"));
    //        assertFalse(Tag.isValidTagName("group1.v@lue"));
    //    }

}
