import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetUsersData {

    List userList = new ArrayList<User>();

    public GetUsersData() throws Exception {

// Open the file
        FileInputStream fstream = new FileInputStream("src/data/Users.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {

            String strLine;

//Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                //System.out.println (strLine);

                // Split by delimiter.

                String[] arrSplit = strLine.split(", ");

                this.userList.add(new User(arrSplit[0], arrSplit[1], arrSplit[2], arrSplit[3]));

                for (int i = 0; i < arrSplit.length; i++) {

                    // Print out each instance for good measure.

                    // System.out.println(arrSplit[i]);
                }

            }
        }

//Close the input stream
        fstream.close();
    }
}
