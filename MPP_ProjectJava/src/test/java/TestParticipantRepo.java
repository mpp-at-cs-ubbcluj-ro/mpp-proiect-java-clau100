import org.example.Domain.Participant;
import org.example.Repository.ParticipantRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class TestParticipantRepo {
    private ParticipantRepo init(){
        Properties props = new Properties();
        props.setProperty("jdbc.url", "jdbc:sqlite:C:/Users/user/Downloads/mpp-proiect-java-clau100/MPP_ProjectJava/Testing.sqlite");
        return new ParticipantRepo(props);
    }

    @Test
    @DisplayName("GetAll Test")
    public void TestGetAll(){
        ParticipantRepo participanti = init();
        List<Participant> all = participanti.getAll();
        assert(all.size() == 2);
    }

    @Test
    @DisplayName("Find Test")
    public void TestFind(){
        ParticipantRepo participanti = init();
        assert(participanti.find(1L).isPresent());
    }
    @Test
    @DisplayName("Find From List Test")
    public void TestFindFromList(){
        ParticipantRepo participanti = init();
        LinkedList<Long> inputs = new LinkedList<>();
        inputs.add(1L);
        inputs.add(2L);
        List<Participant> found = participanti.FindAllFromList(inputs);
        assert(found.size() == 2);
    }
}
