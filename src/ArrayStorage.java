import java.util.Arrays;

/**
 * Array based storage for Resumes

   1. Arrays.fill(storage, 0, size - 1, null); попробуй внимательней ознакомиться с документацией к методу fill и его аргументом toIndex
   2. if (!uuid.equals(storage[index].uuid)) {
   index++;
   проще проверять наоборот на равенство. Если равно, то сделать return storage[index];
   а return null; возвращать за пределами цикла
   3. if (storage[i] == get(uuid)) {
   объекты сравнивай через equals
   4. делай break после size--;
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
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
            if (uuid.equals(storage[index].uuid)) {
                return storage[index];
            } else {
                index++;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (get(uuid).equals(storage[i])) {
                System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                storage[size - 1] = null;
                size--;
                break;
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
