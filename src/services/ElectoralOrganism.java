package services;

public interface ElectoralOrganism {
    boolean canVote(Nif nif);
    void disableVoter(Nif nif);
    DigitalSignature askForDigitalSignature(Party party);
}
