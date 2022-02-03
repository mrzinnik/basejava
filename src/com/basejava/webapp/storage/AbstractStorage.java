package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public abstract void clear();

    @Override
    public final void update(Resume r) {
        int elementIndex = getElementIndex(r.getUuid());
        if (elementIndex < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        rewriteElement(r, elementIndex);
    }

    @Override
    public final void save(Resume r) {
        int elementIndex = getElementIndex(r.getUuid());
        if (elementIndex >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        insertElement(r, elementIndex);
    }

    @Override
    public final Resume get(String uuid) {
        int elementIndex = getElementIndex(uuid);
        if (elementIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getElementValue(uuid, elementIndex);
    }

    @Override
    public final void delete(String uuid) {
        int elementIndex = getElementIndex(uuid);
        if (elementIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        removeElement(uuid, elementIndex);
    }

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    protected abstract void rewriteElement(Resume r, int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Resume getElementValue(String uuid, int index);

    protected abstract int getElementIndex(String uuid);

    protected abstract void removeElement(String uuid, int index);
}
