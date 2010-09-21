/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.intent;

import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Pawn;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.states.GameState;

/**
 * This class creates concrete Intents which are dispatchable by the Intent-
 * Dispatcher.
 * @author gtrefs
 */
public class IntentFactory {

    private static IntentFactory INSTANCE = new IntentFactory();

    public static IntentFactory getInstance() {
        return INSTANCE;
    }

    private IntentFactory() {
    }

    public Intent createMoveIntent(Game game, Pawn pawnToMove) {
        return new MoveIntent(game, pawnToMove);
    }

    public Intent createRollDiceIntent(Game game, Player player) {
        return new RollDiceIntent(game, player);
    }

    public Intent createSystemTransitionIntent() {
        return null;
    }

    public void createAndDispatchTransitionIntent() {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent transitionIntent = createSystemTransitionIntent();
        dispatcher.dispatchIntent(transitionIntent);
    }

    public void createAndDispatchMoveIntent(Game game, Pawn pawn) {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent moveIntent = createMoveIntent(game, pawn);
        dispatcher.dispatchIntent(moveIntent);
    }

    public void createAndDispatchRollDiceIntent(Game game, Player player) {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent rollIntent = createRollDiceIntent(game, player);
        dispatcher.dispatchIntent(rollIntent);
    }

    public Intent createNewGameIntent() {
        return new NewGameIntent();
    }

    public void createAndDispatchNewGameIntent() {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent newGameIntent = createNewGameIntent();
        dispatcher.dispatchIntent(newGameIntent);
    }

    public Intent createEndGameIntent() {
        return new EndGameIntent();
    }

    public void createAndDispatchEndGameIntent() {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent endGameIntent = createEndGameIntent();
        dispatcher.dispatchIntent(endGameIntent);
    }

    public class MoveIntent implements Intent {
        // Target

        private Game game;
        private Pawn pawnToMove;
        private Player player;

        private MoveIntent(Game game, Pawn pawnToMove) {
            this.game = game;
            this.pawnToMove = pawnToMove;
            this.player = pawnToMove.getOwner();
        }

        public void takeGameState(GameState gameState) {
            gameState.processIntent(this);
        }

        public Pawn getPawnToMove() {
            return pawnToMove;
        }

        public void setPawnToMove(Pawn pawnToMove) {
            this.pawnToMove = pawnToMove;
        }

        public void execute() {
            game.handleIntent(this);
        }

        public void reject() {
            player.rejectIntent(this);
        }

        public void success() {
            player.successIntent(this);
        }
    }

    public class RollDiceIntent implements Intent {

        private Game game;
        private Player player;
        // dice count
        private int diceCount;

        private RollDiceIntent(Game game, Player player) {
            this.game = game;
            this.player = player;
        }

        public void takeGameState(GameState gameState) {
            gameState.processIntent(this);
        }

        public void execute() {
            game.handleIntent(this);
        }

        public void reject() {
            player.rejectIntent(this);
        }

        public void success() {
            player.successIntent(this);
        }

        public int getDiceCount() {
            return diceCount;
        }

        public void setDiceCount(int diceCount) {
            this.diceCount = diceCount;
        }
    }

    public class TransitionIntent implements Intent {

        private Game game;
        private IntentDispatcher dispatcher;

        private TransitionIntent(Game game, IntentDispatcher dispatcher) {
            this.game = game;
            this.dispatcher = dispatcher;
        }

        public void takeGameState(GameState gameState) {
            gameState.processIntent(this);
        }

        public void execute() {
            game.handleIntent(this);
        }

        public void reject() {
            
        }

        public void success() {
            
        }
    }

    public class NewGameIntent implements Intent {

        private NewGameIntent() {
        }

        public void takeGameState(GameState gameState) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void execute() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void reject() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void success() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class EndGameIntent implements Intent {

        public EndGameIntent() {
        }

        public void takeGameState(GameState gameState) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void execute() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void reject() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void success() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
