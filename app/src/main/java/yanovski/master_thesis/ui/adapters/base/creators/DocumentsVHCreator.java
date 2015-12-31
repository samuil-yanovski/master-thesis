package yanovski.master_thesis.ui.adapters.base.creators;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Document;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;

/**
 * Created by Samuil on 12/30/2015.
 */
public class DocumentsVHCreator implements ViewHolderCreator<Document> {
    private static final int VIEW_TYPE_ID = CreatorManager.ID_GENERATOR.getAndIncrement();

    public static class DocumentViewHolder extends BaseViewHolder {
        @Bind(R.id.title)
        public TextView title;
        @Bind(R.id.description)
        public TextView description;

        public DocumentViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE_ID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<Document> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<Document> adapter, BaseViewHolder holder,
        int position) {
        DocumentViewHolder h = (DocumentViewHolder) holder;

        Document item = adapter.getItem(position);
        h.title.setText(item.title);
        if (TextUtils.isEmpty(item.description)) {
            h.description.setVisibility(View.GONE);
        } else {
            h.description.setVisibility(View.VISIBLE);
            h.description.setText(item.description);
        }
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<Document> adapter, int position) {
        return true;
    }

    @Override
    public Class getItemClass() {
        return Document.class;
    }
}
