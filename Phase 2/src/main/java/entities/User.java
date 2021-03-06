package entities;


/**
 * A user with a username and password.
 */
public class User{

    /**
     * The username.
     */
    private final String username;
    /**
     * The password.
     */
    private final String password;

    /**
     * A new user with username and password. We are completely ignoring
     * password encryption in this example.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Return whether the password parameter matches this user's password.
     * @param password the password guess
     * @return whether the guess matches the real password.
     */
    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }

    /**
     * The username.
     */
    public String getUsername() {
        return username;
    }
}
