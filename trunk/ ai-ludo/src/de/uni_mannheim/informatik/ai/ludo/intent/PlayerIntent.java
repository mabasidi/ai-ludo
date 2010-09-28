/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.intent;

import de.uni_mannheim.informatik.ai.ludo.model.Player;

/**
 *
 * @author gtrefs
 */
public interface PlayerIntent extends Intent{
    public Player getPlayer();
    public void setPlayer(Player player);
}
