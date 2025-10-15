package seedu.address.model.util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleDataUtilTest {

    @Test
    void getSamplePersons_containNoInvalidData() {
        assertDoesNotThrow(() -> SampleDataUtil.getSamplePersons(),
                "Sample persons provided should be valid and constructible");
    }

    @Test
    void getSamplePersons_notEmpty() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertTrue(samplePersons.length > 0, "There must exist at least one sample person provided");
    }

    @Test
    void getSampleAddressBook_containNoInvalidData() {
        assertDoesNotThrow(() -> {
            var sampleAb = SampleDataUtil.getSampleAddressBook();
            assertTrue(sampleAb.getPersonList().size() > 0,
                    "Sample AddressBook should contain at least one person");
        });
    }
}
