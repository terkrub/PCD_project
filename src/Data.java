 
import java.util.HashMap;
import java.util.Stack;


public class Data {
    boolean loginFlag = false;
    String username;
    double money = 0.00f;
    HashMap<String,Product> products = new HashMap<>();
    Stack<History> history = new Stack();
}
