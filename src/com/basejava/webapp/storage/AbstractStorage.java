package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        checkResumeForExistence(r.getUuid(), true);
        rewriteResume(r);
    }

    @Override
    public final void save(Resume r) {
        checkResumeForExistence(r.getUuid(), false);
        insertResume(r);
    }

    @Override
    public final Resume get(String uuid) {
        checkResumeForExistence(uuid, true);
        return getResume(uuid);
    }

    @Override
    public final void delete(String uuid) {
        checkResumeForExistence(uuid, true);
        removeResume(uuid);
    }

    private void checkResumeForExistence(String uuid, boolean expectedResult) {
        boolean isExist = isResumeExist(uuid);
        if (isExist) {
            if (!expectedResult) {
                throw new ExistStorageException(uuid);
            }
        } else {
            if (expectedResult) {
                throw new NotExistStorageException(uuid);
            }
        }
    }

    protected abstract boolean isResumeExist(String uuid);

    protected abstract void rewriteResume(Resume r);

    protected abstract void insertResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract int getResumeIndex(String uuid);

    protected abstract void removeResume(String uuid);
}
