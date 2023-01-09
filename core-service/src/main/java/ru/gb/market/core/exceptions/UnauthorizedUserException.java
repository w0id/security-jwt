package ru.gb.market.core.exceptions;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException(final String message) {
        super(message);
    }
}
