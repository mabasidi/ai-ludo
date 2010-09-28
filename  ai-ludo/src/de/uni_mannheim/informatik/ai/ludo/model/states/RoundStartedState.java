/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.TransitionIntent;
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;

/**
 * The IdelState class is the first state the game enters.
 * The current player is handed over to the processIntent
 * method which invokes the rollTheDice() method on the player. If user input
 * is required, a corresponding exception is thrown which results into a event
 * to the view. If the player is an artificial player, the method returns a
 * normal dice intent which is being processed by this state.
 * This processing eventually results into a state transition.
 *
 * @author gtrefs
 */
public class RoundStartedState extends GameState {

    public RoundStartedState(Game game) {
        super(game);
    }

    public RoundStartedState() {
    }

    public void processIntent(TransitionIntent intent) {
        // intent has been successfull
        intent.success();
        Player currentPlayer = game.getCurrentPlayer();
        game.setState(new DiceRolledState(game));
        currentPlayer.rollTheDice();
        game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.DICE_ROLLED));
    }

    public void processIntent(RollDiceIntent roleDiceIntent) {
        throw new UnsupportedOperationException();
    }

    public void processIntent(MoveIntent moveIntent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processIntent(EndGameIntent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processIntent(NewGameIntent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
