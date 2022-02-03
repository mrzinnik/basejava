package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

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
    protected void rewriteElement(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected void insertElement(Resume r, int index) {
        storage.add(storage.size(), r);
    }

    @Override
    protected Resume getElementValue(String uuid, int index) {
        return storage.get(index);
    }

    @Override
    protected int getElementIndex(String uuid) {
        ListIterator<Resume> iterator = storage.listIterator();
        while (iterator.hasNext()) {
            if (uuid.equals(iterator.next().getUuid())) {
                return iterator.previousIndex();
            }
        }
        return -1;
    }

    @Override
    protected void removeElement(String uuid, int index) {
        storage.remove(index);
    }
}
