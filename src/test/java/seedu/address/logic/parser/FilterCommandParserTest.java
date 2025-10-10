package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RoleStatusPredicate;

class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("   "));
    }

    @Test
    void parse_validRoleOnly_returnsFilterCommand() throws Exception {
        FilterCommand command = parser.parse("r/buyer seller");
        RoleStatusPredicate predicate = new RoleStatusPredicate(
                Arrays.asList("buyer", "seller"),
                Arrays.asList()
        );

        assertEquals(new FilterCommand(predicate), command);
    }

    @Test
    void parse_validStatusOnly_returnsFilterCommand() throws Exception {
        FilterCommand command = parser.parse("s/completed pending");
        RoleStatusPredicate predicate = new RoleStatusPredicate(
                Arrays.asList(),
                Arrays.asList("completed", "pending")
        );

        assertEquals(new FilterCommand(predicate), command);
    }

    @Test
    void parse_roleAndStatus_returnsFilterCommand() throws Exception {
        FilterCommand command = parser.parse("r/buyer s/completed");
        RoleStatusPredicate predicate = new RoleStatusPredicate(
                Arrays.asList("buyer"),
                Arrays.asList("completed")
        );

        assertEquals(new FilterCommand(predicate), command);
    }

    @Test
    void parse_invalidPrefixOnly_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("x/unknown"));
    }
}
