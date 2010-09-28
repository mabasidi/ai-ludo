/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory;
import de.uni_mannheim.informatik.ai.ludo.intent.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.TransitionIntent;
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Path;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;

/**
 *
 * @author gtrefs
 */
public class RoundFinishedState extends GameState {

    private int diceCount;

    public RoundFinishedState(Game game, int diceCount) {
        super(game);
        this.diceCount = diceCount;
    }

    @Override
    public void processIntent(TransitionIntent intent) {
        /*
         * 1. All pawns of currentPlayer at end fields --> game is won by current player
         * 2. If diceCount == 6 --> repeat round with same player
         * 3. If diceCount < 6 --> next round with next player
         */
        Player currentPlayer = game.getCurrentPlayer();
        Path playerPath = currentPlayer.getPath();
        if (playerPath.allEndFieldsFull()) {
            game.setState(new GameWonState(game));
            game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.GAME_WON));
            IntentFactory.getInstance().createAndDispatchTransitionIntent(game);
            return;
        }
        game.setState(new RoundStartedState(game));
        if (diceCount == 6) {
            // Same player
            game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.NEXT_ROUND_WITH_SAME_PLAYER));
        } else {
            // Next player
            game.nextPlayer();
            game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.NEXT_ROUND_WITH_NEXT_PLAYER));
        }
        IntentFactory.getInstance().createAndDispatchTransitionIntent(game);
        return;
    }

    @Override
    public void processIntent(RollDiceIntent roleDiceIntent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processIntent(MoveIntent moveIntent) {
        throw new UnsupportedOperationException("Not supported yet.");
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
