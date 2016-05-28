package yanovski.master_thesis.ui.adapters;

import java.util.ArrayList;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.Thesis;
import yanovski.master_thesis.data.models.ThesisProposal;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.CategoryVHCreator;
import yanovski.master_thesis.ui.adapters.base.creators.ThesisProposalVHCreator;
import yanovski.master_thesis.ui.adapters.base.creators.ThesisVHCreator;
import yanovski.master_thesis.utils.CollectionUtils;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ThesesAdapter extends BaseRecyclerViewAdapter<ThesesAdapter.Item> {

    public enum ItemType {
        Category,
        Thesis,
        ThesisProposal
    }

    public static class Item {
        private ItemType type;
        private Category category;
        private Thesis thesis;
        private ThesisProposal thesisProposal;

        public Item(Category category) {
            this.category = category;
            type = ItemType.Category;
        }

        public Item(Thesis thesis) {
            this.thesis = thesis;
            type = ItemType.Thesis;
        }

        public Item(ThesisProposal thesisProposal) {
            this.thesisProposal = thesisProposal;
            type = ItemType.ThesisProposal;
        }

        public ItemType getType() {
            return type;
        }

        public Category getCategory() {
            return category;
        }

        public Thesis getThesis() {
            return thesis;
        }

        public ThesisProposal getThesisProposal() {
            return thesisProposal;
        }
    }

    @Override
    protected void init() {
        super.init();
        add(new CategoryVHCreator());
        add(new ThesisVHCreator());
        add(new ThesisProposalVHCreator());
    }

    public void addCategories(List<Category> categories) {
        List<Item> items = new ArrayList<>();

        StreamSupport.stream(categories)
            .sorted((l, r) -> l.name.compareTo(r.name))
            .forEachOrdered(c -> {
                if (null != c.theses && 0 < c.theses.size()) {
                    items.add(new Item(c));
                    StreamSupport.stream(c.theses)
                        .sorted((l, r) -> l.title.compareTo(r.title))
                        .forEachOrdered(t -> items.add(new Item(t)));
                }
            });


        addItems(items);
    }

    public void addThesisProposal(List<ThesisProposal> proposals) {
        List<Item> items = StreamSupport.stream(proposals)
            .map(Item::new)
            .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(items)) {
            List<Item> categoryItems = new ArrayList<>();
            categoryItems.add(new Item(LocalDataProvider.getProposalsCategory()));
            addItems(categoryItems);
        }
        addItems(items);
    }

    private void addItems(List<Item> items) {
        List<Item> current = getItems();
        if (null == current) {
            current = new ArrayList<>();
        }
        current.addAll(items);
        setItems(current);
    }
}
