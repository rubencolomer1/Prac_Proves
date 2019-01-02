package kiosk;

import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.*;
import  services.*;

public abstract class VotingKiosk
{
    private VoteCounter v1;
    private Nif nif;

    private Party opcioVot;


    private MailerService mService;
    private ElectoralOrganism eO;



    public VotingKiosk()
    {

    }

    public void setElectoralOrganism(ElectoralOrganism eO)
    {
        this.eO = eO;
    }

    public void setMailerService(MailerService mService)
    {
        this.mService = mService;
    }

    public void vote(Party party) throws NullPartyException
    {
        if (!eO.canVote(nif))
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


        if (!eO.canVote(nif))
        {
            DigitalSignature digitalSignature = eO.askForDigitalSignature(opcioVot);
            mService.send(address, digitalSignature);
        }
        else
        {
            throw new IllegalStateException();
        }
    }
}
