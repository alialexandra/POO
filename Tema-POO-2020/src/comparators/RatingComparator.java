package comparators;

import actor.Actor;

import java.util.Comparator;

public final class RatingComparator implements Comparator<Actor> {


    @Override
    public int compare(final Actor o1, final Actor o2) {
        int result;
        if (o1 == null && o2 == null) {
            result = 0;
        } else if (o1 == null) {
            result = -1;
        } else if (o2 == null) {
            result = 1;
        } else {
            result = Double.compare(o1.getAverage(), o2.getAverage());
            if (result == 0) {
                result = o1.getName().compareTo(o2.getName());
            }
        }
        return result;
    }

    // la asta mai ma gandesc

}
