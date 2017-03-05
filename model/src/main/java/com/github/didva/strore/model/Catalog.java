package com.github.didva.strore.model;

import com.github.didva.store.persistenceapi.StoredEntity;

import java.util.List;

public class Catalog implements StoredEntity<Long> {

    private Long id;
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
