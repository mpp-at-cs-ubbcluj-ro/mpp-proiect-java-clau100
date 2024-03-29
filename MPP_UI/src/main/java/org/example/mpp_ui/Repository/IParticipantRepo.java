package org.example.mpp_ui.Repository;

import org.example.mpp_ui.Domain.Participant;

import java.util.List;

public interface IParticipantRepo {
    List<Participant> FindAllFromList(List<Long> participantIDs);
}
