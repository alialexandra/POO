package comparators;

import entertainment.Video;

import java.util.Comparator;

public class ViewsComparator implements Comparator<Video> {

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
            result = Integer.compare(o1.getNoViews(), o2.getNoViews());
            if (result == 0) {
                result = o1.getName().compareTo(o2.getName());
            }
        }
        return result;
    }
}
