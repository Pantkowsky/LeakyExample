/**
 * Example memory leaks demonstration done for the course of
 * Automated Software Analysis held at IT University of Copenhagen.
 * Project done by Adam Pantkowski and Coralie Boucheron.
 */
public class Main {
    private static final LeakyHashMap map = new LeakyHashMap();
    private static final LeakyHashMap cache = new LeakyHashMap();
    private static final AutoboxingLeak leak = new AutoboxingLeak();
    private static final LeakyString leakyString = new LeakyString();
    private static final LeakyBufferedReader leakyBufferedReader = new LeakyBufferedReader();
    private static final DummyButton button = new DummyButton();
    private static LeakyThread thread;
    private static  LeakyConnection connection;
    private static LeakyOuterClass.LeakyInnerClass leakyInnerClass;

//    private static final LeakyOuterClass parentClass = new LeakyOuterClass();

    /**
     * Main method to introduce and benchmark various types of memory leaks.
     * Benchmarking done with JVM heap size of 145mb.
     * @param args command-line argument
     */
    public static void main(String[] args) {
        try{
            Thread thread = Thread.currentThread();
            thread.sleep(20000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        benchmark(5);

        //simulating ongoing cycle of the program
        Thread thread = Thread.currentThread();
        try{
            System.out.println("starting sleeping thread");
            thread.sleep(1000000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    /**
     * Benchmarking method. Takes as argument a flag indicating
     * which leaky example to run.
     * @param code flag indicating which leaky class to instantiate
     */
    private static void benchmark(int code){
        switch (code){
            case 0:
                for (int i = 0; i < 1000; i++) {
                    System.out.println(i+"");
                    button.setupLeakingListener();
                }
                break;
            case 1:
                for(int i = 0; i < 100; i++){
                    LeakyObserver leakyObserver = new LeakyObserver();
                    leakyObserver.leak();
                }
                break;
            case 2:
                for(int i = 0; i < 100; i++){
                    leakyString.leakEvenMore();
                    System.out.println("Leaking strings ...");
                }
                break;
            case 3:

                leakyBufferedReader.leak();
                break;
            case 4:
                for (int i = 0; i < 1000; i++) {
                    leakyInnerClass = LeakyOuterClass.LeakyInnerClass.getLeakingInnerClass();
                }
                break;
            case 5:
                for (int i = 0; i < 2000; i++) {
//                    LeakyThread thread = new LeakyThread();
                    thread = new LeakyThread();
                    thread.getThreadName();
                }
//                thread.fixLeak(); <-- call to thread.start() to fix the leak
                break;
            case 6:
                leak.show();
                break;
            case 7:
                cache.init();
                cache.display();
                break;
            case 8:
                for (int i = 0; i < 10000; i++) {
                    map.store("Adam".concat(i+""), "Ghost Buster");
                    map.store("Coralie".concat(i+""), "Dinosaur Race Driver");
                    System.out.println(map.getKey(new LeakyHashKey("Coralie".concat(i+""))));
                    System.out.println(map.getKey(new LeakyHashKey("Adam".concat(i+""))));
                }
                break;
            case 9:
                for (int i = 0; i < 5; i++) {
                    System.out.println(i+"");
                    connection = new LeakyConnection();
                    connection.leak();
//                    connection.fixLeak(); //<- closes the connection
                }
                break;
        }
    }
}
