package com.tobiadeyinka.itunessearch.exceptions;

/**
 * Thrown when a required search parameter is missing
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class MissingRequiredParameterException extends Exception {

    public MissingRequiredParameterException(String message) {
        super(message);
    }

}
