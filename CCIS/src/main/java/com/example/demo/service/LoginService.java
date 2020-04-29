package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.model.User;
import com.example.demo.useful_beans.UserToLogin;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
@Service
public class LoginService {

    private HashSet<User> users;

    public LoginService() {
        User doctor = new Doctor(10000, "nikola@gmail.com","123","Nikola","Stojanovic","Pwuskinova 4",
                "Beograd","Srbija","0111111111","12");
        this.users = new HashSet<User>();
        users.add(doctor);
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public boolean login(UserToLogin userToLogin){
        Optional<User> user;
        user = users.stream().filter(user1 -> user1.getEmail().equals(userToLogin.email)
            && user1.getPassword().equals(userToLogin.password))
                .findAny();
        if(user.isPresent())
            return true;

        return false;
    }

    public Optional<User> selectUserById(String id){
        return users.stream().filter(user -> user.getEmail().equals(id))
                .findFirst();
    }
}
