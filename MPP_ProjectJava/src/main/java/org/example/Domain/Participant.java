package org.example.Domain;

public class Participant extends Entity<Long>{
    private int varsta;
    private Long[] concursuri;

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
