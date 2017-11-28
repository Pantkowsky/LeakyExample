import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

public class LeakyObserver {
    private Subscription subscription;

    public LeakyObserver(){}

    /**
     * Example method to present the memory leak
     * that might happen when setting up observables
     */
    public void leak(){
        showLeak();
    }

    /**
     * Subscribes to Observable and starts to emit the values
     */
    private void showLeak(){
        subscription = createLeak().subscribe(System.out::println);
    }

    /**
     * Sets up the Observable
     * @return Observable
     */
    private Observable<String> createLeak(){
        Integer[] x = new Integer[1000];
        for(int i = 0; i < x.length; i++){
            x[i] = i;
        }

        return Observable.just(x)
                .flatMap(new Func1<Integer[], Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer[] ints) {
                        return Observable.from(ints);
                    }
                })
                .map(s -> s.toString().concat(" emitted"));
    }

    /**
     * Unsubscribes the observable cleaning up the leak
     */
    private void clearLeak(){
        subscription.unsubscribe();
    }
}
