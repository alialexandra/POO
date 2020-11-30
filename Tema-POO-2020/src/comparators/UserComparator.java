package comparators;

import users.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int result;
        if (o1 == null && o2 == null) {
            result = 0;
        } else if (o1 == null) {
            result = -1;
        } else if (o2 == null) {
            result = 1;
        } else {
            result = Integer.compare(o1.getRated().size(), o2.getRated().size());
            if (result == 0) {
                result = o1.getUsername().compareTo(o2.getUsername());
            }
        }
        return result;
    }
}
