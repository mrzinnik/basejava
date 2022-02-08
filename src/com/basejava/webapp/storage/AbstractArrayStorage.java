package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isResumeExist(String searchKey) {
        return searchKeyToIndex(searchKey) >= 0;
    }

    @Override
    protected String findSearchKey(String uuid) {
        return Integer.toString(getResumeIndex(uuid));
    }

    @Override
    protected final void rewriteResume(Resume r, String searchKey) {
        storage[searchKeyToIndex(searchKey)] = r;
    }

    @Override
    protected final void insertResume(Resume r, String searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertToArray(r, searchKeyToIndex(searchKey));
        size++;
    }

    @Override
    protected final Resume getResume(String searchKey) {
        return storage[searchKeyToIndex(searchKey)];
    }

    @Override
    protected final void removeResume(String searchKey) {
        int index = searchKeyToIndex(searchKey);
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        storage[size - 1] = null;
        size--;
    }

    protected abstract void insertToArray(Resume r, int index);

    protected abstract int getResumeIndex(String uuid);

    private int searchKeyToIndex(String searchKey) {
        return Integer.parseInt(searchKey);
    }
}
