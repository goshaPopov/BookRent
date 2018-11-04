package ru.popov.book_rent.exceptions;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
        super("Not enough money");
    }
}
