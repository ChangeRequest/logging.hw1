package com.github.didva.store.inmemmorystorage;

import com.github.didva.store.persistenceapi.IdGenerator;

public class LongIdGenerator implements IdGenerator<Long> {

    private long nextId = 1;

    @Override
    public Long generateId() {
        return nextId++;
    }
}
