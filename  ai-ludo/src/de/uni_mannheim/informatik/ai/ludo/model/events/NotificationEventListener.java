/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.events;

import java.util.EventListener;

/**
 * This interface should be implemented by any view which is interrested in 
 * notifications from the model.
 *
 * @author gtrefs
 */
public interface NotificationEventListener extends EventListener{
    public void notify(NotificationEvent e);
}
