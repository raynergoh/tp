package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's role in the address book.
 * Stub class To be implemented
 */
public class Role {
    // TODO: To be implemented
    public final String roleName;

    /**
     * Constructs a {@code Role}.
     *
     * @param roleName A valid role name.
     */
    public Role(String roleName) {
        requireNonNull(roleName);
        this.roleName = roleName;
    }
}
