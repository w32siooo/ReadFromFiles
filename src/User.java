import java.util.ArrayList;

public class User {
    public int UserID;
    String name;
    ArrayList viewed = new ArrayList<Integer>();
    ArrayList purchased = new ArrayList<Integer>();

    public User(String userID, String name, String viewed2, String purchased2) {
        // Parsing the userID to an Int.
        this.UserID = Integer.parseInt(userID);
        this.name = name;

        //Converting the viewed list to a list.
        String[] arrSplit = viewed2.split(";");
        for (int i = 0; i < arrSplit.length; i++) {
            this.viewed.add(arrSplit[i]);
        }

        String[] arrSplit2 = purchased2.split(";");
        for (int i = 0; i < arrSplit2.length; i++) {
            this.purchased.add(arrSplit2[i]);
        }
    }

    public int getUserID() {
        return UserID;
    }

    public String getName() {
        return name;
    }

    public ArrayList getViewed() {
        return viewed;
    }

    public ArrayList getPurchased() {
        return purchased;
    }
}
