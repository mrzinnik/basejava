package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based sorted storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume with uuid = " + r.getUuid() + " already exists");
        } else if (size == storage.length) {
            System.out.println("Storage is full");
        } else {
            int insertionPoint = Math.abs(index + 1);
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
            storage[insertionPoint] = r;
            size++;
        }
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

/*    private Resume[] getSortedStorage() {
        Resume[] storageToBeSorted = Arrays.copyOf(storage, storage.length);
        for (int i = 0; i < size; i++) {
            Resume r = storageToBeSorted[i];
            int j = Math.abs(Arrays.binarySearch(storageToBeSorted, 0, i, r) + 1);
            System.arraycopy(storageToBeSorted, j, storageToBeSorted, j + 1, i - j);
            storageToBeSorted[j] = r;
        }
        return storageToBeSorted;
    }*/
}
