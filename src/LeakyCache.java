import java.util.HashMap;
import java.util.Map;

public class LeakyCache {

    private Map<String,String> map= new HashMap<String,String>();

    public void initCache() {
        map.put("Coralie", "Works as professional Pokemon Trainer");
        map.put("Adam", "Works as Freelance Chippendale");
        map.put("Big Shaq", "Works as Post Doc Mathematician");
        System.out.println("The ting goes skraa!");
    }

    public Map<String,String> getCache() {
        return map;
    }

    public void forEachDisplay(){

        for(String key : map.keySet()) {
            String val = map.get(key);
            System.out.println(key + " :: "+ val);
        }

    }
}
