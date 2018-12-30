package kiosk;
import  services.ElectoralOrganism;
import services.MailerService;

//???

public class VotingKiosk implements ElectoralOrganism, MailerService {
    public VotingKiosk(){}

    public void setElectoralOrganism(ElectoralOrganism eO){}

    public void setMailerService(MailerService mService){}

    public void vote(Party party){}

    public void sendeReceipt(MailAddress address){}

}
