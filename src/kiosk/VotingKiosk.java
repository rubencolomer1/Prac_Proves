package kiosk;

import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.*;
import  services.*;

public abstract class VotingKiosk
{
    private VoteCounter v1;
    private Nif nif;
    private boolean votEscrutat;
    private Party opcioVot;


    private MailerService mService;
    private ElectoralOrganism eO;



    public VotingKiosk()
    {
        this.votEscrutat = false;
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
        if (!eO.canVote(nif)  || votEscrutat == true)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.opcioVot = party;
            v1.scrutinize(opcioVot);
            eO.disableVoter(nif);
            votEscrutat = true;
        }
    }

    public void sendeReceipt(MailAdress address) throws NullException {


        if (!eO.canVote(nif) && votEscrutat == true)
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
