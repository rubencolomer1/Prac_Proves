package kiosk;

import Exceptions.NullPartyException;
import data.*;
import  services.*;

import java.util.Set;


public abstract class VotingKiosk
{
    private VoteCounter v1;
    private String nif;
    private boolean potVotar;
    private boolean volRebut;
    private Party opcioVot;


    private MailerService mService;
    private ElectoralOrganism eO;

    private Set<Party> validParties;

    VoteCounter votecounter = new VoteCounter(validParties);

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
        votecounter.scrutinize(party);
    }

    public void sendeReceipt(MailAdress address)
    {

    }

}
