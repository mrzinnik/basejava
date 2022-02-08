package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

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
    protected boolean isResumeExist(String searchKey) {
        return searchKeyToIndex(searchKey) >= 0;
    }

    @Override
    protected String findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return Integer.toString(i);
            }
        }
        return "-1";
    }

    @Override
    protected void rewriteResume(Resume r, String searchKey) {
        storage.set(searchKeyToIndex(searchKey), r);
    }

    @Override
    protected void insertResume(Resume r, String searchKey) {
        storage.add(storage.size(), r);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return storage.get(searchKeyToIndex(searchKey));
    }

    @Override
    protected void removeResume(String searchKey) {
        storage.remove(searchKeyToIndex(searchKey));
    }

    private int searchKeyToIndex(String searchKey) {
        return Integer.parseInt(searchKey);
    }
}
