import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apant on 28/11/2017.
 */
public class LeakyHashMap extends HashMap {
    static final ArrayList list = new ArrayList(1000);

    public LeakyHashMap(){}

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public boolean equals(Object object){
        return true;
    }
}
