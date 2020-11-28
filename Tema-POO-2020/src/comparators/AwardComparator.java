package comparators;

import actor.Actor;

import java.util.Comparator;

public final class AwardComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor o1, Actor o2) {
        int result;
        if (o1 == null && o2 == null) {
            result = 0;
        } else if (o1 == null) {
            result = -1;
        } else if (o2 == null) {
            result = 1;
        } else {
            result = Integer.compare(o1.getNoAwards(), o2.getNoAwards());
            if (result == 0) {
                result = o1.getName().compareTo(o2.getName());
            }
        }
        return result;
    }

}
