package org.example.Domain;

public class Participant extends Entity<Long>{
    private final int varsta;
    private Long[] concursuri;

    public Participant(Long id, int varsta, Long[] concursuri) {
        super(id);
        this.varsta = varsta;
        this.concursuri = concursuri;
    }

    public int getVarsta() {
        return varsta;
    }

    public Long[] getConcursuri() {
        return concursuri;
    }

    public void setConcursuri(Long[] concursuri) {
        this.concursuri = concursuri;
    }
}
