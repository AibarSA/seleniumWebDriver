package com.epam.framework.business_objects;

public class Letter {
    private String subject = "From Aibar";
    private String recipient = "aibar.abilchanov@mail.ru";
    private String message = "Test automation engineers in da house!!! ";


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
