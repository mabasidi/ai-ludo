package de.uni_mannheim.informatik.ai.ludo.model;

import de.uni_mannheim.informatik.ai.ludo.intent.Intent;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEventListener;
import de.uni_mannheim.informatik.ai.ludo.model.events.RequestForUserInputEvent;
import de.uni_mannheim.informatik.ai.ludo.model.events.RequestForUserInputEventListener;
import de.uni_mannheim.informatik.ai.ludo.model.states.GameState;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gtrefs
 */
public class Game {

    public enum Color {

        BLUE, GREEN, YELLOW, RED
    }

    /*
     * Events
     */
    // UserInputEvents
    private List<RequestForUserInputEventListener> requestListeners;

    public void addRequestForUserInputEventListener(RequestForUserInputEventListener l) {
        requestListeners.add(l);
    }

    public void removeRequestForUserInputEventListener(RequestForUserInputEventListener l) {
        requestListeners.remove(l);
    }

    public void fireRequestForUserInputEvent(RequestForUserInputEvent e) {
        List<RequestForUserInputEventListener> listenerList = new ArrayList<RequestForUserInputEventListener>(requestListeners);
        for (RequestForUserInputEventListener l : listenerList) {
            l.demandForInputOccured(e);
        }
    }
    // Notification events
    private List<NotificationEventListener> notificationListeners;

    public void addNotificationEventListener(NotificationEventListener l) {
        notificationListeners.add(l);
    }

    public void removeNotificationEventListener(NotificationEventListener l) {
        notificationListeners.remove(l);
    }

    public void fireNotificationEvent(NotificationEvent e) {
        List<NotificationEventListener> listenerList = new ArrayList<NotificationEventListener>(notificationListeners);
        for (NotificationEventListener l : listenerList) {
            l.notify(e);
        }
    }

    /*
     * Singleton
     */
    private static Game INSTANCE = new Game();

    public static Game getInstance() {
        return INSTANCE;
    }

    private Game() {
    }
    /*
     * Game vars
     */
    private GameState state;
    private Player[] players;
    private Board board;
    private Dice dice;
    private Player currentPlayer;
    private int playerIndex;

    public void handleIntent(Intent intent) {
        intent.takeVisitor(state);
    }

    public void reset() {
        fireNotificationEvent(new NotificationEvent(this, NotificationEvent.Type.NEW_GAME));
    }

    public void nextPlayer() {
        currentPlayer = players[++playerIndex % players.length];
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Player getPlayerByColor(Color color) {
        return null;
    }

    public Path getPathByPlayer() {
        return null;
    }

    public Dice getDice() {
        return dice;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
