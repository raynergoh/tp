package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's status in the address book.
 * Stub class To be implemented
 */
public class Status {
    // TODO: To be implemented
    public final String value;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        value = status;
    }
}
