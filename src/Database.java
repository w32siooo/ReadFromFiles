import java.util.List;

public class Database <T>{

    //The lists that will hold the data from the text files.
    private List <T> userList;
    private List <T> sessionList;
    private List <T> productList;

    public Database() throws Exception {
        FileReader Users = new FileReader("src/data/Users.txt");
        this.userList = Users.getOutputList();

        FileReader CurrentUserSession = new FileReader("src/data/CurrentUserSession.txt");
        this.sessionList = CurrentUserSession.getOutputList();

        FileReader Products = new FileReader("src/data/Products.txt");
        this.productList = Products.getOutputList();
    }

    public List<T> getUserList() {
        return userList;
    }

    public List<T> getSessionList() {
        return sessionList;
    }

    public List<T> getProductList() {
        return productList;
    }

}
