package org.example.mpp_ui.Service;

import org.example.mpp_ui.Domain.Concurs;
import org.example.mpp_ui.Repository.ConcursRepo;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ConcursService {
    private final ConcursRepo repo;
    public ConcursService(Properties props){
        repo = new ConcursRepo(props);
    }

    public List<Concurs> getAll(){
        return repo.getAll();
    }

    public Optional<Concurs> find(long id){
        return repo.find(id);
    }

    public void Enroll(long participantId, long concursId){
        repo.Enroll(participantId, concursId);
    }
    public List<Concurs> findAllForAge(int age){
        return repo.FindAllForAge(age);
    }
}
