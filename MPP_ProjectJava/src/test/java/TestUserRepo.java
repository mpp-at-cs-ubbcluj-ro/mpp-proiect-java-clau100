import org.example.Domain.User;
import org.example.Repository.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Properties;

public class TestUserRepo {
    private UserRepo init(){
        Properties props = new Properties();
        props.setProperty("jdbc.url", "jdbc:sqlite:C:/Users/user/Downloads/mpp-proiect-java-clau100/MPP_ProjectJava/Testing.sqlite");
        return new UserRepo(props);
    }
    @Test
    @DisplayName("GetAll Test")
    public void TestGetAll(){
        UserRepo users = init();
        List<User> all = users.getAll();
        assert(all.size() == 2);
    }

    @Test
    @DisplayName("Find Test")
    public void TestFind(){
        UserRepo users = init();
        assert(users.find(1L).isPresent());
    }
    @Test
    @DisplayName("Login Test")
    public void TestLogin()
    {
        UserRepo users = init();
        assert(users.CheckUser("claudiu", "ubb123"));
    }
}
