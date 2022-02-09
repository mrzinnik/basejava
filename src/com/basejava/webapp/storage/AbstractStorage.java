package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        updateResume(r, checkResumeForExistence(r.getUuid(), true));
    }

    @Override
    public final void save(Resume r) {
        saveResume(r, checkResumeForExistence(r.getUuid(), false));
    }

    @Override
    public final Resume get(String uuid) {
        return getResume(checkResumeForExistence(uuid, true));
    }

    @Override
    public final void delete(String uuid) {
        deleteResume(checkResumeForExistence(uuid, true));
    }

    private Object checkResumeForExistence(String uuid, boolean mustExist) {
        Object searchKey = getSearchKey(uuid);
        boolean isExist = isResumeExist(searchKey);
        if (isExist ^ mustExist) {
            if (isExist) {
                throw new ExistStorageException(uuid);
            }
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isResumeExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Resume r, Object searchKey);

    protected abstract void saveResume(Resume r, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);
}
