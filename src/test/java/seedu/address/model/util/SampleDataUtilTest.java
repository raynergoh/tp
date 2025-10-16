package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;


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
