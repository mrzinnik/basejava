package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        String uuid = r.getUuid();
        Object searchKey = findSearchKey(uuid);
        checkResumeForExistence(uuid, searchKey, true);
        rewriteResume(r, searchKey);
    }

    @Override
    public final void save(Resume r) {
        String uuid = r.getUuid();
        Object searchKey = findSearchKey(uuid);
        checkResumeForExistence(uuid, searchKey, false);
        insertResume(r, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = findSearchKey(uuid);
        checkResumeForExistence(uuid, searchKey, true);
        return getResume(searchKey);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = findSearchKey(uuid);
        checkResumeForExistence(uuid, searchKey, true);
        removeResume(searchKey);
    }

    private void checkResumeForExistence(String uuid, Object searchKey, boolean expectedResult) {
        boolean isExist = isResumeExist(searchKey);
        if (isExist ^ expectedResult) {
            if (isExist) {
                throw new ExistStorageException(uuid);
            }
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract boolean isResumeExist(Object searchKey);

    protected abstract Object findSearchKey(String uuid);

    protected abstract void rewriteResume(Resume r, Object searchKey);

    protected abstract void insertResume(Resume r, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void removeResume(Object searchKey);
}
