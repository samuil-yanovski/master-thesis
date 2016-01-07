package yanovski.master_thesis.utils;

import java.util.Collection;

/**
 * Created by Samuil on 1/7/2016.
 */
public class CollectionsUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || 0 == collection.size();
    }
}
