package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public final void update(Resume r) {
        LOG.info("update " + r);
        updateResume(r, checkResumeForExistence(r.getUuid(), true));
    }

    @Override
    public final void save(Resume r) {
        LOG.info("save " + r);
        saveResume(r, checkResumeForExistence(r.getUuid(), false));
    }

    @Override
    public final Resume get(String uuid) {
        LOG.info("get " + uuid);
        return getResume(checkResumeForExistence(uuid, true));
    }

    @Override
    public final void delete(String uuid) {
        LOG.info("delete " + uuid);
        deleteResume(checkResumeForExistence(uuid, true));
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> listStorage = getStorageAsList();
        Collections.sort(listStorage);
        return listStorage;
    }

    private SK checkResumeForExistence(String uuid, boolean mustExist) {
        SK searchKey = getSearchKey(uuid);
        boolean isExist = isResumeExist(searchKey);
        if (isExist ^ mustExist) {
            if (isExist) {
                LOG.warning("Resume with uuid = " + uuid + " already exists");
                throw new ExistStorageException(uuid);
            }
            LOG.warning("Resume with uuid = " + uuid + " doesn't exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isResumeExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateResume(Resume r, SK searchKey);

    protected abstract void saveResume(Resume r, SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract List<Resume> getStorageAsList();
}