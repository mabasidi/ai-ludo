/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.events;

import java.util.EventObject;

/**
 * This event indicates that user input is required.
 * There are only two types of user input, namely roling the dice and
 * moving the pawn. In order to avoid a classes overhead both types
 * are merged into this class.
 * Default type is a request for a user move.
 *
 * @author gtrefs
 */
public class RequestForUserInputEvent extends EventObject{

    public enum Type {
        REQUEST_FOR_MOVE,REQUEST_FOR_ROLLING_THE_DICE,INIT_DATA;
    }

    private Type actType=Type.REQUEST_FOR_MOVE;
    private String message;

    public RequestForUserInputEvent(Object source, Type type){
        super(source);
        actType=type;
    }

    public RequestForUserInputEvent(Object source){
        super(source);
    }

    public Type getType(){
        return actType;
    }

    public void setType(Type type){
        actType=type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
