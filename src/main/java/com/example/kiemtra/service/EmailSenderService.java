package com.example.kiemtra.service;

public interface EmailSenderService {

    void sendMail(String to, String body, String subject);
}
