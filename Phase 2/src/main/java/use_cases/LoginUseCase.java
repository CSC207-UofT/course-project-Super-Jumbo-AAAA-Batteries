package use_cases;
import entities.UserList;
import entities.User;
import gateway.LoadingManager;
import gateway.LoginInputBoundary;
import gateway.UserReadWriter;

import java.io.IOException;

public class LoginUseCase implements LoginInputBoundary {

    /**
     * A list of users organized by username.
     */
    private final UserList users;

    /**
     * Serializes and deserializes list of users
     */
    public LoginUseCase() {
        this.users = new UserList();
    }

    UserReadWriter readWriter = new UserReadWriter();

    /**
     * / The "output" of this use case.
     */
    // Note: This could also be a fully-fledged class if we need to return
    // information to the controller.
    public enum LoginResult {
        SUCCESS, FAILURE
    }

    public LoginUseCase(UserList users) {
        this.users = users;
        try {
            readWriter.saveToFile("users.ser", users);
        } catch (IOException e) {
            System.out.println("User list did not save.");
        }
    }

    /**
     * Run the login use case.
     * @param username the username
     * @param password the password attempt
     * @return whether the attempt matches the password associated with username
     */
    public LoginResult logIn(String username, String password) {
        User user = users.getUser(username);
        if (user.passwordMatches(password)) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.FAILURE;
        }
    }

    /**
     * Add the user with the given username and password to the UserList
     * @param username the username
     * @param password the password
     */
    public static void addUser(String username, String password) {
        User user = new User(username, password);
        UserList.add(user);
    }

    /**
     * Creates a new UserList
     * @return the new UserList object
     */
    public static UserList newUserList() {
        UserList users = new UserList();
        return users;
    }

    /**
     * Load and return the UserList from the corresponding csv file
     * @return the loaded UserList object
     */
    public static UserList loadUserList(){
        UserList users = new UserList();
        LoadingManager.UserListLoader();
        return users;
    }
}