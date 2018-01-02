package com.epam.framework.business_objects;

public class Letter {
    private String subject ;
    private String recipient ;
    private String textContent;


    public Letter(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.textContent = message;
    }


    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getTextContent() {
        return textContent;
    }
}
