/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model.events;

import java.util.EventObject;

/**
 * This event indicates a notification to any view.
 * Such a notfication could be of the type update which should result into
 * an update of the view.
 *
 * @author gtrefs
 */
public class NotificationEvent extends EventObject {

    private Type type;

    public NotificationEvent(Object source, Type type) {
        super(source);
        this.type = type;
    }

    public enum Type {
        REFRESH, DICE_ROLLED, NO_MORE_ATTEMPS, PAWN_ENTERED, NO_MOVEABLE_PAWNS, MOVEABLE_PAWN_MOVED,
        GAME_WON, NEXT_ROUND_WITH_SAME_PLAYER, NEXT_ROUND_WITH_NEXT_PLAYER, NEW_GAME
    }

    public Type getType() {
        return type;
    }
}
