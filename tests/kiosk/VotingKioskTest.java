package kiosk;

import Exceptions.*;
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
    void testVotingKioskServicesAlreadySetted() throws AlreadySetServiceException {
        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        assertThrows(AlreadySetServiceException.class, () -> vk.setMailerService(mSD));
        assertThrows(AlreadySetServiceException.class, () -> vk.setElectoralOrganism(eOD));
    }

    @Test
    void testVotingKioskServicesNotSet() throws NullException, NifDoesNotExistsException, EmailDoesNotExistsException
    {

        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        MailAdress mail = new MailAdress("rubencolomer.1@gmail.com");

        //Encara no s'han establit els serveis

        assertThrows(ServicesNotSetException.class, () -> vk.vote(party));
        assertThrows(ServicesNotSetException.class, () -> vk.sendeReceipt(mail));
    }

    @Test
    void testNifNotSet() throws NullException, EmailDoesNotExistsException, NifDoesNotExistsException, AlreadySetServiceException
    {
        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        MailAdress mail = new MailAdress("rubencolomer.1@gmail.com");

        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);

        assertThrows(NifNotSetException.class, () -> vk.vote(party));
    }

    @Test
    void testNifAlreadySet() throws NullException, EmailDoesNotExistsException, NifDoesNotExistsException, AlreadySetServiceException, AlreadySetNifException
    {
        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        MailAdress mail = new MailAdress("rubencolomer.1@gmail.com");

        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        vk.SetNif(nif);
        assertThrows(AlreadySetNifException.class, () -> vk.SetNif(nif));

    }
    @Test
    void testVote() throws NullException, NullPartyException, NifDoesNotExistsException, ServicesNotSetException, NifCannotVoteException, NifNotSetException, AlreadySetServiceException, AlreadySetNifException
    {

        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        Set<Party> validParties = new HashSet<>();
        validParties.add(party);
        vk.v1 = new VoteCounter(validParties);
        vk.SetNif(nif);
        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);

        //Aquell Nif no pot votar
        assertThrows(NifCannotVoteException.class, () -> vk.vote(party));

        eOD.nifsCanVote.add(nif);

        vk.vote(party);
        assertFalse(eOD.canVote(nif));
    }

    @Test
    void testSendeReceipt() throws NullException, EmailDoesNotExistsException, NullPartyException, NifDoesNotExistsException, ServicesNotSetException, NifCannotVoteException, NifNotSetException, HasNotVotesYetException, AlreadySetServiceException, AlreadySetNifException
    {

        Party party = new Party("party");
        Nif nif = new Nif("48057957D");
        MailAdress mail = new MailAdress("rubencolomer.1@gmail.com");
        Set<Party> validParties = new HashSet<>();
        validParties.add(party);
        vk.v1 = new VoteCounter(validParties);
        vk.setElectoralOrganism(eOD);
        vk.setMailerService(mSD);
        vk.SetNif(nif);
        eOD.nifsCanVote.add(nif);

        //Encara no s'ha votat

        assertThrows(HasNotVotesYetException.class, () -> vk.sendeReceipt(mail));

        vk.vote(party);
        vk.sendeReceipt(mail);
        assertTrue(mSD.emailSended);

    }
}