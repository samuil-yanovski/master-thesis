package yanovski.master_thesis.ui.adapters;

import java.util.ArrayList;
import java.util.List;

import java8.util.stream.StreamSupport;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.Thesis;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.CategoryVHCreator;
import yanovski.master_thesis.ui.adapters.base.creators.ThesisVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ThesesAdapter extends BaseRecyclerViewAdapter<ThesesAdapter.Item> {

    public enum ItemType {
        Category,
        Thesis
    }

    public static class Item {
        private ItemType type;
        private Category category;
        private Thesis thesis;

        public Item(Category category) {
            this.category = category;
            type = ItemType.Category;
        }

        public Item(Thesis thesis) {
            this.thesis = thesis;
            type = ItemType.Thesis;
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
    }

    @Override
    protected void init() {
        super.init();
        add(new CategoryVHCreator());
        add(new ThesisVHCreator());
    }

    public void setCategories(List<Category> categories) {
        List<Item> items = new ArrayList<>();

        StreamSupport.stream(categories)
            .sorted((l, r) -> l.name.compareTo(r.name))
            .forEachOrdered(c -> {
                items.add(new Item(c));
                StreamSupport.stream(c.theses)
                    .sorted((l, r) -> l.title.compareTo(r.title))
                    .forEachOrdered(t -> items.add(new Item(t)));
            });

        setItems(items);
    }
}
