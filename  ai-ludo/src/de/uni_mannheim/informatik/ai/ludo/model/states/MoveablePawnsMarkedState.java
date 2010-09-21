/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.TransitionIntent;
import de.uni_mannheim.informatik.ai.ludo.model.Field;
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Path;
import de.uni_mannheim.informatik.ai.ludo.model.Pawn;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;

/**
 *
 * @author gtrefs
 */
public class MoveablePawnsMarkedState extends GameState {

    private Pawn[] markedPawns;
    private int diceCount;

    public MoveablePawnsMarkedState(Game game) {
        super(game);
    }

    public MoveablePawnsMarkedState(Game game, Pawn[] markedPawns, int diceCount) {
        super(game);
        this.markedPawns = markedPawns;
        this.diceCount = diceCount;
    }

    @Override
    public void processIntent(TransitionIntent intent) {
        intent.success();
        if (markedPawns.length == 0) {
            game.setState(new RoundFinishedState(game, diceCount));
            game.fireNotificationEvent(new NotificationEvent(this, NotificationEvent.Type.NO_MOVEABLE_PAWNS));
            IntentFactory.getInstance().createAndDispatchTransitionIntent();
            return;
        }
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.movePawn();
    }

    @Override
    public void processIntent(RollDiceIntent roleDiceIntent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processIntent(MoveIntent moveIntent) {
        Pawn pawnToMove = moveIntent.getPawnToMove();
        Player currentPlayer = pawnToMove.getOwner();
        for (Pawn p : markedPawns) {
            if (pawnToMove.equals(p)) {
                Path playerPath = game.getCurrentPlayer().getPath();
                Field currentField = playerPath.getFieldOfPawn(p);
                int newIndex = playerPath.getPathIndexOfField(currentField) + diceCount;
                Field newField = playerPath.getFieldByIndex(newIndex);
                // Hit Test !
                if (!newField.isEmpty()) {
                    Pawn otherPlayersPawn = newField.takePawnFromField();
                    otherPlayersPawn.getOwner().getPath().placeOnStartField(otherPlayersPawn);
                    // Tell the player that he actually kicked a butt.
                    currentPlayer.enemyPawnThrown(otherPlayersPawn, moveIntent);
                }
                newField.placePawnOnField(p);
                // Intent has been successfull
                moveIntent.success();
                // Notifiy about the move
                game.setState(new RoundFinishedState(game, diceCount));
                game.fireNotificationEvent(new NotificationEvent(p, NotificationEvent.Type.MOVEABLE_PAWN_MOVED));
                IntentFactory.getInstance().createAndDispatchTransitionIntent();
                return;
            }
        }
        // No moveable pawn selected.
        // Do it once again.
        game.getCurrentPlayer().movePawn();
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
