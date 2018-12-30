package services;

import data.DigitalSignature;
import data.MailAdress;

public interface MailerService {
    void send(MailAdress address, DigitalSignature signature);
}
