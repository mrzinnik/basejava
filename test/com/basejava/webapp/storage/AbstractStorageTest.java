package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected static final String[] UUIDS = {"uuid0", "uuid1", "uuid2", "uuid3", "uuidToSave", "dummy"};
    protected static final String[] NAMES = {"Alexander", "Boris", "Boris", "Nick", "Random", "Dummy"};

    protected Storage storage;
    protected Resume[] exampleStorage = new Resume[4];

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < exampleStorage.length; i++) {
            exampleStorage[i] = new Resume(UUIDS[i], NAMES[i]);
            storage.save(new Resume(UUIDS[exampleStorage.length - 1 - i], NAMES[exampleStorage.length - 1 - i]));
        }
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.getAllSorted().size());
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(exampleStorage[0]);
        assertEquals(exampleStorage[0], storage.get(UUIDS[0]));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUIDS[5], NAMES[5]));
    }

    @Test
    public void save() {
        Resume resumeToSave = new Resume(UUIDS[4], NAMES[4]);
        storage.save(resumeToSave);
        assertEquals(resumeToSave, storage.get(UUIDS[4]));
        assertEquals(5, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUIDS[0], NAMES[0]));
    }

    @Test
    public void get() {
        assertEquals(exampleStorage[0], storage.get(UUIDS[0]));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUIDS[5]);
    }

    @Test
    public void delete() {
        storage.delete(UUIDS[0]);
        for (Resume r : storage.getAllSorted()) {
            assertNotEquals(exampleStorage[0], r);
        }
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUIDS[5]);
    }

    @Test
    public void getAllSorted() {
        assertArrayEquals(exampleStorage, storage.getAllSorted().toArray(new Resume[0]));
    }

    @Test
    public void size() {
        assertEquals(4, storage.size());
    }
}