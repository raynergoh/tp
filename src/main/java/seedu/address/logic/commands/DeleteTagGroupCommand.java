package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TagGroup;

/**
 * Deletes an existing TagGroup from the address book.
 */
public class DeleteTagGroupCommand extends Command {

    public static final String COMMAND_WORD = "dtg";

    public static final String MESSAGE_USAGE = "To delete "
            + "a specified TagGroup from the address book, please follow the given format: "
            + COMMAND_WORD
            + " TAG_GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " location";

    public static final String MESSAGE_DELETE_SUCCESS = "TagGroup has been deleted: %1$s";
    public static final String MESSAGE_TAGGROUP_NOT_FOUND = "This TagGroup does not exist: %1$s";

    private final TagGroup toDelete;

    /**
     * Creates a DeleteTagGroupCommand to delete the specified {@code TagGroup}.
     */
    public DeleteTagGroupCommand(TagGroup toDelete) {
        requireNonNull(toDelete);
        this.toDelete = toDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTagGroup(toDelete)) {
            throw new CommandException(String.format(MESSAGE_TAGGROUP_NOT_FOUND, toDelete));
        }

        if (model.isTagGroupInUse(toDelete)) {
            throw new CommandException("Cannot delete tag group: It is still used by contacts."
                    + " Please delete all tags associated with this Tag Group first.");
        }

        model.removeTagGroup(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTagGroupCommand)) {
            return false;
        }

        DeleteTagGroupCommand otherCommand = (DeleteTagGroupCommand) other;
        return toDelete.equals(otherCommand.toDelete);
    }

    @Override
    public int hashCode() {
        return toDelete.hashCode();
    }
}
