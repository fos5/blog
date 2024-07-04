package dev.festus.blog.email;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {
    @Async
    void sendEmail(String to, String email);
}
