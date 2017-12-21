package objectRepository;

public class Mail {
    private String email;
    private String subject;
    private String textContent;

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getTextContent() {
        return textContent;
    }

    public Mail(String email, String subject, String textContent) {

        this.email = email;
        this.subject = subject;
        this.textContent = textContent;
    }
}
