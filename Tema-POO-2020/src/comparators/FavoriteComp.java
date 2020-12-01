package comparators;

import entertainment.Video;
import java.util.Comparator;

public final class FavoriteComp implements Comparator<Video> {

    @Override
    public int compare(final Video o1, final Video o2) {
        int result;
        if (o1 == null && o2 == null) {
            result = 0;
        } else if (o1 == null) {
            result = -1;
        } else if (o2 == null) {
            result = 1;
        } else {
            result = Integer.compare(o1.getNoFavorite(), o2.getNoFavorite());
            if (result == 0) {
                result = Integer.compare(o1.getId(), o2.getId());
            }
        }
        return result;
    }
}

