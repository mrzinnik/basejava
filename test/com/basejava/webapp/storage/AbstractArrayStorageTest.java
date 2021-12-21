package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private static final String[] UUIDS = {"uuid0", "uuid1", "uuid2"};

    private Storage storage;
    private Resume[] exampleStorage = new Resume[UUIDS.length];

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < UUIDS.length; i++) {
            exampleStorage[i] = new Resume(UUIDS[i]);
            storage.save(new Resume(UUIDS[i]));
        }
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.getAll().length);
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(exampleStorage[0]);
        assertEquals(exampleStorage[0], storage.get(UUIDS[0]));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        Resume resumeToSave = new Resume("uuid3");
        storage.save(resumeToSave);
        assertEquals(resumeToSave, storage.get("uuid3"));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid0"));
    }

    @Test(expected = StorageException.class)
    public void saveExistOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Storage overflow ahead of time");
        }
        storage.save(new Resume());
    }

    @Test
    public void get() {
        assertEquals(exampleStorage[0], storage.get(UUIDS[0]));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete(UUIDS[0]);
        for (Resume r : storage.getAll()) {
            assertNotEquals(exampleStorage[0], r);
        }
        assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] storageReceived = storage.getAll();
        assertEquals(exampleStorage.length, storageReceived.length);
        for (int i = 0; i < exampleStorage.length; i++) {
            assertEquals(exampleStorage[i], storageReceived[i]);
        }
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}