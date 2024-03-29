/*
* Copyright (C) 2010 Gregor Trefs, Dominique Ritze
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package de.uni_mannheim.informatik.ai.ludo.intent;

/**
 * The intent visitor has to be implemented which wants to visit the specific {@link de.uni_mannheim.informatik.ai.ludo.intent.Intent Intent} implementations.
 * @author gtrefs
 */
public interface IntentVisitor {

    public void processIntent(TransitionIntent intent);

    public void processIntent(RollDiceIntent intent);

    public void processIntent(MoveIntent intent);

    public void processIntent(EndGameIntent intent);

    public void processIntent(NewGameIntent intent);
}