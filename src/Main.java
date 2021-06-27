import java.io.File;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map;

//Using this existing data,
// write a system that creates a list of “recent popular products” (high purchase rate and/or high user review). You can decide exactly how many products to show, but 2 to 3 is a good starting point.

//Secondly, create a solution to recommend individual products to a user base on their current session data. CurrentUserSession.txt indicates which product the user is currently looking at.
// Think of “frequently bought together” on amazon, or recommendations after watching a movie on Netflix. (Users who like products from a given genre will usually like more products from the same genre.)
//In both parts, the system needs only be a simple text interface (console/terminal), it must read from the files and display the relevant outputs for each user in CurrentUserSession.txt. You do not need to write any website code or user interfaces.

public class Main {
    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {

            Scanner scanner = new Scanner(new InputStreamReader(System.in));

            Database database = new Database();

            Api api = new Api(database);

            // This is our DB object, handling all the files. So we do not have to load our TXT files from storage more than once.

            System.out.println("Type 1 for recent popular products. \nType 2 for recommendations based on user data.\nType anything else to exit.");

            String input = scanner.nextLine();

            if (input.contains("1")) {

                api.highestRatedProducts(3);

            } else if (input.contains("2")) {

                System.out.println("Specify which user to look at.");
                String[] userSessions = api.printUserSessions();

                System.out.println("Type either of the following userIDs" + Arrays.toString(userSessions));

                String input2 = scanner.nextLine();

                api.generateRecommendations(input2);

            } else {
                running = false;
            }

            //System One, generates recent popular products (Highly rated, highly purchased by users). //

        }
    }// end main

}


