package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("y"), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand("y"), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_withoutConfirmation_showsWarning() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ClearCommand clearCommand = new ClearCommand(null);

        CommandResult result = clearCommand.execute(model);

        assertEquals(ClearCommand.MESSAGE_CONFIRMATION_REQUIRED, result.getFeedbackToUser());
        // Verify that the address book was NOT cleared
        assertEquals(getTypicalAddressBook().getPersonList(), model.getAddressBook().getPersonList());
    }

    @Test
    public void execute_withYesConfirmation_clearsAddressBook() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand("y"), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_withNoConfirmation_cancels() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ClearCommand clearCommand = new ClearCommand("n");

        CommandResult result = clearCommand.execute(model);

        assertEquals(ClearCommand.MESSAGE_CANCELLED, result.getFeedbackToUser());
        // Verify that the address book was NOT cleared
        assertEquals(getTypicalAddressBook().getPersonList(), model.getAddressBook().getPersonList());
    }

    @Test
    public void equals() {
        ClearCommand clearCommandY = new ClearCommand("y");
        ClearCommand clearCommandN = new ClearCommand("n");
        ClearCommand clearCommandNull = new ClearCommand(null);

        // same object -> returns true
        assertTrue(clearCommandY.equals(clearCommandY));

        // same values -> returns true
        ClearCommand clearCommandYCopy = new ClearCommand("y");
        assertTrue(clearCommandY.equals(clearCommandYCopy));

        // both null -> returns true
        ClearCommand clearCommandNullCopy = new ClearCommand(null);
        assertTrue(clearCommandNull.equals(clearCommandNullCopy));

        // different types -> returns false
        assertFalse(clearCommandY.equals(1));

        // null -> returns false
        assertFalse(clearCommandY.equals(null));

        // different confirmation input -> returns false
        assertFalse(clearCommandY.equals(clearCommandN));
        assertFalse(clearCommandY.equals(clearCommandNull));
    }

}
