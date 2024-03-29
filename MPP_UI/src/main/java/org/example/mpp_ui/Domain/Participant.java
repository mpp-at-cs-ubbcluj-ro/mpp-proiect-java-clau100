package org.example.mpp_ui.Domain;

import java.util.List;

public class Participant extends Entity<Long> {
    private final int varsta;
    private List<Long> concursuri;

    public Participant(Long id, int varsta, List<Long> concursuri) {
        super(id);
        this.varsta = varsta;
        this.concursuri = concursuri;
    }

    public int getVarsta() {
        return varsta;
    }

    public List<Long> getConcursuri() {
        return concursuri;
    }

    public void setConcursuri(List<Long> concursuri) {
        this.concursuri = concursuri;
    }
}
