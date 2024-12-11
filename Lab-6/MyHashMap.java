import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap<K, V> {
  private static int DEFAULT_INITIAL_CAPACITY = 4;
  private static int MAXIMUM_CAPACITY = 1 << 30;
  private int capacity;
  private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;
  private float loadFactorThreshold;
  private int size = 0;
  MyMap.Entry<K, V>[] table;

  // Special marker to represent deleted entries
  private static final MyMap.Entry<?, ?> TOMBSTONE = new MyMap.Entry<>(null, null);

  // Construct a map with the default capacity and load factor
  public MyHashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
  }

  // Construct a map with the specified initial capacity and default load factor
  public MyHashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
  }

  // Construct a map with the specified initial capacity and load factor
  public MyHashMap(int initialCapacity, float loadFactorThreshold) {
    this.capacity = initialCapacity;
    this.loadFactorThreshold = loadFactorThreshold;
    table = new MyMap.Entry[capacity];
  }

  // Clear all entries in the map
  public void clear() {
    size = 0;
    removeEntries();
  }

  // Check if the map contains the specified key
  public boolean containsKey(K key) {
    return get(key) != null;
  }

  // Check if the map contains the specified value
  public boolean containsValue(V value) {
    for (int i = 0; i < table.length; i++)
      if (table[i] != null && table[i].value.equals(value))
        return true;
    return false;
  }

  // Return a set of entries in the map
  public java.util.Set<MyMap.Entry<K, V>> entrySet() {
    java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<>();
    for (int i = 0; i < capacity; i++)
      if (table[i] != null && table[i] != TOMBSTONE)
        set.add(table[i]);
    return set;
  }

  // Check if the map is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Return a set of keys in the map
  public java.util.Set<K> keySet() {
    java.util.Set<K> set = new java.util.HashSet<>();
    for (int i = 0; i < capacity; i++)
      if (table[i] != null && table[i] != TOMBSTONE)
        set.add(table[i].key);
    return set;
  }

  // Remove the entry for the specified key
  public void remove(K key) {
    int i = hash(key.hashCode());
    int j = 0;

    while (table[i] != null && j < capacity) {
      if (table[i] != TOMBSTONE && table[i].key != null && table[i].key.equals(key)) {
        table[i] = (MyMap.Entry<K, V>) TOMBSTONE; // Mark as deleted
        size--;
        return;
      }
      i = (i + (j * j)) % capacity; // Quadratic probing
      j++;
    }
  }

  // Return the number of mappings in this map
  public int size() {
    return size;
  }

  // Return a set of values in the map
  public java.util.Set<V> values() {
    java.util.Set<V> set = new java.util.HashSet<>();
    for (int i = 0; i < capacity; i++)
      if (table[i] != null && table[i] != TOMBSTONE)
        set.add(table[i].value);
    return set;
  }

  public void displayAllValues() {
    System.out.println("Values in the map:");
    for (int i = 0; i < capacity; i++) {
        if (table[i] != null && table[i] != TOMBSTONE) {
            System.out.println(table[i].value);
        }
    }
}

  // Hash function
  private int hash(int hashCode) {
    return supplementalHash(hashCode) & (capacity - 1);
  }

  // Ensure the hashing is evenly distributed
  private static int supplementalHash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }

  // Remove all entries from the table
  private void removeEntries() {
    for (int i = 0; i < capacity; i++)
      table[i] = null;
  }

  // Rehash the map
  private void rehash() {
    java.util.Set<MyMap.Entry<K, V>> set = entrySet(); // Get entries
    capacity <<= 1; // Double capacity
    table = new MyMap.Entry[capacity]; // Create a new hash table

    size = 0; // Reset size

    for (MyMap.Entry<K, V> entry : set) {
      put(entry.getKey(), entry.getValue()); // Store to new table
    }
  }

  // String representation of the map
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null && table[i] != TOMBSTONE && table[i].key != null)
        builder.append(table[i].toString());
    }
    return builder.append("]").toString();
  }

  // Get the value for a specified key
  public V get(K key) {
    int i = hash(key.hashCode());
    int j = 0;
    while (table[i] != null && j < capacity) {
      if (table[i] != TOMBSTONE && table[i].key != null && table[i].key.equals(key)) {
        return table[i].value;
      }
      i = (i + (j * j)) % capacity; // Quadratic probing
      j++;
    }
    return null; // Not found
  }

  // Add a key-value pair into the map
  public V put(K key, V value) {
    if (size >= capacity * loadFactorThreshold) {
      rehash(); // Rehash if load factor is exceeded
    }

    int i = hash(key.hashCode());
    int j = 0;

    while (table[i] != null && table[i] != TOMBSTONE && table[i].key != null) {
      if (table[i].key.equals(key)) { // Key already exists, update value
        V oldValue = table[i].value;
        table[i].value = value;
        return oldValue;
      }
      i = (i + (j * j)) % capacity; // Quadratic probing
      j++;
    }

    table[i] = new MyMap.Entry<>(key, value); // Insert new key-value pair
    size++;
    return null;
  }
}