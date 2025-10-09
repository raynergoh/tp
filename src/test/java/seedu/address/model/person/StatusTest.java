package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void toggle_pending_returnsCompleted() {
        // PENDING should toggle to COMPLETED
        assertEquals(Status.COMPLETED, Status.PENDING.toggle());
    }

    @Test
    public void toggle_completed_returnsPending() {
        // COMPLETED should toggle to PENDING
        assertEquals(Status.PENDING, Status.COMPLETED.toggle());
    }

    @Test
    public void equals_sameStatus_returnsTrue() {
        // same enum value -> returns true
        assertEquals(Status.PENDING, Status.PENDING);
        assertEquals(Status.COMPLETED, Status.COMPLETED);
    }

    @Test
    public void equals_differentStatus_returnsFalse() {
        // different enum values -> returns false
        assertNotEquals(Status.PENDING, Status.COMPLETED);
        assertNotEquals(Status.COMPLETED, Status.PENDING);
    }
}
