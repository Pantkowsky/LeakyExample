/**
 * Example memory leaks demonstration done for the course of
 * Automated Software Analysis held at IT University of Copenhagen.
 * Project done by Adam Pantkowski and Coralie Boucheron.
 */
public class Main {

    /**
     * Main method to introduce and benchmark various types of memory leaks.
     * Benchmarking done with JVM heap size of 145mb.
     * @param args command-line argument
     */
    public static void main(String[] args) {
        benchmark(1);
        benchmark(2);
        benchmark(3);
    }

    /**
     * Benchmarking method. Takes as argument a flag indicating
     * which leaky example to run.
     * @param code flag indicating which leaky class to instantiate
     */
    private static void benchmark(int code){
        switch (code){
            case 1:
                for(int i = 0; i < 100; i++){
                    LeakyObserver leakyObserver = new LeakyObserver();
                    leakyObserver.leak();
                }
                break;
            case 2:
                for(int i = 0; i < 10; i++){
                    LeakyString leakyString = new LeakyString();
                    leakyString.leakEvenMore();
                    System.out.println("Leaking strings ...");
                }
                break;
            case 3:
                LeakyBufferedReader leakyBufferedReader = new LeakyBufferedReader();
                leakyBufferedReader.leak();
                break;
            case 4:
                break;
        }
    }
}