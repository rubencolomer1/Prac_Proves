package kiosk;

import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.*;
import  services.*;

import java.util.HashSet;
import java.util.Set;

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

    public void setElectoralOrganism(ElectoralOrganism eO)
    {
        if (!eOServiceSet)
        {
            this.eO = eO;
            eOServiceSet = true;
        }
        else
        {
            throw new IllegalStateException();
        }

    }

    public void setMailerService(MailerService mService)
    {
        if (!mServiceSet)
        {
            this.mService = mService;
            mServiceSet = true;
        }
        else
        {
            throw new IllegalStateException();
        }

    }
    public void SetNif(Nif nif)
    {
        if (!nifSet)
        {
            this.nif = nif;
            nifSet = true;
        }
        else
        {
            throw new IllegalStateException();
        }

    }

    public void vote(Party party) throws NullPartyException
    {
        if (!eOServiceSet || !mServiceSet || !eO.canVote(nif) || !nifSet)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.opcioVot = party;
            v1.scrutinize(opcioVot);
            eO.disableVoter(nif);
            nifSet = false;
        }
    }

    public void sendeReceipt(MailAdress address) throws NullException {


        if (!eOServiceSet || !mServiceSet || eO.canVote(nif))
        {
            throw new IllegalStateException();
        }
        else
        {
            DigitalSignature digitalSignature = eO.askForDigitalSignature(opcioVot);
            mService.send(address, digitalSignature);
        }
    }
}
