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
    protected boolean isResumeExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void rewriteResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void insertResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected int getResumeIndex(String uuid) {
        return -1;
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(uuid);
    }
}
