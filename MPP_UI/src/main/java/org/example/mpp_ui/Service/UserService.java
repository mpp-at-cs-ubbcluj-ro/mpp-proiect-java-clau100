package org.example.mpp_ui.Service;

import org.example.mpp_ui.Domain.User;
import org.example.mpp_ui.Repository.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserService {
    private final UserRepo repo;
    public UserService(Properties props){
        repo = new UserRepo(props);
    }
    public List<User> getAll(){
        return repo.getAll();
    }

    public Optional<User> find(long id){
        return repo.find(id);
    }

    public Boolean CheckUser(String Username, String Password){
        return repo.CheckUser(Username, Password);
    }
}
