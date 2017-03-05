package com.github.didva.store;

import com.github.didva.store.api.BasketApi;
import com.github.didva.store.api.repositories.CatalogRepository;
import com.github.didva.store.api.repositories.CategoryRepository;
import com.github.didva.store.api.repositories.ItemRepository;
import com.github.didva.store.inmemmorystorage.InMemoryStorage;
import com.github.didva.store.inmemmorystorage.LongIdGenerator;
import com.github.didva.strore.model.Catalog;
import com.github.didva.strore.model.Category;
import com.github.didva.strore.model.Item;
import com.github.didva.strore.model.Property;

import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;

public class StoreApp {

    private BasketApi basketApi;
    private CategoryRepository categoryRepository;
    private CatalogRepository catalogRepository;
    private ItemRepository itemRepository;

    public StoreApp() {
        this.basketApi = new BasketApi();
        this.catalogRepository = new CatalogRepository(new InMemoryStorage<>(new LongIdGenerator()));
        this.categoryRepository = new CategoryRepository(new InMemoryStorage<>(new LongIdGenerator()));
        this.itemRepository = new ItemRepository(new InMemoryStorage<>(new LongIdGenerator()));
    }

    public void checkout() {
        Map<Item, Integer> items = basketApi.checkout();
        String format = "%3s | %30.30s | %10s | %7s ";
        System.out.println(String.format(format, "#", "Title", "Amount", "Price"));
        int index = 1;
        double totalPrice = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            Integer amount = entry.getValue();
            double price = item.getPrice() * amount;
            System.out.println(String.format(format, index++, item.getTitle(), amount, price));
            totalPrice += price;
        }
        System.out.println(String.format("%50s: %.2f ", "Total", totalPrice));
    }

    public void showLastItemsInTheBasket(int n) {
        String format = "%3s | %30.30s | %60.60s | %7s | %20s | %s ";
        System.out.println(String.format(format, "#", "Title", "Description", "Price", "Categories", "Properties"));
        for (Item item : basketApi.getLast(n)) {
            System.out.println(String.format(format, item.getId(), item.getTitle(), item.getDescription(),
                    item.getPrice(), item.getCategories(), item.getProperties()));
        }
        System.out.println();
    }

    public void showAllCatalogs() {
        List<Catalog> catalogs = catalogRepository.getAll();
        for (Catalog catalog : catalogs) {
            System.out.println(String.format("Catalog #%d:", catalog.getId()));
            String format = "%3s | %30.30s | %60.60s | %7s | %20s | %s ";
            System.out.println(String.format(format, "#", "Title", "Description", "Price", "Categories", "Properties"));
            for (Item item : catalog.getItems()) {
                System.out.println(String.format(format, item.getId(), item.getTitle(), item.getDescription(),
                        item.getPrice(), item.getCategories(), item.getProperties()));
            }
            System.out.println();
        }
    }

    public Catalog createCatalog(List<Item> items) {
        Catalog catalog = new Catalog();
        catalog.setItems(items);
        return catalogRepository.saveOrUpdate(catalog);
    }

    public Category createCategory(String title, String description) {
        Category category = new Category();
        category.setTitle(title);
        category.setDescription(description);
        return categoryRepository.saveOrUpdate(category);
    }

    public Item createItem(String title, String description, double price, List<Category> categories,
            List<Property> properties) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        item.setCategories(unmodifiableList(categories));
        item.setProperties(unmodifiableList(properties));
        return itemRepository.saveOrUpdate(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.getAll();
    }

    public void addItemToBasket(Item item) {
        basketApi.add(item);
    }

    public void removeItemFromBasket(Item item) {
        basketApi.remove(item);
    }
}
