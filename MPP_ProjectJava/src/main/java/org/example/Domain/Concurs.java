package org.example.Domain;

public class Concurs extends Entity<Long>{
    private String proba;
    private int varstaMin;
    private int varstaMax;
    private int numarParticipanti;
    private Long[] participanti;

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
        return numarParticipanti;
    }

    public Long[] getParticipanti() {
        return participanti;
    }

    public void setParticipanti(Long[] participanti) {
        this.participanti = participanti;
    }
}
