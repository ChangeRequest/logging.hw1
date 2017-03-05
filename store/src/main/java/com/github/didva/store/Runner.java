package com.github.didva.store;

import com.github.didva.strore.model.Catalog;
import com.github.didva.strore.model.Category;
import com.github.didva.strore.model.Item;
import com.github.didva.strore.model.Property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class Runner {

    public static void main(String[] args) {
        StoreApp storeApp = new StoreApp();
        createTestData(storeApp);
        storeApp.showAllCatalogs();
        List<Item> itemsInTheBasket = putItemsIntoBasket(storeApp, 20);
        List<Item> remainItemsInTheBasket = removeItemsFromBasket(storeApp, new ArrayList<>(itemsInTheBasket));
        storeApp.showLastItemsInTheBasket(countUniqueItems(remainItemsInTheBasket) / 2);
        storeApp.checkout();
    }

    private static List<Item> removeItemsFromBasket(StoreApp storeApp, List<Item> itemsInTheBasket) {
        if (itemsInTheBasket.isEmpty()) {
            return emptyList();
        }
        int toRemoveCount = itemsInTheBasket.size() / 2;
        int removed = 0;
        Iterator<Item> iterator = itemsInTheBasket.iterator();
        while (iterator.hasNext() && removed < toRemoveCount) {
            storeApp.removeItemFromBasket(iterator.next());
            iterator.remove();
            ++removed;
        }
        return itemsInTheBasket;
    }

    private static List<Item> putItemsIntoBasket(StoreApp storeApp, int count) {
        List<Item> items = storeApp.getAllItems();
        if (items.isEmpty()) {
            return emptyList();
        }
        List<Item> addedItems = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; ++i) {
            Item item = items.get(random.nextInt(items.size()));
            addedItems.add(item);
            storeApp.addItemToBasket(item);
        }
        return addedItems;
    }

    private static int countUniqueItems(List<Item> items) {
        return new HashSet<>(items).size();
    }

    private static void createTestData(StoreApp storeApp) {
        Category toys = storeApp.createCategory("Toys", "Toys for children");
        Category cloth = storeApp.createCategory("Cloth", "Cloth for children");

        Item smallToy = storeApp.createItem("Small butterfly", "Small handmade butterfly", 99, singletonList(toys),
                asList(new Property("size", "10"), new Property("manufacturer", "CN")));
        Item bigToy = storeApp.createItem("Big butterfly", "Big handmade butterfly", 199, singletonList(toys),
                asList(new Property("size", "50"), new Property("manufacturer", "CN")));

        Item blackJacket = storeApp.createItem("Jacket", "Black children jacket", 200, singletonList(cloth),
                asList(new Property("size", "S"), new Property("manufacturer", "UAE")));
        Item redShoes = storeApp.createItem("Shoes", "Red handmade shoes", 350, singletonList(cloth),
                asList(new Property("size", "39"), new Property("manufacturer", "UA")));

        Catalog childrenCatalog = storeApp.createCatalog(asList(smallToy, bigToy, blackJacket, redShoes));
    }

}
