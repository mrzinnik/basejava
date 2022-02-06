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
    protected boolean isResumeExist(String uuid) {
        return getResumeIndex(uuid) >= 0;
    }

    @Override
    protected void rewriteResume(Resume r) {
        storage.set(getResumeIndex(r.getUuid()), r);
    }

    @Override
    protected void insertResume(Resume r) {
        storage.add(storage.size(), r);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(getResumeIndex(uuid));
    }

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(getResumeIndex(uuid));
    }
}
