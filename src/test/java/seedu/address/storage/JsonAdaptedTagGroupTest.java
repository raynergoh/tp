package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GROUP_PROPERTY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.TagGroup;

public class JsonAdaptedTagGroupTest {
    private static final String INVALID_GROUP_NAME = "property Type"; // Contains space

    @Test
    public void toModelType_validTagGroup_returnsTagGroup() throws Exception {
        JsonAdaptedTagGroup tagGroup = new JsonAdaptedTagGroup(VALID_TAG_GROUP_PROPERTY);
        assertEquals(new TagGroup(VALID_TAG_GROUP_PROPERTY), tagGroup.toModelType());
    }

    @Test
    public void toModelType_invalidGroupName_throwsIllegalValueException() {
        JsonAdaptedTagGroup tagGroup = new JsonAdaptedTagGroup(INVALID_GROUP_NAME);
        assertThrows(IllegalValueException.class, tagGroup::toModelType);
    }

    @Test
    public void toModelType_nullGroupName_throwsIllegalValueException() {
        JsonAdaptedTagGroup tagGroup = new JsonAdaptedTagGroup((String) null);
        assertThrows(IllegalValueException.class, tagGroup::toModelType);
    }

    @Test
    public void constructor_validTagGroup_success() throws Exception {
        TagGroup tagGroup = new TagGroup(VALID_TAG_GROUP_PROPERTY);
        JsonAdaptedTagGroup jsonTagGroup = new JsonAdaptedTagGroup(tagGroup);
        assertEquals(tagGroup, jsonTagGroup.toModelType());
    }
}
