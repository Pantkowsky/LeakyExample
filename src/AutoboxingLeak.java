/**
 * Memory leak caused by unnecessary auto-boxing.
 * Due to auto-boxing, sum=sum+l; creates a new object in every iteration,
 * so 1000 unnecessary objects will be created.
 * Leak can be avoided by using primitive values instead.
 */
public class AutoboxingLeak {
    private static Long sum;

    public AutoboxingLeak(){}

    private long addIncremental(long l) {
        sum = 0L;
        sum = sum + l;
        System.out.println(sum);
        return sum;
    }

    public void show(){
        for(long i = 0; i<10000000; i++){
            this.addIncremental(i);
        }
    }
}
