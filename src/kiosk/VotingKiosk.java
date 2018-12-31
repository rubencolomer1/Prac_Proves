package kiosk;

import data.*;
import  services.*;


public abstract class VotingKiosk
{
    private VoteCounter v1;
    private String nif;
    private boolean potVotar;
    private boolean volRebut;
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

    public void vote(Party party)
    {

    }

    public void sendeReceipt(MailAdress address)
    {

    }

}
