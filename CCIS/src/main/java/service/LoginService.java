package service;

import model.Doctor;
import model.User;
import useful_beans.UserToLogin;

import java.util.HashSet;
import java.util.Optional;

public class LoginService {

    private HashSet<User> users = new HashSet<User>();

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public boolean login(UserToLogin userToLogin){
        Optional<User> user;
        user = users.stream().filter(user1 -> user1.getEmail().equals(userToLogin)
            && user1.getPassword().equals(userToLogin.password))
                .findFirst();
        if(user.isPresent())
            return true;

        return false;
    }

    public Optional<User> selectUserById(String id){
        return users.stream().filter(user -> user.getEmail().equals(id))
                .findFirst();
    }
}
