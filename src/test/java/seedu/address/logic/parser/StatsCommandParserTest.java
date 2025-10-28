package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StatsCommand;

public class StatsCommandParserTest {

    private StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_emptyArg_returnsStatsCommand() {
        assertParseSuccess(parser, "", new StatsCommand());
    }

    @Test
    public void parse_whitespaceArg_returnsStatsCommand() {
        assertParseSuccess(parser, "   ", new StatsCommand());
    }

    @Test
    public void parse_anyArg_returnsStatsCommand() {
        // Stats command ignores any arguments
        assertParseSuccess(parser, "some random text", new StatsCommand());
    }
}
