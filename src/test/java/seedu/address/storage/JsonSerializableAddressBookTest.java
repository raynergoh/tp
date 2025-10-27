package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GROUP_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GROUP_PROPERTY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.tag.TagGroup;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path TYPICAL_TAG_GROUPS_FILE = TEST_DATA_FOLDER.resolve("typicalTagGroupsAddressBook.json");
    private static final Path DUPLICATE_TAG_GROUP_FILE = TEST_DATA_FOLDER.resolve("duplicateTagGroupAddressBook.json");

    @Test
    public void toModelType_typicalTagGroupsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TAG_GROUPS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();

        // Verify tag groups are loaded
        assertTrue(addressBookFromFile.hasTagGroup(new TagGroup(VALID_TAG_GROUP_PROPERTY)));
        assertTrue(addressBookFromFile.hasTagGroup(new TagGroup(VALID_TAG_GROUP_LOCATION)));
        assertEquals(2, addressBookFromFile.getTagGroups().size());
    }

    @Test
    public void toModelType_duplicateTagGroups_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TAG_GROUP_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_TAG_GROUP,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_emptyTagGroups_success() throws Exception {
        // Create address book with no tag groups
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(ALICE);

        JsonSerializableAddressBook jsonAddressBook = new JsonSerializableAddressBook(addressBook);
        AddressBook deserializedAddressBook = jsonAddressBook.toModelType();

        // Verify no tag groups
        assertTrue(deserializedAddressBook.getTagGroups().isEmpty());
        assertEquals(1, deserializedAddressBook.getPersonList().size());
    }


    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
