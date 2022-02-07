package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        setSearchKey(r.getUuid());
        checkResumeForExistence(r.getUuid(), true);
        rewriteResume(r);
    }

    @Override
    public final void save(Resume r) {
        setSearchKey(r.getUuid());
        checkResumeForExistence(r.getUuid(), false);
        insertResume(r);
    }

    @Override
    public final Resume get(String uuid) {
        setSearchKey(uuid);
        checkResumeForExistence(uuid, true);
        return getResume();
    }

    @Override
    public final void delete(String uuid) {
        setSearchKey(uuid);
        checkResumeForExistence(uuid, true);
        removeResume();
    }

    private void checkResumeForExistence(String uuid, boolean expectedResult) {
        boolean isExist = isResumeExist();
        if (isExist ^ expectedResult) {
            if (isExist) {
                throw new ExistStorageException(uuid);
            } else {
                throw new NotExistStorageException(uuid);
            }
        }
    }

    protected abstract void setSearchKey(String uuid);

    protected abstract boolean isResumeExist();

    protected abstract void rewriteResume(Resume r);

    protected abstract void insertResume(Resume r);

    protected abstract Resume getResume();

    protected abstract void removeResume();
}
