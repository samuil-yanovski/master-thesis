package yanovski.master_thesis.data.models;

import java.util.List;

/**
 * Created by Samuil on 1/1/2016.
 */
public class Category {
    public String id;
    public String name;
    public List<Thesis> theses;

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        if (o instanceof Category) {
            Category other = (Category) o;
            if (null == id && null == other.id) {
                equal = true;
            } else if (null != id) {
                equal = id.equals(other.id);
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return null != id ? id.hashCode() : 0;
    }
}
