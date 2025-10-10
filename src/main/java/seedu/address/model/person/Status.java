package seedu.address.model.person;

/**
 * Represents the status of a {@link Person} in the address book.
 * <p>
 * A {@code Status} indicates the current progress or state associated with a person.
 * For example, in a property management context, this may represent whether
 * a transaction, agreement, or interaction with the person is still {@code PENDING}
 * or has been {@code COMPLETED}.
 * <p>
 * This enum is immutable and type-safe.
 */
public enum Status {
    /**
     * Indicates that the person’s associated task or process is still pending.
     */
    PENDING,
    /**
     * Indicates that the person’s associated task or process has been completed.
     */
    COMPLETED;

    /**
     * Returns the opposite {@code Status} of this instance.
     * <p>
     * If the current status is {@code PENDING}, this method returns {@code COMPLETED}.
     * If the current status is {@code COMPLETED}, this method returns {@code PENDING}.
     * <p>
     * This is a convenience method to easily switch between the two fixed statuses.
     *
     * @return the opposite {@code Status} of this instance
     */
    public Status toggle() {
        return this == PENDING ? COMPLETED : PENDING;
    }


}
