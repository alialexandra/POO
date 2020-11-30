package action;

import actor.Actor;
import comparators.RatingComparator;
import comparators.UserComparator;
import comparators.VideoRatingComp;
import entertainment.Video;
import users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsersQuery extends Query{

    public UsersQuery(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }


    public List<User> ratings(List<User> users){

        List<User> sorted = new ArrayList<>();

        UserComparator cmp = new UserComparator();

        if (this.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(this.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(this.getNumber() < sorted.size()){// <= ??
            List<User> first;
            first = sorted.stream().limit(this.getNumber()).collect(Collectors.toList());
            return first;
        }

        return sorted;

    }
}
