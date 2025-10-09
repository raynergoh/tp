package seedu.address.logic.parser;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * prefix (any token starting with the pattern "x/") begins or when the tokens end.
     *
     * @param tokens the array of input tokens
     * @param prefix the prefix to look for (e.g. r/, s/, t/, b/)
     * @return a list of extracted keywords with the prefix removed
     */
    private List<String> extractKeywords(String[] tokens, String prefix) {
        return Arrays.stream(tokens)
                .dropWhile(token -> !token.startsWith(prefix)) // skip until this prefix is found
                .takeWhile(token -> !isPrefixToken(token, prefix)) // collect until another prefix starts
                .map(token -> token.substring(prefix.length())) // strip prefix from collected tokens
                .collect(Collectors.toList());
    }

    /**
     * Returns true if the given token is a prefix (e.g. starts with any pattern like x/)
     * and is not the same as the current prefix.
     *
     * @param token  the token to check
     * @param prefix the current prefix being processed
     * @return true if the token represents a different prefix, false otherwise
     */
    private boolean isPrefixToken(String token, String prefix) {
        return token.matches(".+/") && !token.startsWith(prefix);
    }
}

