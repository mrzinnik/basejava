package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isResumeExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume r, Integer index) {
        storage.set(index, r);
    }

    @Override
    protected void saveResume(Resume r, Integer index) {
        storage.add(storage.size(), r);
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage.get(index);
    }

    @Override
    protected void deleteResume(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    protected List<Resume> getStorageAsList() {
        return new ArrayList<>(storage);
    }
}