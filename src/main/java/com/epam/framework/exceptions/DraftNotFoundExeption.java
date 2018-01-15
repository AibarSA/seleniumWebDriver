package com.epam.framework.exceptions;

public class DraftNotFoundExeption extends Exception {
    @Override
    public String toString() {
        return "There is no any Draft with similar recipient and subject";
    }
}
