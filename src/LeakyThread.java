/**
 * Example of causing a memory leak with {@link Thread}
 */
public class LeakyThread extends Thread {
    private static Thread thread;

    /**
     * Creating a {@link Thread} without starting it causes a potential
     * memory leak due to bug in {@link ThreadGroup} which causes unstarted threads
     * not be eligible for collection
     */
    public LeakyThread(){
        thread = new Thread();
    }

    public String getThreadName(){
        return thread.getName();
    }

    /**
     * Fix to the memory leak by starting the thread
     */
    public void fixLeak(){
        thread.start();
    }
}
