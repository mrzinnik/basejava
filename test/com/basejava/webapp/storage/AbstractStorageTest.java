package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected static final String[] UUIDS = {"uuid0", "uuid1", "uuid2", "uuid3", "uuidToSave", "dummy"};
    protected static final String[] NAMES = {"Alexander", "Boris", "Boris", "Nick", "Random", "Dummy"};

    protected Storage storage;
    protected List<Resume> exampleStorage = new ArrayList<>();

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < 4; i++) {
            exampleStorage.add(i, new Resume(UUIDS[i], NAMES[i]));
        }
        for (int i = 3; i >= 0; i--) {
            storage.save(exampleStorage.get(i));
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
        Resume updatedResume = new Resume(UUIDS[0], NAMES[5]);
        storage.update(updatedResume);
        assertSame(updatedResume, storage.get(UUIDS[0]));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUIDS[5], NAMES[5]));
    }

    @Test
    public void save() {
        Resume resumeToSave = new Resume(UUIDS[4], NAMES[4]);
        storage.save(resumeToSave);
        assertSame(resumeToSave, storage.get(UUIDS[4]));
        assertEquals(5, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUIDS[0], NAMES[0]));
    }

    @Test
    public void get() {
        assertSame(exampleStorage.get(0), storage.get(UUIDS[0]));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUIDS[5]);
    }

    @Test
    public void delete() {
        Resume resumeToDelete = exampleStorage.get(0);
        storage.delete(resumeToDelete.getUuid());
        for (Resume r : storage.getAllSorted()) {
            assertNotSame(resumeToDelete, r);
        }
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUIDS[5]);
    }

    @Test
    public void getAllSorted() {
        List<Resume> sortedStorage = storage.getAllSorted();
        assertEquals(exampleStorage.size(), sortedStorage.size());
        for (int i = 0; i < exampleStorage.size(); i++) {
            assertSame(exampleStorage.get(i), sortedStorage.get(i));
        }
    }

    @Test
    public void size() {
        assertEquals(4, storage.size());
    }
}