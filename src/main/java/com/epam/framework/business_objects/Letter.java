package com.epam.framework.business_objects;

public class Letter {
    private String subject ;
    private String recipient ;
    private String message ;


    public Letter(String subject, String recipient, String message) {
        this.subject = subject;
        this.recipient = recipient;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}
