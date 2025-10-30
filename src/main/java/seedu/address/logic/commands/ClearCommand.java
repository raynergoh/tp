package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CONFIRMATION_REQUIRED =
            "WARNING: This will delete ALL contacts permanently!\n"
            + "Are you sure? (y/n)";
    public static final String MESSAGE_CANCELLED = "Clear command cancelled.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all contacts from the address book.\n"
            + "Example: " + COMMAND_WORD;

    private final String confirmationInput;

    /**
     * Creates a ClearCommand with the specified confirmation input.
     *
     * @param confirmationInput the user's confirmation input (null, "y", "n", or other)
     */
    public ClearCommand(String confirmationInput) {
        this.confirmationInput = confirmationInput;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // If no input provided, ask for confirmation
        if (confirmationInput == null || confirmationInput.trim().isEmpty()) {
            return new CommandResult(MESSAGE_CONFIRMATION_REQUIRED);
        }

        String input = confirmationInput.trim().toLowerCase();

        // User confirmed with 'y'
        if (input.equals("y")) {
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        }

        // User cancelled with 'n' (parser ensures only y/n reach here)
        return new CommandResult(MESSAGE_CANCELLED);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ClearCommand)) {
            return false;
        }

        ClearCommand otherClearCommand = (ClearCommand) other;

        if (confirmationInput == null && otherClearCommand.confirmationInput == null) {
            return true;
        }

        if (confirmationInput == null || otherClearCommand.confirmationInput == null) {
            return false;
        }

        return confirmationInput.equals(otherClearCommand.confirmationInput);
    }
}
