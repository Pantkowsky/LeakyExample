import java.util.HashMap;
import java.util.Map;

/**
 * Two examples of how memory leaks can be caused
 * when working with custom HashMaps
 */
public class LeakyHashMap {

    /**
     * Example of memory leak caused by caching. The memory leak occurs
     * due to the internal map data structure. The class displays information
     * about a person's job. Once the info is displayed, there is no need for further
     * caching. When implementing your own cache, it is necessary to clear it after
     * displaying wanted information as though objects in cache are not needed anymore,
     * they can’t be GC'ed, as map holds a strong reference to them.
     */
    private static final Map<String,String> map = new HashMap<>();

    public void init() {
        map.put("Coralie", "IT Consultant");
        map.put("Adam", "Software Developer");
    }

    public void clearLeak(){
        map.clear();
    }

    public void display(){
        for(String key : map.keySet()) {
            String val = map.get(key);
            System.out.println(key + " :: "+ val);
        }
    }

    /**
     * Example of how implementing a custom access key for hashmaps
     * can leak memory. When Implementing custom keys it is necessary
     * to provide {@link #hashCode()} and {@link #equals(Object)}
     * implementations. Without doing so the key and value stored in map
     * can’t be retrieved later, as the map get() method checks hashcode() and equals().
     * Therefore that entry is not able to be GC'ed, as the map has a reference to it,
     * but application can’t access it.
     */
    private Map<LeakyHashKey, String> leakyMap = new HashMap<>();

    public String getKey(LeakyHashKey key){
        return leakyMap.get(key);
    }

    public void store(String key, String value){
        leakyMap.put(new LeakyHashKey(key), value);
    }
}
