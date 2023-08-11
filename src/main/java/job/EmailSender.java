package job;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String to, String subject, String body, String attachmentPath) {
        // Setup mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Port as a String
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Authenticate and create session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("your_email@example.com", "your_password");
            }
        });

        // Enable debug mode
        session.setDebug(true);

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("admgbvrj@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Create multipart message
            MimeMultipart multipart = new MimeMultipart();

            // Add text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);
            multipart.addBodyPart(textPart);

            // Add attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(attachmentPath); // Path to the HTML report
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

