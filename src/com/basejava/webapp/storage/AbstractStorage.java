package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        checkResumeForExistence(r.getUuid(), true);
        rewriteResume(r, findSearchKey(r.getUuid()));
    }

    @Override
    public final void save(Resume r) {
        checkResumeForExistence(r.getUuid(), false);
        insertResume(r, findSearchKey(r.getUuid()));
    }

    @Override
    public final Resume get(String uuid) {
        checkResumeForExistence(uuid, true);
        return getResume(findSearchKey(uuid));
    }

    @Override
    public final void delete(String uuid) {
        checkResumeForExistence(uuid, true);
        removeResume(findSearchKey(uuid));
    }

    private void checkResumeForExistence(String uuid, boolean expectedResult) {
        boolean isExist = isResumeExist(findSearchKey(uuid));
        if (isExist ^ expectedResult) {
            if (isExist) {
                throw new ExistStorageException(uuid);
            } else {
                throw new NotExistStorageException(uuid);
            }
        }
    }

    protected abstract boolean isResumeExist(String searchKey);

    protected abstract String findSearchKey(String uuid);

    protected abstract void rewriteResume(Resume r, String searchKey);

    protected abstract void insertResume(Resume r, String searchKey);

    protected abstract Resume getResume(String searchKey);

    protected abstract void removeResume(String searchKey);
}
