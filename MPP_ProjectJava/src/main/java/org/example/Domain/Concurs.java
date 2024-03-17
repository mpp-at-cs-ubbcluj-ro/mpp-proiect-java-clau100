package org.example.Domain;

public class Concurs extends Entity<Long>{
    private final String proba;
    private final int varstaMin;
    private final int varstaMax;

    private Long[] participanti;

    public Concurs(Long id, String proba, int varstaMin, int varstaMax, Long[] participanti) {
        super(id);
        this.proba = proba;
        this.varstaMin = varstaMin;
        this.varstaMax = varstaMax;
        this.participanti = participanti;
    }

    public String getProba() {
        return proba;
    }

    public int getVarstaMin() {
        return varstaMin;
    }

    public int getVarstaMax() {
        return varstaMax;
    }

    public int getNumarParticipanti() {
        return participanti.length;
    }

    public Long[] getParticipanti() {
        return participanti;
    }

    public void setParticipanti(Long[] participanti) {
        this.participanti = participanti;
    }
}
