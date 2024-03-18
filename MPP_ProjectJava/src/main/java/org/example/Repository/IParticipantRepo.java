package org.example.Repository;

import org.example.Domain.Participant;

import java.util.List;

public interface IParticipantRepo {
    List<Participant> FindAllFromList(List<Long> participantIDs);
}
