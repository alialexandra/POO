package comparators;

import entertainment.Video;

import java.util.Comparator;

public class DurationComp implements Comparator<Video> {

    @Override
    public int compare(Video o1, Video o2) {
        int result;
        if (o1 == null && o2 == null) {
            result = 0;
        } else if (o1 == null) {
            result = -1;
        } else if (o2 == null) {
            result = 1;
        } else {
            result = Integer.compare(o1.getDurationVideo(), o2.getDurationVideo());
            if (result == 0) {
                result = o1.getName().compareTo(o2.getName());
            }
        }
        return result;
    }
}
