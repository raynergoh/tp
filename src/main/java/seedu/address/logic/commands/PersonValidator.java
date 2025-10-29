package seedu.address.logic.commands;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Utility class for validating person data before add/edit operations.
 * Performs duplicate checks for name, phone numbers and email addresses.
 */
public class PersonValidator {
    public static final String MESSAGE_DUPLICATE_EMAIL = "This email address already exists in the address book";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_DUPLICATE_PHONE = "This phone number already exists in the address book";

    private static final Logger logger = LogsCenter.getLogger(PersonValidator.class);

    /**
     * Validates that a person can be added to the address book.
     * Checks for duplicate name, phone number and email address.
     *
     * @param model The model containing the address book data.
     * @param person The person to validate.
     * @throws CommandException If the person already exists,
     *                          or if the phone number or email is already used by another person.
     */
    public static void validatePersonForAdd(Model model, Person person) throws CommandException {
        validateNoDuplicatePerson(model, person);
        validateNoDuplicatePhone(model, person);
        validateNoDuplicateEmail(model, person);
    }

    /**
     * Validates that an edited person can be saved to the address book.
     * Only checks for duplicates if the relevant field has changed from the original person.
     *
     * @param model The model containing the address book data.
     * @param personToEdit The original person before editing.
     * @param editedPerson The person with edited details.
     * @throws CommandException If the edited person conflicts with an existing person,
     *                          or if the phone number or email (when changed) is already used by another person.
     */
    public static void validatePersonForEdit(Model model, Person personToEdit, Person editedPerson)
            throws CommandException {
        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            logger.warning("EditCommand failed: Duplicate person detected.");
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!personToEdit.getPhone().equals(editedPerson.getPhone())
                && model.hasSamePhoneNumber(editedPerson)) {
            logger.warning("EditCommand failed: Duplicate phone number detected.");
            throw new CommandException(MESSAGE_DUPLICATE_PHONE);
        }

        if (!personToEdit.getEmail().equals(editedPerson.getEmail())
                && model.hasSameEmail(editedPerson)) {
            logger.warning("EditCommand failed: Duplicate email address detected.");
            throw new CommandException(MESSAGE_DUPLICATE_EMAIL);
        }
    }

    /**
     * Validates that the person does not already exist in the address book.
     */
    private static void validateNoDuplicatePerson(Model model, Person person)
            throws CommandException {
        if (model.hasPerson(person)) {
            logger.warning("AddCommand failed: Duplicate person detected.");
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
    }

    /**
     * Validates that the phone number is not used by another person.
     */
    private static void validateNoDuplicatePhone(Model model, Person person)
            throws CommandException {
        if (model.hasSamePhoneNumber(person)) {
            logger.warning("AddCommand failed: Duplicate phone number detected.");
            throw new CommandException(MESSAGE_DUPLICATE_PHONE);
        }
    }

    /**
     * Validates that the email address is not used by another person.
     */
    private static void validateNoDuplicateEmail(Model model, Person person)
            throws CommandException {
        if (model.hasSameEmail(person)) {
            logger.warning("AddCommand failed: Duplicate email detected.");
            throw new CommandException(MESSAGE_DUPLICATE_EMAIL);
        }
    }
}
