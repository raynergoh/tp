package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class RoleStatusPredicateTest {

    @Test
    void test_matchesRoleOnly_returnsTrue() {
        // TODO: Implement test once RoleStatusPredicate.test() is implemented
        // TODO: Implement PersonBuilder.withRole(String) method
        Person person = new PersonBuilder()/* .withRole("buyer") */.build();
        RoleStatusPredicate predicate = new RoleStatusPredicate(Arrays.asList("buyer"), Collections.emptyList());

        // TODO: Replace with actual test assertion
        assertTrue(predicate.test(person));
    }

    @Test
    void test_matchesStatusOnly_returnsTrue() {
        // TODO: Implement test once RoleStatusPredicate.test() is implemented
        // TODO: Implement PersonBuilder.withStatus(String) method
        Person person = new PersonBuilder()/* .withStatus("completed") */.build();
        RoleStatusPredicate predicate = new RoleStatusPredicate(Collections.emptyList(), Arrays.asList("completed"));

        // TODO: Replace with actual test assertion
        assertTrue(predicate.test(person));
    }

    @Test
    void test_matchesRoleAndStatus_returnsTrue() {
        // TODO: Implement test once RoleStatusPredicate.test() is implemented
        // TODO: Implement PersonBuilder.withRole(String) and withStatus(String)
        Person person = new PersonBuilder()/* .withRole("buyer").withStatus("completed") */.build();
        RoleStatusPredicate predicate = new RoleStatusPredicate(Arrays.asList("buyer"), Arrays.asList("completed"));

        // TODO: Replace with actual test assertion
        assertTrue(predicate.test(person));
    }

    @Test
    void test_emptyKeywords_matchesAll() {
        // TODO: Implement test once RoleStatusPredicate.test() is implemented
        // TODO: Implement PersonBuilder.withRole(String) and withStatus(String)
        Person person = new PersonBuilder()/* .withRole("anyrole").withStatus("anystatus") */.build();
        RoleStatusPredicate predicate = new RoleStatusPredicate(Collections.emptyList(), Collections.emptyList());

        // TODO: Replace with actual test assertion
        assertTrue(predicate.test(person));
    }
    @Test
    void test_equals() {
        RoleStatusPredicate predicate1 = new RoleStatusPredicate(
                Arrays.asList("buyer"), Arrays.asList("completed"));
        RoleStatusPredicate predicate2 = new RoleStatusPredicate(
                Arrays.asList("buyer"), Arrays.asList("completed"));
        RoleStatusPredicate predicate3 = new RoleStatusPredicate(
                Arrays.asList("seller"), Arrays.asList("pending"));

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

