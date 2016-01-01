package yanovski.master_thesis.ui.adapters.base.creators;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Thesis;
import yanovski.master_thesis.ui.adapters.ThesesAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ThesisVHCreator implements ViewHolderCreator<ThesesAdapter.Item> {
    private static final int VIEW_TYPE_ID = CreatorManager.ID_GENERATOR.getAndIncrement();

    public static class ThesisViewHolder extends BaseViewHolder implements ValueAnimator.AnimatorUpdateListener {
        @Bind(R.id.name)
        public TextView name;
        @Bind(R.id.title)
        public TextView title;
        @Bind(R.id.description)
        public TextView description;
        @Bind(R.id.avatar)
        public ImageView avatar;
        @Bind(R.id.toggle)
        public ImageButton toggle;
        private TextPaint paint;
        private int duration;

        public ThesisViewHolder(View view) {
            super(view);
            paint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(description.getTextSize());
            duration = view.getContext()
                .getResources()
                .getInteger(android.R.integer.config_shortAnimTime);
        }

        @OnClick(R.id.toggle)
        public void toggleDescription() {
            int height = description.getMeasuredHeight();
            if (0 < height) {
                collapseDescription();
            } else {
                expandDescription();
            }
        }

        private void expandDescription() {
            int height = calculateTextHeight();

            toggle.setEnabled(false);
            ValueAnimator animator = ValueAnimator.ofInt(0, height);
            animator.setDuration(duration);
            animator.addUpdateListener(this);
            animator.start();
        }

        private int calculateTextHeight() {
            CharSequence text = description.getText();
            StaticLayout layout = new StaticLayout(text, paint, description.getMeasuredWidth(),
                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
            return layout.getHeight();
        }

        private void collapseDescription() {
            toggle.setEnabled(false);
            int height = description.getMeasuredHeight();
            ValueAnimator animator = ValueAnimator.ofInt(height, 0);
            animator.setDuration(duration);
            animator.addUpdateListener(this);
            animator.start();
        }

        @OnClick(R.id.ask)
        public void ask() {

        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Integer value = (Integer) animation.getAnimatedValue();
            int height = value.intValue();
            description.getLayoutParams().height = height;
            description.requestLayout();

            if (animation.isRunning()) {
                if (0 < height) {
                    toggle.setImageResource(R.drawable.ic_collapse);
                    toggle.setContentDescription(toggle.getContext()
                        .getString(R.string.collapse_content_description));
                } else {
                    toggle.setImageResource(R.drawable.ic_expand);
                    toggle.setContentDescription(toggle.getContext()
                        .getString(R.string.expand_content_description));
                }
                toggle.setEnabled(true);
            }
        }
    }

    @Inject
    Picasso picasso;

    public ThesisVHCreator() {
        MasterThesisApplication.getMainComponent()
            .inject(this);
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE_ID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<ThesesAdapter.Item> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_thesis, parent, false);
        return new ThesisViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<ThesesAdapter.Item> adapter,
        BaseViewHolder holder, int position) {
        ThesisViewHolder h = (ThesisViewHolder) holder;

        ThesesAdapter.Item item = adapter.getItem(position);
        Thesis thesis = item.getThesis();
        h.title.setText(thesis.title);
        h.description.setText(thesis.description);
        h.name.setText(thesis.author.name);
        picasso.load(thesis.author.avatar)
            .error(R.drawable.ic_person)
            .into(h.avatar);
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<ThesesAdapter.Item> adapter, int position) {
        return ThesesAdapter.ItemType.Thesis.equals(adapter.getItem(position)
            .getType());
    }

    @Override
    public Class getItemClass() {
        return ThesesAdapter.Item.class;
    }
}
