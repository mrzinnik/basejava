import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Storage is full");
        }
    }

    Resume get(String uuid) {
        int index = 0;
        while (index < size) {
            if (!storage[index].uuid.equals(uuid)) {
                index++;
            } else {
                break;
            }
        }
        if (index < size) {
            return storage[index];
        } else {
            return null;
        }
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == get(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
