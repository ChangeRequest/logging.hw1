package com.github.didva.store.api.repositories;

import com.github.didva.store.storage.Storage;
import com.github.didva.strore.model.Catalog;

import java.util.List;

public class CatalogRepository {

    private Storage<Catalog, Long> catalogStorage;

    public CatalogRepository(Storage<Catalog, Long> catalogStorage) {
        if (catalogStorage == null) {
            throw new IllegalArgumentException();
        }
        this.catalogStorage = catalogStorage;
    }

    public Catalog saveOrUpdate(Catalog catalog) {
        if (catalog.getId() == null) {
            return catalogStorage.save(catalog);
        }
        return catalogStorage.update(catalog);
    }

    public Catalog find(Long id) {
        return catalogStorage.findOne(id);
    }

    public void remove(Long id) {
        catalogStorage.delete(id);
    }

    public List<Catalog> getAll() {
        return catalogStorage.findAll();
    }

}
