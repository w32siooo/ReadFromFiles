import java.util.List;

public class Database {

    List userList;
    List sessionList;
    List productList;


    public Database() throws Exception {
        FileReader Users = new FileReader("src/data/Users.txt");
        this.userList = Users.getOutputList();

        FileReader CurrentUserSession = new FileReader("src/data/CurrentUserSession.txt");
        this.sessionList = CurrentUserSession.getOutputList();

        FileReader Products = new FileReader("src/data/Products.txt");
        this.productList = Products.getOutputList();
    }

    public List getUserList() {
        return userList;
    }

    public List getSessionList() {
        return sessionList;
    }

    public List getProductList() {
        return productList;
    }

}
