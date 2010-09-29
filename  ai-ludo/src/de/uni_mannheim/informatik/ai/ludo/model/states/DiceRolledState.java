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
import de.uni_mannheim.informatik.ai.ludo.model.Field;
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Path;
import de.uni_mannheim.informatik.ai.ludo.model.Pawn;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.StartField;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;
import java.util.ArrayList;

/**
 *
 * @author gtrefs
 */
public class DiceRolledState extends GameState {

    private int attemps = 0;

    public DiceRolledState(Game game) {
        super(game);
    }

    @Override
    public void processIntent(TransitionIntent intent) {
        throw new UnsupportedOperationException();
    }

    private Pawn[] determineMoveablePawns(Player p, Path playerPath, int diceCount) {
        ArrayList<Pawn> ret = new ArrayList<Pawn>();
        Pawn[] playerPawns = p.getPawns();

        for (Pawn pawn : playerPawns) {
            Field pawnField = playerPath.getFieldOfPawn(pawn);
            int fieldIndex = playerPath.getPathIndexOfField(pawnField);
            int nextFieldIndex = fieldIndex + diceCount;
            Field nextField = playerPath.getFieldByIndex(nextFieldIndex);
            if (nextField != null && playerPath.fieldNotPossedByAnotherOwnerPawn(nextField)) {
                // The pawn has to move from the begin field if he can, so he gets the only moveable pawn
                if (pawnField.equals(playerPath.getBeginField())) {
                    return new Pawn[]{pawn};
                }
                ret.add(pawn);
            }
        }
        return ret.toArray(new Pawn[0]);
    }

    @Override
    public void processIntent(RollDiceIntent roleDiceIntent) {
        // Intent arrived
        // Within this state the intnent is valid.
        // Increase attempt count, if the dice number was not 6.
        int diceCount = roleDiceIntent.getDiceCount();
        if (diceCount != 6) {
            attemps++;
        }
        // Intent has been successfull
        roleDiceIntent.success();
        Player currentPlayer = game.getCurrentPlayer();
        Path playerPath = currentPlayer.getPath();
        if (diceCount != 6 && playerPath.noPawnOnIntermediatePathFields()) {
            if (attemps == 3) {
                game.setState(new RoundFinishedState(game,diceCount));
                game.fireNotificationEvent(new NotificationEvent(this, NotificationEvent.Type.NO_MORE_ATTEMPS));
                IntentFactory.getInstance().createAndDispatchTransitionIntent(game);
                return;
            }
            currentPlayer.rollTheDice();
            return;
        }
        if (diceCount == 6 && playerPath.beginFieldNotPossessedByAnotherOwnerPawn() && playerPath.isAtLeastOneStartFieldFull()) {
            // Take pawn and place it on the beginField
            StartField s = playerPath.getFullStartField();
            Pawn ownerPawn = s.takePawnFromField();
            Field f = playerPath.getBeginField();
            // Hit check !
            if (!f.isEmpty()) {
                Pawn pawnOnField = f.takePawnFromField();
                pawnOnField.getOwner().getPath().placeOnStartField(pawnOnField);
                // Tell the player that he actually kicked a butt.
                currentPlayer.enemyPawnThrownByIntent(pawnOnField, roleDiceIntent);
            }
            // Place my pawn on field
            f.placePawnOnField(ownerPawn);
            game.fireNotificationEvent(new NotificationEvent(ownerPawn, NotificationEvent.Type.PAWN_ENTERED));
            // Role the dice once again
            currentPlayer.rollTheDice();
            return;
        }
        game.setState(new MoveablePawnsMarkedState(game, determineMoveablePawns(currentPlayer, playerPath, diceCount),diceCount));
        game.fireNotificationEvent(new NotificationEvent(null, NotificationEvent.Type.REFRESH));
        IntentFactory.getInstance().createAndDispatchTransitionIntent(game);
    }

    @Override
    public void processIntent(MoveIntent moveIntent) {
    }

    @Override
    public void processIntent(EndGameIntent intent) {
    }

    @Override
    public void processIntent(NewGameIntent intent) {
    }
}
