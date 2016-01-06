package yanovski.master_thesis.ui.adapters;

import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.StudentVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class StudentsAdapter extends BaseRecyclerViewAdapter<Student> {
    @Override
    protected void init() {
        super.init();
        add(new StudentVHCreator());
    }
}
