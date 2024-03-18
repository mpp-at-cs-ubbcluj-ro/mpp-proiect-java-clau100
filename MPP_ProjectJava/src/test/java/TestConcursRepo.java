import org.example.Domain.Concurs;
import org.example.Repository.ConcursRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Properties;

public class TestConcursRepo {
    private ConcursRepo init(){
        Properties props = new Properties();
        props.setProperty("jdbc.url", "jdbc:sqlite:C:/Users/user/Downloads/mpp-proiect-java-clau100/MPP_ProjectJava/Testing.sqlite");
        return new ConcursRepo(props);
    }

    @Test
    @DisplayName("GetAll Test")
    public void TestGetAll(){
        ConcursRepo concursuri = init();
        List<Concurs> all = concursuri.getAll();
        assert(all.size() == 2);
    }

    @Test
    @DisplayName("Find Test")
    public void TestFind(){
        ConcursRepo concursuri = init();
        assert(concursuri.find(1L).isPresent());
    }

    @Test
    @DisplayName("Find All for age Test")
    public void TestFindForAge(){
        ConcursRepo concursuri = init();
        assert(concursuri.FindAllForAge(12).size() == 2);
    }

}
