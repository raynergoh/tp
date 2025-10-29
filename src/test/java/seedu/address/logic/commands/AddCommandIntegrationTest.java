package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_sameNameDifferentContact_success() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        // Person with same name but different phone and email should be allowed
        Person personWithSameName = new PersonBuilder()
                .withName(personInList.getName().fullName)
                .withPhone("99999999")
                .withEmail("unique@example.com")
                .build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(personWithSameName);

        assertCommandSuccess(new AddCommand(personWithSameName), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(personWithSameName)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePhone_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        Person personWithDuplicatePhone = new PersonBuilder()
                .withName("Different Name")
                .withPhone(personInList.getPhone().value)
                .withEmail("different@example.com")
                .build();
        assertCommandFailure(new AddCommand(personWithDuplicatePhone), model,
                PersonValidator.MESSAGE_DUPLICATE_PHONE);
    }

    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        Person personWithDuplicateEmail = new PersonBuilder()
                .withName("Different Name")
                .withPhone("99999999")
                .withEmail(personInList.getEmail().value)
                .build();
        assertCommandFailure(new AddCommand(personWithDuplicateEmail), model,
                PersonValidator.MESSAGE_DUPLICATE_EMAIL);
    }
}
