package kiosk;

import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.*;
import  services.*;

public abstract class VotingKiosk
{
    private VoteCounter v1;
    private String nif;
    private boolean haVotat;
    private boolean votEscrutat;
    private Party opcioVot;


    private MailerService mService;
    private ElectoralOrganism eO;



    public VotingKiosk()
    {
        this.haVotat = false;
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
        if (haVotat == true || votEscrutat == true)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.opcioVot = party;
            v1.scrutinize(opcioVot);
            votEscrutat = true;
            haVotat = true;
        }
    }

    public void sendeReceipt(MailAdress address) throws NullException {
        if (!haVotat)
        {
            throw new IllegalStateException();
        }
        else if (haVotat == false && votEscrutat == false)
        {
            throw new IllegalStateException();
        }

        else
        {
            DigitalSignature digitalSignature = eO.askForDigitalSignature(opcioVot);
            mService.send(address, digitalSignature);
            haVotat = false;
        }
    }

}
