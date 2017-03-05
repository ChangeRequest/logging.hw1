package com.github.didva.store.persistenceapi;

public interface StoredEntity<ID> {

    ID getId();

    void setId(ID id);
}
