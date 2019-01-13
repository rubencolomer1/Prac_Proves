package kiosk;

import Exceptions.*;
import data.*;
import  services.*;

public  class VotingKiosk
{
    public VoteCounter v1;
    private Nif nif;

    private Party opcioVot;


    private MailerService mService;
    private ElectoralOrganism eO;

    private boolean eOServiceSet;
    private boolean mServiceSet;
    private boolean nifSet;

    public VotingKiosk()
    {
        this.eOServiceSet = false;
        this.mServiceSet = false;
        this.nifSet = false;
        this.v1 = new VoteCounter(null);
    }

    public void setElectoralOrganism(ElectoralOrganism eO) throws AlreadySetServiceException {
        if (!eOServiceSet)
        {
            this.eO = eO;
            eOServiceSet = true;
        }
        else
        {
            throw new AlreadySetServiceException("Serveis ja establits!");
        }

    }

    public void setMailerService(MailerService mService) throws AlreadySetServiceException {
        if (!mServiceSet)
        {
            this.mService = mService;
            mServiceSet = true;
        }
        else
        {
            throw new AlreadySetServiceException("Serveis ja establits!");
        }

    }
    public void SetNif(Nif nif) throws AlreadySetNifException {
        if (!nifSet)
        {
            this.nif = nif;
            nifSet = true;
        }
        else
        {
            throw new AlreadySetNifException("Nif ja establit!");
        }

    }

    public void vote(Party party) throws NullPartyException, ServicesNotSetException, NifNotSetException, NifCannotVoteException {
        if (!eOServiceSet || !mServiceSet)
        {
            throw new ServicesNotSetException("Serveis no establits!");
        }

        else if (!nifSet)
        {
            throw new NifNotSetException("Nif no establit!");
        }
        else if (!eO.canVote(nif))
        {
            throw new NifCannotVoteException("Aquest Nif no pot votar!");
        }
        else
        {
            this.opcioVot = party;
            v1.scrutinize(opcioVot);
            eO.disableVoter(nif);
            nifSet = false;
        }
    }

    public void sendeReceipt(MailAdress address) throws NullException, ServicesNotSetException, HasNotVotedYetException {


        if (!eOServiceSet || !mServiceSet)
        {
            throw new ServicesNotSetException("Serveis no establits!");
        }
        else if(eO.canVote(nif))
        {
            throw new HasNotVotedYetException("Encara no ha votat");
        }
        else
        {
            DigitalSignature digitalSignature = eO.askForDigitalSignature(opcioVot);
            mService.send(address, digitalSignature);
        }
    }
}
