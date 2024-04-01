package org.example.mpp_ui.Service;

import org.example.mpp_ui.Domain.Participant;
import org.example.mpp_ui.Repository.ParticipantRepo;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ParticipantService {
    private final ParticipantRepo repo;
    public ParticipantService(Properties props){
        repo = new ParticipantRepo(props);
    }

    public List<Participant> getAll(){
        return repo.getAll();
    }

    public Optional<Participant> find(long id){
        return repo.find(id);
    }

    public Optional<Participant> add(Participant toAdd){
        return repo.add(toAdd);
    }

    public List<Participant> findAllFromList(List<Long> ids){
        return repo.FindAllFromList(ids);
    }
}
