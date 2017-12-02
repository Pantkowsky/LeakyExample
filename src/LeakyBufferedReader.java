
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class LeakyBufferedReader {
    private BufferedReader reader;
    private String longString;

    /**
     * The constructor.
     * Creates the instance of {@link LeakyBufferedReader}
     * and a new {@link #longString}
     */
    public LeakyBufferedReader(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            builder.append(String.valueOf(i));
        }
        this.longString = builder.toString();
    }

    /**
     * Creates the leak by opening the {@link BufferedReader},
     * but forgetting to close it after the job is done. This however
     * has been fixed in Java 8, where if the stream is not closed explicitly
     * by the developer, it will be closed automatically after try-catch
     * block gets executed.
     */
    void leak(){
        try{
            reader = new BufferedReader(new StringReader(longString));
            int line = 0;
            for (String x = reader.readLine(); x != null; x = reader.readLine())
            {
                if(line % 2 == 0) System.out.println("The string is: ".concat(x));
                line++;
            }
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    /**
     * To avoid creating the memory leak when working with IO operations, such as
     * with {@link BufferedReader}, after finishing executing the operation the object
     * was tasked with, the reader needs to be closed. It can be done by adding a
     * FINALLY clause after try-catch block and calling .close().
     */
    void fixLeak(){
        try{
            reader.close();
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
