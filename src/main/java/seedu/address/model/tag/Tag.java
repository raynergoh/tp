package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagFormat(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags should be in the format "
            + "GROUP.VALUE where both GROUP and VALUE are alphanumeric";

    /**
     * Regex to validate tags of the form GROUP.VALUE,
     * where both GROUP and VALUE contain one or more alphanumeric characters.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9]+$|^[A-Za-z0-9]+\\.[A-Za-z0-9]+$";

    public final String tagFormat;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagFormat A valid tag format, which must conform to GROUP.VALUE format.
     */
    public Tag(String tagFormat) {
        requireNonNull(tagFormat);
        checkArgument(isValidTagFormat(tagFormat), MESSAGE_CONSTRAINTS);
        this.tagFormat = tagFormat;
    }

    /**
     * Returns true if a given string is a valid tag name.
     * The valid format is "GROUP.VALUE" where GROUP and VALUE are alphanumeric.
     * @param test String to test.
     * @return true if valid format, false otherwise.
     */
    public static boolean isValidTagFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return tagFormat.equals(otherTag.tagFormat);
    }

    @Override
    public int hashCode() {
        return tagFormat.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagFormat + ']';
    }

}
