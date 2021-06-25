import java.io.File;
import java.util.List;
import java.util.Scanner;

//Using this existing data, write a system that creates a list of “recent popular products” (high purchase rate and/or high user review). You can decide exactly how many products to show, but 2 to 3 is a good starting point.
//Secondly, create a solution to recommend individual products to a user base on their current session data. CurrentUserSession.txt indicates which product the user is currently looking at.
// Think of “frequently bought together” on amazon, or recommendations after watching a movie on Netflix. (Users who like products from a given genre will usually like more products from the same genre.)
//In both parts, the system needs only be a simple text interface (console/terminal), it must read from the files and display the relevant outputs for each user in CurrentUserSession.txt. You do not need to write any website code or user interfaces.

public class Main {
    public static void main(String[] args) throws Exception {

        // Grabs all the userdata.
        GetUsersData GetUsersData = new GetUsersData();

        //lets display all our users.
        for (int i = 0; i < GetUsersData.userList.size(); i++) {
            User user = (User) GetUsersData.userList.get(i);
          //  System.out.println("UserID: " + user.getUserID() + " With the name " + user.getName() +" Has Viewed " + user.getViewed() + " Has Purchased " + user.getPurchased());

        }

        FileReader fileReader = new FileReader("src/data/Users.txt");
        List userList = fileReader.getOutputList();

        String[] hello = (String[]) userList.get(0);

        //System.out.println(hello[3]);

        FileReader UserSessions = new FileReader("src/data/CurrentUserSession.txt");



        sessionTo(UserSessions.getOutputList(),3);




    }

    public static void displayRelevantOutputs(String o) throws Exception {

        FileReader products = new FileReader("src/data/Products.txt");

        List productList = products.getOutputList();

        String[] hello = (String[]) productList.get(Integer.parseInt(o));

        for (int i = 0; i < hello.length; i++) {

            System.out.println(hello[i]);
        }

    }

    public static void sessionTo(List sessionList, int userId) throws Exception {

        String[] dd = (String[]) sessionList.get(userId);


        //Display relevant outputs for each user in CurrentUserSessions.

        displayRelevantOutputs(dd[0]);
    }


}


