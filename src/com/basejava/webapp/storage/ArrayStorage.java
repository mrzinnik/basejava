package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int STORAGE_LIMIT = 10_000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findResume(uuid);
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume with uuid = " + uuid + " has been updated");
        } else {
            System.out.println("Resume with uuid = " + uuid + " doesn't exist");
        }
    }

    public void save(Resume r) {
        if (findResume(r.getUuid()) == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Storage is full");
            }
        } else {
            System.out.println("Resume with uuid = " + r.getUuid() + " already exists");
        }
    }

    public Resume get(String uuid) {
        int index = findResume(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume with uuid = " + uuid + " doesn't exist");
        return null;
    }

    public void delete(String uuid) {
        int index = findResume(uuid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume with uuid = " + uuid + " doesn't exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
