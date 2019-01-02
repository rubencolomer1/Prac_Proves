package kiosk;

import Exceptions.EmailDoesNotExistsException;
import Exceptions.NifDoesNotExistsException;
import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.DigitalSignature;
import data.MailAdress;
import data.Nif;
import data.Party;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import services.ElectoralOrganism;
import services.MailerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VotingKioskTest
{
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
            DigitalSignature sign = new DigitalSignature(new byte[]{Byte.parseByte(party.toString())});
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
    //Es torna a intentar activar els serveis del voting Kiosk
    @Test
    void votingKioskServicesAlreadySetted()
    {
        VotingKiosk vk = new VotingKiosk();
        ElectoralOrganismDoble eOD = new ElectoralOrganismDoble();
        MailerServiceDoble mSD = new MailerServiceDoble();
        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        assertThrows(IllegalStateException.class, () -> vk.setMailerService(mSD));
        assertThrows(IllegalStateException.class, () -> vk.setElectoralOrganism(eOD));
    }
    @Test
    void votingKioskServicesNotSetted() throws NullException, NifDoesNotExistsException, EmailDoesNotExistsException {
        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        MailAdress mail = new MailAdress("rubencolomer.1@gmail.com");
        VotingKiosk vk = new VotingKiosk();
        assertThrows(IllegalStateException.class, () -> vk.vote(party));
        assertThrows(IllegalStateException.class, () -> vk.sendeReceipt(mail));
    }
    @Test
    void testVote() throws NullException, NullPartyException, NifDoesNotExistsException {
        VotingKiosk vk = new VotingKiosk();
        ElectoralOrganismDoble eOD = new ElectoralOrganismDoble();
        MailerServiceDoble mSD = new MailerServiceDoble();
        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        vk.SetNif(nif);
        vk.vote(party);
        assertFalse(eOD.canVote(nif));
    }

    @Test
    void testSendeReceipt()
    {

    }
}