package dev.festus.blog.appUser.registration;


public record RegistrationRequest(String firstName, String lastName, String email, String password) {
}
