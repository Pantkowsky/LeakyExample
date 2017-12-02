import java.util.HashMap;
import java.util.Map;

public class LeakyHashMap {

    /**
     * Example of memory leak caused by caching. The memory leak occurs
     * due to the internal map data structure. The class displays information
     * about a person's job. Once the info is displayed, there is no need for further
     * caching. When implementing your own cache, it is necessary to clear it after
     * displaying wanted information as though objects in cache are not needed anymore,
     * they can’t be GC'ed, as map holds a strong reference to them.
     */
    private Map<String,String> map= new HashMap<>();

    public void init() {
        map.put("Coralie", "Works as professional Pokemon Trainer");
        map.put("Adam", "Works as Freelance Chippendale");
        map.put("Big Shaq", "Works as Post-Doc Mathematician");
        System.out.println("The ting goes skraa!");
    }

    public Map<String,String> getCache() {
        return map;
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
