package kiosk;
import data.Party;
import data.MailAdress;
import  services.ElectoralOrganism;
import services.MailerService;

//???

public abstract class VotingKiosk implements ElectoralOrganism, MailerService {
    public VotingKiosk(){}

    public void setElectoralOrganism(ElectoralOrganism eO){}

    public void setMailerService(MailerService mService){}

    public void vote(Party party){}

    public void sendeReceipt(MailAdress address){}

}
