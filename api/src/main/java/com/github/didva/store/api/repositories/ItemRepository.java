package com.github.didva.store.api.repositories;

import com.github.didva.store.storage.Storage;
import com.github.didva.strore.model.Item;

import java.util.List;

public class ItemRepository {

    private Storage<Item, Long> itemStorage;

    public ItemRepository(Storage<Item, Long> itemStorage) {
        if (itemStorage == null) {
            throw new IllegalArgumentException();
        }
        this.itemStorage = itemStorage;
    }

    public Item saveOrUpdate(Item item) {
        return itemStorage.saveOrUpdate(item);
    }

    public Item find(Long id) {
        return itemStorage.findOne(id);
    }

    public void remove(Long id) {
        itemStorage.delete(id);
    }

    public List<Item> getAll() {
        return itemStorage.findAll();
    }

}
