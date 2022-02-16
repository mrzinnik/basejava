package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isResumeExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected final void updateResume(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected final void saveResume(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertToArray(r, index);
        size++;
    }

    @Override
    protected final Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected final void deleteResume(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        storage[size - 1] = null;
        size--;
    }

    protected abstract void insertToArray(Resume r, int index);

    @Override
    protected List<Resume> getStorageAsList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }
}