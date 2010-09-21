/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.view.renderer;

import de.uni_mannheim.informatik.ai.ludo.model.BeginField;
import de.uni_mannheim.informatik.ai.ludo.model.Board;
import de.uni_mannheim.informatik.ai.ludo.model.Dice;
import de.uni_mannheim.informatik.ai.ludo.model.EndField;
import de.uni_mannheim.informatik.ai.ludo.model.Field;
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Path;
import de.uni_mannheim.informatik.ai.ludo.model.Pawn;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.StartField;

/**
 * This interface is to implemented by every class which wants to display the
 * game in some matter. The rendereing technology is up to the programmer.
 * This class implements the visitor pattern.
 * @author gtrefs
 */
public interface Renderer {
    public void render(Game game);
    public void render(BeginField beginField);
    public void render(Board board);
    public void render(Dice dice);
    public void render(EndField endField);
    public void render(Field field);
    public void render(Path path);
    public void render(Pawn pawn);
    public void render(Player player);
    public void render(StartField startField);
}
