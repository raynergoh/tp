package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Displays status statistics of all persons in the address book.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays status statistics of all contacts.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Status Statistics:\n"
            + "- Pending: %d contact(s)\n"
            + "- Completed: %d contact(s)\n"
            + "- No Status: %d contact(s)\n"
            + "Total: %d contact(s)";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        int pendingCount = 0;
        int completedCount = 0;
        int noStatusCount = 0;

        for (Person person : model.getAddressBook().getPersonList()) {
            if (person.getStatus().isEmpty()) {
                noStatusCount++;
            } else if (person.getStatus().get() == Status.PENDING) {
                pendingCount++;
            } else if (person.getStatus().get() == Status.COMPLETED) {
                completedCount++;
            }
        }

        int totalCount = pendingCount + completedCount + noStatusCount;

        String resultMessage = String.format(MESSAGE_SUCCESS,
                pendingCount, completedCount, noStatusCount, totalCount);

        return new CommandResult(resultMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StatsCommand); 
    }
}
