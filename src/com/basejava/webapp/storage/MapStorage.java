package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();
    private String key;

    @Override
    protected void setSearchKey(String uuid) {
        key = uuid;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isResumeExist() {
        return storage.containsKey(key);
    }

    @Override
    protected void rewriteResume(Resume r) {
        storage.put(key, r);
    }

    @Override
    protected void insertResume(Resume r) {
        storage.put(key, r);
    }

    @Override
    protected Resume getResume() {
        return storage.get(key);
    }

    @Override
    protected void removeResume() {
        storage.remove(key);
    }
}
