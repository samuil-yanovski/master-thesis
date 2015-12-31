package yanovski.master_thesis.ui.adapters;

import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.TeacherVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class TeachersAdapter extends BaseRecyclerViewAdapter<Teacher> {
    @Override
    protected void init() {
        super.init();
        add(new TeacherVHCreator());
    }
}
