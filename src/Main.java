import java.io.File;
import java.util.*;
import java.util.Map;

//Using this existing data,
// write a system that creates a list of “recent popular products” (high purchase rate and/or high user review). You can decide exactly how many products to show, but 2 to 3 is a good starting point.

//Secondly, create a solution to recommend individual products to a user base on their current session data. CurrentUserSession.txt indicates which product the user is currently looking at.
// Think of “frequently bought together” on amazon, or recommendations after watching a movie on Netflix. (Users who like products from a given genre will usually like more products from the same genre.)
//In both parts, the system needs only be a simple text interface (console/terminal), it must read from the files and display the relevant outputs for each user in CurrentUserSession.txt. You do not need to write any website code or user interfaces.

public class Main {
    public static void main(String[] args) throws Exception {

        // This is our DB object, handling all the files. So we do not have to load our TXT files from storage more than once.

        Database database = new Database();

        //System One, generates recent popular products (Highly rated, highly purchased by users). //

        Api api = new Api(database);

        api.highestRatedProducts(3);

        api.generateRecommendations();


    }// end main

}


