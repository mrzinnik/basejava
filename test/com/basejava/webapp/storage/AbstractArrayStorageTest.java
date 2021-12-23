package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private static final String[] UUIDS = {"uuid0", "uuid1", "uuid2", "uuidToSave", "dummy"};

    private Storage storage;
    private Resume[] exampleStorage = new Resume[3];

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < 3; i++) {
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
        storage.update(new Resume(UUIDS[4]));
    }

    @Test
    public void save() {
        Resume resumeToSave = new Resume(UUIDS[3]);
        storage.save(resumeToSave);
        assertEquals(resumeToSave, storage.get(UUIDS[3]));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUIDS[0]));
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
        storage.get(UUIDS[4]);
    }

    @Test
    public void delete() {
        storage.delete(UUIDS[0]);
        for (Resume r : storage.getAll()) {
            assertNotEquals(exampleStorage[0], r);
        }
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUIDS[4]);
    }

    @Test
    public void getAll() {
        assertArrayEquals(exampleStorage, storage.getAll());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}