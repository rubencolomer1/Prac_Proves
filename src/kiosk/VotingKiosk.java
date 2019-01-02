package kiosk;

import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.*;
import  services.*;

public  class VotingKiosk
{
    private VoteCounter v1;
    private Nif nif;

    private Party opcioVot;


    private MailerService mService;
    private ElectoralOrganism eO;

    private boolean eOServiceActivated;
    private boolean mServiceActivated;



    public VotingKiosk()
    {
        this.eOServiceActivated = false;
        this.mServiceActivated = false;
    }

    public void setElectoralOrganism(ElectoralOrganism eO)
    {
        if (!eOServiceActivated)
        {
            this.eO = eO;
            eOServiceActivated = true;
        }
        else
        {
            throw new IllegalStateException();
        }

    }

    public void setMailerService(MailerService mService)
    {
        if (!mServiceActivated)
        {
            this.mService = mService;
            mServiceActivated = true;
        }
        else
        {
            throw new IllegalStateException();
        }

    }
    public void SetNif(Nif nif)
    {
        this.nif = nif;
    }

    public void vote(Party party) throws NullPartyException
    {
        if (!eO.canVote(nif) || !eOServiceActivated || !mServiceActivated)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.opcioVot = party;
            v1.scrutinize(opcioVot);
            eO.disableVoter(nif);
        }
    }

    public void sendeReceipt(MailAdress address) throws NullException {


        if (eO.canVote(nif) || !eOServiceActivated || !mServiceActivated)
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
