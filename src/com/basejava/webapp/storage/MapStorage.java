package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();

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
    protected boolean isResumeExist(Object searchKey) {
        return storage.containsKey(searchKeyToKey(searchKey));
    }

    @Override
    protected Object findSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void rewriteResume(Resume r, Object searchKey) {
        storage.put(searchKeyToKey(searchKey), r);
    }

    @Override
    protected void insertResume(Resume r, Object searchKey) {
        storage.put(searchKeyToKey(searchKey), r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get(searchKeyToKey(searchKey));
    }

    @Override
    protected void removeResume(Object searchKey) {
        storage.remove(searchKeyToKey(searchKey));
    }

    private String searchKeyToKey(Object searchKey) {
        return (String) searchKey;
    }
}
