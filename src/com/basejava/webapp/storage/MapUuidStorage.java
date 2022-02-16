package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage<String> {

    @Override
    protected boolean isResumeExist(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume r, String key) {
        storage.put(key, r);
    }

    @Override
    protected void saveResume(Resume r, String key) {
        storage.put(key, r);
    }

    @Override
    protected Resume getResume(String key) {
        return storage.get(key);
    }

    @Override
    protected void deleteResume(String key) {
        storage.remove(key);
    }
}