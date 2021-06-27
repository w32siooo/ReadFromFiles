import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//A simple class that reads data from a text file and returns an arraylist of String containing the inputs
//Line by line.

public class FileReader {
    List outPut = new ArrayList<String[]>();

    public FileReader(String path) throws Exception {

// Open the file
        FileInputStream fstream = new FileInputStream(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {

            String strLine;

//Read file line by line.
            while ((strLine = br.readLine()) != null) {

                // Split by delimiter.

                String[] arrSplit = strLine.split(",");

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
