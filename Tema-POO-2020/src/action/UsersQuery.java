package action;

import users.User;

import java.util.List;

public class UsersQuery extends Query{

    public UsersQuery(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }

    public List<User> ratings(List<User> users){
        return null;
    }
}
