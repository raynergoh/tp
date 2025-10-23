package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s role and/or status matches any of the given keywords.
 */
public class RoleStatusPredicate implements Predicate<Person> {

    private final Set<Role> roles;
    private final Set<Status> statuses;

    /**
     * Creates a predicate with the given roles and statuses.
     *
     * @param roles   the set of roles to match
     * @param statuses the set of statuses to match
     */
    public RoleStatusPredicate(Set<Role> roles, Set<Status> statuses) {
        this.roles = roles;
        this.statuses = statuses;
    }

    /**
     * Returns true if the given {@code Person} matches the roles or statuses.
     */
    @Override
    public boolean test(Person person) {
        // If no filter criteria are provided, no person should match.
        if (roles.isEmpty() && statuses.isEmpty()) {
            return false;
        }

        // A person matches if they match any of the specified roles OR any of the specified statuses.
        boolean roleMatch = !roles.isEmpty() && person.getRoles().stream()
                .anyMatch(roles::contains);
        boolean statusMatch = !statuses.isEmpty() && person.getStatus()
                .map(statuses::contains).orElse(false);

        return roleMatch || statusMatch;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RoleStatusPredicate)) {
            return false;
        }
        RoleStatusPredicate otherPredicate = (RoleStatusPredicate) other;
        return Objects.equals(roles, otherPredicate.roles)
                && Objects.equals(statuses, otherPredicate.statuses);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("roles", roles)
                .add("statuses", statuses).toString();
    }
}
