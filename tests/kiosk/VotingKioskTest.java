package kiosk;

import Exceptions.EmailDoesNotExistsException;
import Exceptions.NifDoesNotExistsException;
import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.DigitalSignature;
import data.MailAdress;
import data.Nif;
import data.Party;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import services.ElectoralOrganism;
import services.MailerService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VotingKioskTest
{
    public VotingKiosk vk;
    public ElectoralOrganismDoble eOD;
    public MailerServiceDoble mSD;

    private static class ElectoralOrganismDoble implements ElectoralOrganism
    {
        private List<Nif> nifsCanVote = new ArrayList();

        @Override
        public boolean canVote(Nif nif)
        {
            if (nifsCanVote.contains(nif))
            {
                return true;
            }

            return false;
        }

        @Override
        public void disableVoter(Nif nif)
        {
            nifsCanVote.remove(nif);
        }

        @Override
        public DigitalSignature askForDigitalSignature(Party party) throws NullException {
            DigitalSignature sign = new DigitalSignature(party.toString().getBytes());
            return sign;
        }
    }

    private static class MailerServiceDoble implements MailerService
    {
        public boolean emailSended = false;

        @Override
        public void send(MailAdress address, DigitalSignature signature)
        {
            emailSended = true;
        }
    }

    @BeforeEach
    void init()
    {
        vk = new VotingKiosk();
        eOD = new ElectoralOrganismDoble();
        mSD = new MailerServiceDoble();
    }

    @Test
    void votingKioskServicesAlreadySetted()
    {
        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        assertThrows(IllegalStateException.class, () -> vk.setMailerService(mSD));
        assertThrows(IllegalStateException.class, () -> vk.setElectoralOrganism(eOD));
    }

    @Test
    void votingKioskServicesNotSetted() throws NullException, NifDoesNotExistsException, EmailDoesNotExistsException
    {

        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        MailAdress mail = new MailAdress("rubencolomer.1@gmail.com");

        //Encara no s'han establit els serveis

        assertThrows(IllegalStateException.class, () -> vk.vote(party));
        assertThrows(IllegalStateException.class, () -> vk.sendeReceipt(mail));
    }

    @Test
    void testVote() throws NullException, NullPartyException, NifDoesNotExistsException
    {

        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        Set<Party> validParties = new HashSet<>();
        validParties.add(party);
        vk.v1 = new VoteCounter(validParties);

        //Serveis no establits.

        assertThrows(IllegalStateException.class, () -> vk.vote(party));

        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        vk.SetNif(nif);
        eOD.nifsCanVote.add(nif);

        vk.vote(party);
        assertFalse(eOD.canVote(nif));
    }

    @Test
    void testSendeReceipt() throws NullException, EmailDoesNotExistsException, NullPartyException, NifDoesNotExistsException
    {

        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        MailAdress mail = new MailAdress("rubencolomer.1@gmail.com");
        Set<Party> validParties = new HashSet<>();
        validParties.add(party);
        vk.v1 = new VoteCounter(validParties);

        //Serveis no establits.
        assertThrows(IllegalStateException.class, () -> vk.vote(party));

        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        vk.SetNif(nif);
        eOD.nifsCanVote.add(nif);

        //Encara no s'ha votat

        assertThrows(IllegalStateException.class, () -> vk.sendeReceipt(mail));

        vk.vote(party);
        vk.sendeReceipt(mail);

        assertTrue(mSD.emailSended);

    }
}