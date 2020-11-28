package comparators;

import actor.Actor;

import java.util.Comparator;

public final class NameComparator implements Comparator<Actor> {

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
            result = o1.getName().compareTo(o2.getName());
        }
        return result;
    }
}
