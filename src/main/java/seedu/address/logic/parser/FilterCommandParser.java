package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RoleStatusPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    private static final String ROLE_PREFIX = "r/";
    private static final String STATUS_PREFIX = "s/";

    /**
     * Parses the given {@code String} of arguments in the context of the {@link FilterCommand}
     * and returns a {@link FilterCommand} object for execution.
     *
     * @param args the user input string
     * @return a {@code FilterCommand} object containing the parsed filters
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        // Split by whitespace to process each token
        String[] tokens = trimmedArgs.split("\\s+");

        List<String> roleKeywords = extractKeywords(tokens, ROLE_PREFIX);
        List<String> statusKeywords = extractKeywords(tokens, STATUS_PREFIX);

        if (roleKeywords.isEmpty() && statusKeywords.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(new RoleStatusPredicate(roleKeywords, statusKeywords));
    }

    /**
     * Extracts all keywords associated with the specified prefix from the token list.
     * Collection starts from the first occurrence of the prefix and stops when another
     * prefix (any token matching ".+/") begins or when the tokens end.
     *
     * @param tokens the array of input tokens
     * @param prefix the prefix to look for (e.g. r/, s/, t/, b/)
     * @return a list of extracted keywords with the prefix removed
     */
    private List<String> extractKeywords(String[] tokens, String prefix) {
        List<String> keywords = new java.util.ArrayList<>();
        boolean collecting = false;

        for (String rawToken : tokens) {
            String token = rawToken.trim(); // trim whitespace

            if (token.startsWith(prefix)) {
                collecting = true;
                keywords.add(token.substring(prefix.length()));
                continue;
            }

            if (collecting) {
                // stop collecting if we hit any other prefix token
                if (token.matches("^[a-z]/.*")) { // must start with letter + /
                    break; // stop BEFORE adding this token
                }
                keywords.add(token); // safe to add
            }
        }

        return keywords;
    }
}

