package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private static final String[] UUIDS = {"uuid0", "uuid1", "uuid2"};

    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (String uuid : UUIDS) {
            storage.save(new Resume(uuid));
        }
    }

    @Test
    public void clear() throws NoSuchFieldException, IllegalAccessException {
        storage.clear();
        for (Resume r : getStorage(storage)) {
            assertNull(r);
        }
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws NoSuchFieldException, IllegalAccessException {
        Resume resumeToUpdate = new Resume("uuid2");
        storage.update(resumeToUpdate);
        assertEquals(resumeToUpdate, getStorage(storage)[2]);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() throws NoSuchFieldException, IllegalAccessException {
        Resume resumeToSave = new Resume("uuid3");
        storage.save(resumeToSave);
        assertEquals(resumeToSave, getStorage(storage)[3]);
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
    public void get() throws NoSuchFieldException, IllegalAccessException {
        assertEquals(getStorage(storage)[0], storage.get("uuid0"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() throws NoSuchFieldException, IllegalAccessException {
        Resume resumeToDelete = getStorage(storage)[0];
        storage.delete("uuid0");
        Resume[] resumeStorage = getStorage(storage);
        for (Resume r : resumeStorage) {
            assertNotEquals(resumeToDelete, r);
        }
        assertEquals(2, storage.size());
    }

    @Test
    public void getAll() throws NoSuchFieldException, IllegalAccessException {
        Resume[] resumeStorage = getStorageWithoutNulls(storage);
        Resume[] resumeStorageReceived = storage.getAll();
        assertEquals(resumeStorage.length, resumeStorageReceived.length);
        for (int i = 0; i < resumeStorage.length; i++) {
            assertEquals(resumeStorage[i], resumeStorageReceived[i]);
        }
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    private Resume[] getStorage(Storage storage) throws NoSuchFieldException, IllegalAccessException {
        return (Resume[]) storage.getClass().getSuperclass().getDeclaredField("storage").get(storage);
    }

    private Resume[] getStorageWithoutNulls(Storage storage) throws NoSuchFieldException, IllegalAccessException {
        Resume[] resumeStorage = getStorage(storage);
        int size = 0;
        for (Resume r : resumeStorage) {
            if (r == null) {
                break;
            }
            size++;
        }
        return Arrays.copyOf(resumeStorage, size);
    }
}