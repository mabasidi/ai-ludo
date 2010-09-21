/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model;

import de.uni_mannheim.informatik.ai.ludo.intent.Intent;

/**
 * This Player interface is the common interface all players (human and artficial)
 * have to implement.
 * @author gtrefs
 */
public interface Player {
    public void setColor(Game.Color color);
    public Game.Color getColor();

    public void setPawns(Pawn[] pawns);
    public Pawn[] getPawns();
    public Pawn getPawn(int index);

    public void setPath(Path path);
    public Path getPath();

    public void movePawn();
    public void rollTheDice();

    public void rejectIntent(Intent intent);
    public void successIntent(Intent intent);

    public void enemyPawnThrown(Pawn enemyPawn,Intent intent);
}
