package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();
    private int index;

    @Override
    protected void setSearchKey(String uuid) {
        index = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                index = i;
            }
        }
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isResumeExist() {
        return index >= 0;
    }

    @Override
    protected void rewriteResume(Resume r) {
        storage.set(index, r);
    }

    @Override
    protected void insertResume(Resume r) {
        storage.add(storage.size(), r);
    }

    @Override
    protected Resume getResume() {
        return storage.get(index);
    }

    @Override
    protected void removeResume() {
        storage.remove(index);
    }
}
