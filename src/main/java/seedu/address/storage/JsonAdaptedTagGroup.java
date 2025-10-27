package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.TagGroup;

/**
 * Jackson-friendly version of TagGroup.
 */
public class JsonAdaptedTagGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TagGroup's %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedTagGroup} with the given tag group name.
     */
    @JsonCreator
    public JsonAdaptedTagGroup(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code TagGroup} into this class for Jackson use.
     */
    public JsonAdaptedTagGroup(TagGroup source) {
        this.name = source.getName().toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag group object into the model's {@code TagGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag group.
     */
    public TagGroup toModelType() throws IllegalValueException {
        if (name == null || name.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        return new TagGroup(name);
    }
}
