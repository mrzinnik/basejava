package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected boolean isResumeExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((String) searchKey);
    }
}