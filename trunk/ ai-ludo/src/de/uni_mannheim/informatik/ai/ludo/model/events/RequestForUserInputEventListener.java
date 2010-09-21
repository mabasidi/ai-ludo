/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.events;

import java.util.EventListener;

/**
 * This interface is to be implemented by any class which is interested in a
 * notification whether a request for user input occured.
 * 
 * @author gtrefs
 */
public interface RequestForUserInputEventListener extends EventListener{
    public void demandForInputOccured(RequestForUserInputEvent r);
}
