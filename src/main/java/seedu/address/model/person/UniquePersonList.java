package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique if they have a different phone number and email address from all other persons
 * in the list. Adding and updating of persons checks for duplicate phone numbers and emails to ensure uniqueness.
 * The removal of a person uses Person#equals(Object) so as to ensure that the person with exactly the same fields
 * will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePhone(Person)
 * @see Person#isSameEmail(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains a person with the same phone number or email as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return containsPhoneNumber(toCheck) || containsEmail(toCheck);
    }

    /**
     * Returns true if the list contains an equivalent phone number as the given argument.
     */
    public boolean containsPhoneNumber(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePhone);
    }

    /**
     * Returns true if the list contains an equivalent email address as the given argument.
     */
    public boolean containsEmail(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEmail);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The phone number and email of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        // Only check for duplicates if phone or email changed
        boolean phoneChanged = !target.isSamePhone(editedPerson);
        boolean emailChanged = !target.isSameEmail(editedPerson);

        if (phoneChanged || emailChanged) {
            boolean hasDuplicate = internalList.stream()
                    .filter(person -> !person.equals(target))
                    .anyMatch(person -> editedPerson.isSamePhone(person)
                            || editedPerson.isSameEmail(person));

            if (hasDuplicate) {
                throw new DuplicatePersonException();
            }
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     * Persons are considered unique if they have different phone numbers and email addresses.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePhone(persons.get(j))
                        || persons.get(i).isSameEmail(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
