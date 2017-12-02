import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Example of potential memory leak during network connection.
 */
public class LeakyConnection {
    private URL url;
    private InputStream is;

    /**
     * Instance constructor of {@link LeakyConnection}.
     * Will create a new {@link URL} link.
     */
    public LeakyConnection(){
        try{
            url = new URL("https://google.com");
        }catch(MalformedURLException mue){
            mue.printStackTrace();
        }
    }

    /**
     * Leak happens due to opening the network connection
     * and the {@link InputStream} to parse it, however not
     * explicitly closing it after performed task is finished.
     */
    public void leak(){
        try{
            URLConnection connection = url.openConnection();
            is = connection.getInputStream();
            System.out.println(is.toString());
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    /**
     * Leak can be easily fixed by remembering to close
     * the connection after finishing the parsing. Can be
     * simply included in the "finally" part of the
     * try-catch block.
     */
    void fixLeak(){
        try{
            is.close();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
