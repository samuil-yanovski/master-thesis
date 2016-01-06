package yanovski.master_thesis.ui.adapters.base.creators;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;
import yanovski.master_thesis.ui.utils.CircleTransform;

/**
 * Created by Samuil on 12/30/2015.
 */
public class StudentVHCreator implements ViewHolderCreator<Student> {
    private static final int VIEW_TYPE_ID = CreatorManager.ID_GENERATOR.getAndIncrement();

    public static class StudentViewHolder extends BaseViewHolder {
        @Bind(R.id.name)
        public TextView name;
        @Bind(R.id.thesis)
        public TextView thesis;
        @Bind(R.id.avatar)
        public ImageView avatar;

        public StudentViewHolder(View view) {
            super(view);
        }
    }

    @Inject
    Picasso picasso;

    @Override
    public int getItemViewType() {
        return VIEW_TYPE_ID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<Student> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<Student> adapter, BaseViewHolder holder,
        int position) {
        StudentViewHolder h = (StudentViewHolder) holder;

        Student item = adapter.getItem(position);
        picasso.load(item.avatar)
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .transform(new CircleTransform())
            .into(h.avatar);
        h.name.setText(item.name);
        h.thesis.setText(null != item.thesis ? item.thesis.title : null);
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<Student> adapter, int position) {
        return true;
    }

    @Override
    public Class getItemClass() {
        return Student.class;
    }
}
