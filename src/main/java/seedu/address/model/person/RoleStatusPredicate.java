package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.Objects;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s role and/or status matches any of the given keywords.
 */
public class RoleStatusPredicate implements Predicate<Person> {

    private final List<String> roleKeywords;
    private final List<String> statusKeywords;

    /**
     * Creates a predicate with the given role and status keyword lists.
     *
     * @param roleKeywords   the list of role keywords to match
     * @param statusKeywords the list of status keywords to match
     */
    public RoleStatusPredicate(List<String> roleKeywords, List<String> statusKeywords) {
        this.roleKeywords = roleKeywords;
        this.statusKeywords = statusKeywords;
    }

    /**
     * Returns true if the given {@code Person} matches the role and/or status keywords.
     * TODO: Implement the actual matching logic for role and status keywords.
     */
    @Override
    public boolean test(Person person) {
        return true;
    }


    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RoleStatusPredicate
                && roleKeywords.equals(((RoleStatusPredicate) other).roleKeywords)
                && statusKeywords.equals(((RoleStatusPredicate) other).statusKeywords));
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleKeywords, statusKeywords);
    }
}
