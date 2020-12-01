package action;


import comparators.UserComparator;
import users.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsersQuery extends Query {

    public UsersQuery(final int id, final int number,
                      final String objectType,
                      final String sortCriteria,
                      final String criteria,
                      final List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }

    /**
     *
     * @param users
     * @return user with most ratings given
     */
    public List<User> ratings(final List<User> users) {

        List<User> sorted = new ArrayList<>();

        for (User user: users) {
            if (!user.getRated().isEmpty()) {
                sorted.add(user);
            }
        }

        UserComparator cmp = new UserComparator();

        if (this.getSortCriteria().equals("asc")) {
            Collections.sort(sorted, cmp);
        } else
        if (this.getSortCriteria().equals("desc")) {
            Collections.sort(sorted, Collections.reverseOrder(cmp));
        }
        if (this.getNumber() < sorted.size()) {
            List<User> first;
            first = sorted.stream().limit(this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;

    }
}
