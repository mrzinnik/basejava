package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        if (!isResumeExist(r.getUuid())) {
            throw new NotExistStorageException(r.getUuid());
        }
        rewriteResume(r);
    }

    @Override
    public final void save(Resume r) {
        if (isResumeExist(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        }
        insertResume(r);
    }

    @Override
    public final Resume get(String uuid) {
        if (!isResumeExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(uuid);
    }

    @Override
    public final void delete(String uuid) {
        if (!isResumeExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        removeResume(uuid);
    }

    protected abstract boolean isResumeExist(String uuid);

    protected abstract void rewriteResume(Resume r);

    protected abstract void insertResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void removeResume(String uuid);
}
