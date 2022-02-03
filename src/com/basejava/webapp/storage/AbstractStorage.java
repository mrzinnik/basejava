package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        int resumeIndex = getResumeIndex(r.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        rewriteResume(r, resumeIndex);
    }

    @Override
    public final void save(Resume r) {
        int resumeIndex = getResumeIndex(r.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        insertResume(r, resumeIndex);
    }

    @Override
    public final Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(uuid, resumeIndex);
    }

    @Override
    public final void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        removeResume(uuid, resumeIndex);
    }

    protected abstract void rewriteResume(Resume r, int index);

    protected abstract void insertResume(Resume r, int index);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract int getResumeIndex(String uuid);

    protected abstract void removeResume(String uuid, int index);
}
