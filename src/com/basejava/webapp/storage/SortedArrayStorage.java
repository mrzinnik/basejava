package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based sorted storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void insertElement(Resume r, int index) {
        int insertionPoint = Math.abs(index + 1);
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = r;
    }
}
