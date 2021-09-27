/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        int index = 0;
        while (index < size()) {
            if (uuid.equals(storage[index].uuid)) {
                break;
            }
            index++;
        }
        if (index < size()) {
            return storage[index];
        } else {
            return null;
        }
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i] == get(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size() - 1 - i);
                storage[size() - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumesWithoutNull = new Resume[size()];
        System.arraycopy(storage, 0, resumesWithoutNull, 0, size());
        return resumesWithoutNull;
    }

    int size() {
        int storageSize = 0;
        while (storage[storageSize] != null) {
            storageSize++;
        }
        return storageSize;
    }
}
