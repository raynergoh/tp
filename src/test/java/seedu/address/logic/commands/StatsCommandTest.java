package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests and unit tests for StatsCommand.
 */
public class StatsCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_emptyAddressBook_showsZeroStats() {
        Model emptyModel = new ModelManager();
        StatsCommand statsCommand = new StatsCommand();

        CommandResult result = statsCommand.execute(emptyModel);

        String expectedMessage = "Status Statistics:\n"
                + "- Pending: 0 contact(s)\n"
                + "- Completed: 0 contact(s)\n"
                + "- No Status: 0 contact(s)\n"
                + "Total: 0 contact(s)";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_typicalAddressBook_showsCorrectStats() {
        StatsCommand statsCommand = new StatsCommand();

        CommandResult result = statsCommand.execute(model);

        // Count the actual statuses in typical address book
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

        String expectedMessage = String.format("Status Statistics:\n"
                + "- Pending: %d contact(s)\n"
                + "- Completed: %d contact(s)\n"
                + "- No Status: %d contact(s)\n"
                + "Total: %d contact(s)",
                pendingCount, completedCount, noStatusCount, totalCount);

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_allPending_showsCorrectStats() {
        Model modelWithAllPending = new ModelManager();
        modelWithAllPending.addPerson(new PersonBuilder().withStatus(Status.PENDING).build());
        modelWithAllPending.addPerson(new PersonBuilder().withName("Bob").withPhone("98765432")
                .withEmail("bob@example.com").withStatus(Status.PENDING).build());

        StatsCommand statsCommand = new StatsCommand();
        CommandResult result = statsCommand.execute(modelWithAllPending);

        String expectedMessage = "Status Statistics:\n"
                + "- Pending: 2 contact(s)\n"
                + "- Completed: 0 contact(s)\n"
                + "- No Status: 0 contact(s)\n"
                + "Total: 2 contact(s)";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_allCompleted_showsCorrectStats() {
        Model modelWithAllCompleted = new ModelManager();
        modelWithAllCompleted.addPerson(new PersonBuilder().withStatus(Status.COMPLETED).build());
        modelWithAllCompleted.addPerson(new PersonBuilder().withName("Bob").withPhone("98765432")
                .withEmail("bob@example.com").withStatus(Status.COMPLETED).build());

        StatsCommand statsCommand = new StatsCommand();
        CommandResult result = statsCommand.execute(modelWithAllCompleted);

        String expectedMessage = "Status Statistics:\n"
                + "- Pending: 0 contact(s)\n"
                + "- Completed: 2 contact(s)\n"
                + "- No Status: 0 contact(s)\n"
                + "Total: 2 contact(s)";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_allNoStatus_showsCorrectStats() {
        Model modelWithNoStatus = new ModelManager();
        modelWithNoStatus.addPerson(new PersonBuilder().withoutStatus().build());
        modelWithNoStatus.addPerson(new PersonBuilder().withName("Bob").withPhone("98765432")
                .withEmail("bob@example.com").withoutStatus().build());

        StatsCommand statsCommand = new StatsCommand();
        CommandResult result = statsCommand.execute(modelWithNoStatus);

        String expectedMessage = "Status Statistics:\n"
                + "- Pending: 0 contact(s)\n"
                + "- Completed: 0 contact(s)\n"
                + "- No Status: 2 contact(s)\n"
                + "Total: 2 contact(s)";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_mixedStatuses_showsCorrectStats() {
        Model modelWithMixed = new ModelManager();
        modelWithMixed.addPerson(new PersonBuilder().withStatus(Status.PENDING).build());
        modelWithMixed.addPerson(new PersonBuilder().withName("Bob").withPhone("98765432")
                .withEmail("bob@example.com").withStatus(Status.COMPLETED).build());
        modelWithMixed.addPerson(new PersonBuilder().withName("Charlie").withPhone("87654321")
                .withEmail("charlie@example.com").withoutStatus().build());

        StatsCommand statsCommand = new StatsCommand();
        CommandResult result = statsCommand.execute(modelWithMixed);

        String expectedMessage = "Status Statistics:\n"
                + "- Pending: 1 contact(s)\n"
                + "- Completed: 1 contact(s)\n"
                + "- No Status: 1 contact(s)\n"
                + "Total: 3 contact(s)";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void equals() {
        StatsCommand statsCommand1 = new StatsCommand();
        StatsCommand statsCommand2 = new StatsCommand();

        // same object -> returns true
        assertEquals(statsCommand1, statsCommand1);

        // different objects, same type -> returns true
        assertEquals(statsCommand1, statsCommand2);

        // different types -> returns false
        assertEquals(false, statsCommand1.equals(1));

        // null -> returns false
        assertEquals(false, statsCommand1.equals(null));
    }
}
