package services;

import Exceptions.NullException;
import data.DigitalSignature;
import data.Nif;
import data.Party;

public interface ElectoralOrganism {
    boolean canVote(Nif nif);
    void disableVoter(Nif nif);
    DigitalSignature askForDigitalSignature(Party party) throws NullException;
}
