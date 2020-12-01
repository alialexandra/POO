package comparators;

import entertainment.Video;
import java.util.Comparator;

public class BestRatingComp implements Comparator<Video> {
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
            result = Double.compare(o1.getRatingAverage(), o2.getRatingAverage());
            if (result == 0) {
                result = Integer.compare(o1.getId(), o2.getId());
            }
        }
        return result;
    }
}
