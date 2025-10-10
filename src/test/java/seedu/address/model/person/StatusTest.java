package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    public void isValidStatus_validUppercase_returnsTrue() {
        assertTrue(Status.isValidStatus("PENDING"));
        assertTrue(Status.isValidStatus("COMPLETED"));
    }

    @Test
    public void isValidStatus_validLowercase_returnsTrue() {
        assertTrue(Status.isValidStatus("pending"));
        assertTrue(Status.isValidStatus("completed"));
    }

    @Test
    public void isValidStatus_validMixedCase_returnsTrue() {
        assertTrue(Status.isValidStatus("Pending"));
        assertTrue(Status.isValidStatus("CoMpLeTeD"));
        assertTrue(Status.isValidStatus("PeNdInG"));
    }

    @Test
    public void isValidStatus_invalidStatus_returnsFalse() {
        assertFalse(Status.isValidStatus("UNKNOWN"));
        assertFalse(Status.isValidStatus("IN_PROGRESS"));
        assertFalse(Status.isValidStatus("ACTIVE"));
        assertFalse(Status.isValidStatus(""));
    }

    @Test
    public void isValidStatus_statusWithWhitespace_returnsFalse() {
        assertFalse(Status.isValidStatus(" PENDING"));
        assertFalse(Status.isValidStatus("PENDING "));
        assertFalse(Status.isValidStatus(" PENDING "));
        assertFalse(Status.isValidStatus("COMPLE TED"));
    }

    @Test
    public void isValidStatus_nullStatus_returnsFalse() {
        assertFalse(Status.isValidStatus(null));
    }
}
