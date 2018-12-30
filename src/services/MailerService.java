package services;

public interface MailerService {
    void send(MailAddress address, DigitalSignature signature);
}
