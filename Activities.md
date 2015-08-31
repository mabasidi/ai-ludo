## 20.10.2010: Meeting to discuss the best practice: ##
  1. Discussion about the existing code: structure, functionality
    1. 1 Clearance of questions: e.g. which methods are required
> 2. Next Steps: Implementation of agents
> > 2.1 Which agents to use with Ludo?: Random, Search, Neural Networks, Logical Agent

> 3. Next Meeting: Friday, 29. Oct. 2010

## 26.10.2010: Skype meeting ##
Talk about required methods to implement the agents.

## 29.10.2010: Meeting to discuss the AI implementation ##
One implementation should have a utility function, in order to measure the expected utility of a move.
  1. Following actions should result into positive utility:
    * Beat other player
    * Get into position where other pawn is potentially beatable next round
    * Get out of a position where own pawn is potentially beatable by other player
    * Get pawn on an end field
  1. Following actions should result into negative utility:
    * Move own pawn into a position where it can potetially be beaten by other's player pawn
    * Move ends on a beginfield of another player
  1. Definitions:
    * potentially beatable: distance between two opponent pawns is less or equal to 6
  1. Additions:
    * Bonus. To reflect the ultimate goal of a player to get all player's pawns on the player's end fields, a utility bonus is granted to each pawn reflecting the distance between it's current field and the first end field (Bonus = 1 <=> Pawn on 1. Field, ..., Bonus = 40 <=> Pawn on 40. Field).
    * Optional: Utility of one player's pawn depends on the number of another player pawns. E.g. if the enemy pawn could potentially beat me in the next round and the enemy player does not have any other pawn present on the board, it might be better to move the potential beatable pawn.