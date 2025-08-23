package com.desafioviaappia.api.Web.DTO;

public record TokenResponse(String token, String tokenType) {
    public static TokenResponse bearer(String token) {
        return new TokenResponse(token, "Bearer");
    }
}
