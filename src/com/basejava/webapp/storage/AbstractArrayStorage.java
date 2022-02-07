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
    protected int index;

    @Override
    protected void setSearchKey(String uuid) {
        index = getResumeIndex(uuid);
    }

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
    protected boolean isResumeExist() {
        return index >= 0;
    }

    @Override
    protected final void rewriteResume(Resume r) {
        storage[index] = r;
    }

    @Override
    protected final void insertResume(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertToArray(r);
        size++;
    }

    @Override
    protected final Resume getResume() {
        return storage[index];
    }

    @Override
    protected final void removeResume() {
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        storage[size - 1] = null;
        size--;
    }

    protected abstract int getResumeIndex(String uuid);

    protected abstract void insertToArray(Resume r);
}
