package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        Integer index = findResume(resume.getUuid());
        if (Objects.nonNull(index)) {
            storage[index] = resume;
            System.out.println("Resume with uuid = " + storage[index].getUuid() + " is updated");
        } else {
            System.out.println("Resume with this uuid doesn't exist");
        }
    }

    public void save(Resume r) {
        if (Objects.isNull(findResume(r.getUuid()))) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Storage is full");
            }
        } else {
            System.out.println("Resume with this uuid already exists");
        }
    }

    public Resume get(String uuid) {
        Integer index = findResume(uuid);
        if (Objects.nonNull(index)) {
            return storage[index];
        } else {
            System.out.println("Resume with this uuid doesn't exist");
            return null;
        }
    }

    public void delete(String uuid) {
        Integer index = findResume(uuid);
        if (Objects.nonNull(index)) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume with this uuid doesn't exist");
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

    private Integer findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return null;
    }
}
