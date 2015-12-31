package yanovski.master_thesis.ui.adapters.base.creators;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;
import yanovski.master_thesis.utils.PhoneHelper;

/**
 * Created by Samuil on 12/30/2015.
 */
public class TeacherVHCreator implements ViewHolderCreator<Teacher> {
    private static final int VIEW_TYPE_ID = CreatorManager.ID_GENERATOR.getAndIncrement();

    public static class TeacherViewHolder extends BaseViewHolder {
        @Bind(R.id.name)
        public TextView name;
        @Bind(R.id.interest)
        public TextView interest;
        @Bind(R.id.avatar)
        public ImageView avatar;
        @Bind(R.id.call)
        public View call;
        public String phone;
        private PhoneHelper phoneHelper;

        public TeacherViewHolder(View view) {
            super(view);
            phoneHelper = MasterThesisApplication.getMainComponent().getPhoneHelper();
        }

        @OnClick(R.id.call)
        public void call() {
            phoneHelper.call(phone);
        }

    }

    @Inject
    Picasso picasso;

    public TeacherVHCreator() {
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE_ID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<Teacher> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_teacher, parent, false);
        return new TeacherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<Teacher> adapter, BaseViewHolder holder,
        int position) {
        TeacherViewHolder h = (TeacherViewHolder) holder;

        Teacher item = adapter.getItem(position);
        picasso.load(item.avatar).error(R.drawable.ic_person).into(h.avatar);
        h.name.setText(item.name);
        if (null != item.interests && 0 < item.interests.size()) {
            h.interest.setText(item.interests.get(0).name);
        } else {
            h.interest.setText(null);
        }

        h.phone = item.contact.phone;
        h.call.setVisibility(TextUtils.isEmpty(h.phone) ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<Teacher> adapter, int position) {
        return true;
    }

    @Override
    public Class getItemClass() {
        return Teacher.class;
    }
}
