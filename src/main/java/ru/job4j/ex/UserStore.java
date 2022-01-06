package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(login)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            throw new UserNotFoundException("No user with this name was found.");
        }
        return user;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (user.isValid()) {
            return true;
        } else {
            throw new UserInvalidException("User is not valid.");
        }
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
