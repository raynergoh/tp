package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.TagGroup;
import seedu.address.testutil.PersonBuilder;

class MatchesRoleStatusTagGroupPredicateTest {

    @Test
    void test_matchesRoleOnly_returnsTrue() {
        Person person = new PersonBuilder().withRoles("buyer").build();
        Set<Role> roles = Set.of(new Role("buyer"));
        Set<Status> statuses = Set.of(Status.COMPLETED);
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person));
    }

    @Test
    void test_matchesRoleIgnoreCase_returnsTrue() {
        // Person has role "Buyer" but filter uses lowercase
        Person person = new PersonBuilder().withRoles("Buyer").build();
        Set<Role> roles = Set.of(new Role("buyer")); // lowercased
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person), "Should match regardless of role name casing");
    }

    @Test
    void test_matchesRoleDifferentCase_returnsTrue() {
        // Person has role "manager" but filter uses uppercase
        Person person = new PersonBuilder().withRoles("manager").build();
        Set<Role> roles = Set.of(new Role("MANAGER"));
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person), "Should match even if role case differs");
    }

    @Test
    void test_matchesRoleAcrossMultipleRolesIgnoreCase_returnsTrue() {
        // Multiple roles with mixed casing
        Person person = new PersonBuilder().withRoles("Admin", "Supervisor").build();
        Set<Role> roles = Set.of(new Role("admin"));
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person), "Case-insensitive role match should still return true");
    }

    @Test
    void test_noRoleMatchEvenIgnoringCase_returnsFalse() {
        // Person has unrelated roles
        Person person = new PersonBuilder().withRoles("Buyer").build();
        Set<Role> roles = Set.of(new Role("Seller"));
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertFalse(predicate.test(person), "Should return false if roles differ even ignoring case");
    }

    @Test
    void test_matchesStatusOnly_returnsTrue() {
        Person person = new PersonBuilder().withStatus(Status.COMPLETED).build();
        Set<Role> roles = Collections.emptySet();
        Set<Status> statuses = Set.of(Status.COMPLETED);
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person));
    }

    @Test
    void test_matchesRoleAndStatus_returnsTrue() {
        Person person = new PersonBuilder().withRoles("buyer").withStatus(Status.COMPLETED).build();
        Set<Role> roles = Set.of(new Role("buyer"));
        Set<Status> statuses = Set.of(Status.COMPLETED);
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person));
    }

    @Test
    void test_matchesTagGroupOnly_returnsTrue() {
        Person person = new PersonBuilder().withTags("location.downtown").build();
        Set<Role> roles = Collections.emptySet();
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Set.of(new TagGroup("location"));
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person));
    }

    @Test
    void test_matchesAnyTagGroup_returnsTrue() {
        Person person = new PersonBuilder().withTags("location.downtown", "department.sales").build();
        Set<Role> roles = Collections.emptySet();
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Set.of(new TagGroup("location"));
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertTrue(predicate.test(person));
    }

    @Test
    void test_simpleTagDoesNotMatchTagGroup_returnsFalse() {
        Person person = new PersonBuilder().withTags("urgent").build();
        Set<Role> roles = Collections.emptySet();
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Set.of(new TagGroup("location"));
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertFalse(predicate.test(person));
    }

    @Test
    void test_noMatches_returnsFalse() {
        Person person = new PersonBuilder().withRoles("buyer").withStatus(Status.PENDING).build();
        Set<Role> roles = Set.of(new Role("seller"));
        Set<Status> statuses = Set.of(Status.COMPLETED);
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertFalse(predicate.test(person));
    }

    @Test
    void test_noCriteria_returnsFalse() {
        Person person = new PersonBuilder().withRoles("buyer").build();
        Set<Role> roles = Collections.emptySet();
        Set<Status> statuses = Collections.emptySet();
        Set<TagGroup> tagGroups = Collections.emptySet();
        MatchesRoleStatusTagGroupPredicate predicate =
                new MatchesRoleStatusTagGroupPredicate(roles, statuses, tagGroups);

        assertFalse(predicate.test(person));
    }

    @Test
    void test_equals() {
        Set<Role> roles1 = Set.of(new Role("buyer"));
        Set<Status> statuses1 = Set.of(Status.COMPLETED);
        Set<TagGroup> tagGroups1 = Set.of(new TagGroup("location"));

        Set<Role> roles2 = Set.of(new Role("seller"));
        Set<Status> statuses2 = Set.of(Status.PENDING);
        Set<TagGroup> tagGroups2 = Set.of(new TagGroup("department"));

        MatchesRoleStatusTagGroupPredicate predicate1 =
                new MatchesRoleStatusTagGroupPredicate(roles1, statuses1, tagGroups1);
        MatchesRoleStatusTagGroupPredicate predicate2 =
                new MatchesRoleStatusTagGroupPredicate(roles1, statuses1, tagGroups1);
        MatchesRoleStatusTagGroupPredicate predicate3 =
                new MatchesRoleStatusTagGroupPredicate(roles2, statuses2, tagGroups2);

        // same object
        assertTrue(predicate1.equals(predicate1));

        // same values
        assertTrue(predicate1.equals(predicate2));

        // different values
        assertFalse(predicate1.equals(predicate3));

        // null
        assertFalse(predicate1.equals(null));

        // different type
        assertFalse(predicate1.equals("not a predicate"));
    }
}
