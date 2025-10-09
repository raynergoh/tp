package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class RoleStatusPredicate implements Predicate<Person> {

    private final List<String> roleKeywords;
    private final List<String> statusKeywords;

    public RoleStatusPredicate(List<String> roleKeywords, List<String> statusKeywords) {
        this.roleKeywords = roleKeywords;
        this.statusKeywords = statusKeywords;
    }

    @Override
    public boolean test(Person person) {
        return true;    //to implement
    }

    @Override
    public boolean equals(Object other) {
        return true;    //to implement
    }
}

