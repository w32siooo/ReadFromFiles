import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    List outPut = new ArrayList<String[]>();

    public FileReader(String path) throws Exception {

// Open the file
        FileInputStream fstream = new FileInputStream(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {

            String strLine;

//Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                //System.out.println (strLine);

                // Split by delimiter.

                String[] arrSplit = strLine.split(", ");

                this.outPut.add(arrSplit);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

//Close the input stream
        fstream.close();
    }

    public List getOutputList() {
        return outPut;
    }
}
