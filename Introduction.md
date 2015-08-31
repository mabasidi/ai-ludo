# Introduction to the ai ludo basic source code

# Page Iterations #

Before getting to the interesting stuff, please not that this document is a living document. This means, the sections which are left blank will be filled out in the future. Further, some sections might change, but we try to keep the structure of the overall document constant over the time.

# Introduction #

Welcome to the ai-ludo project. This project is developed under the GNU GPL 3. The developers are Dominique Ritze and Gregor Trefs, both students of the University of Mannheim. The game is being developed complementary to the lecture "Artficial Intelligence". This short wiki page should give you an overview of the basic program structure. The program itself can be found under [source](http://code.google.com/p/ai-ludo/source/checkout). Following topics are covered:

  * [MVC as the primary pattern](Introduction#MVC_as_the_primary_pattern.md)
  * [Ludo the controller](Introduction#Ludo_the_controller.md)
  * [Game rules and their implementation](Introduction#Game_rules_and_their_implementation.md)
    * [The IntentDispatcher as central command executer](Introduction#The_IntentDispatcher_as_central_command_executer.md)
  * [Views and rendering](Introduction#Views_and_rendering.md)
  * [The Player interface](Introduction#The_Player_interface.md)

If you are just interested in developing an AI player for the game, you can leave out the first two topics and advance to the chapter "The `Player` interface". The topics are discussed in the order given by the list above.


# MVC as the primary pattern #
The Model View Controller pattern is a pattern for which many interpretations exist. However, all interpretations identify 3 main elements, namely Model, View and Controller. These elements might be named slightly different in some modelling approaches in order to emphasize the application of the MVC principle on different modelling levels (e.g. enterprise scope). But the concept behind these elements stays the same: Separation of Concerns. This means, each element embodies an interest or concern which overlaps as little as possible with the interest or concern of another element. Speaking in MVC terms: The View is responsible for displaying an application, the model implements the business logic and the controller controlls the program flow (e.g. start, end, user input ...). All these responsibilites require communication between the elements. Normally, the communication has a specfific flow. The model starts talking to the view about newly occured updates within the model, the view repaints itself in order to display such updates and the controller takes possible user input from the view and introduces this new information to the model. Most often, the communication between the view and the model is loosely coupled. This means, the model itself does not have any reference to a specfic view, but publishes so called events. A view can register itself at the model for receiving such events. The benefit is, that one model can have many views and vice versa. The seperations are somehow blury. For example, the Swing framework uses the MVC pattern within its components (e.g. `JButton`) and some people think that the controllor should be accounted for the view, as it, more or less, validates user input from the view. Although, introduced before the web existed as we know it today, the MVC seems to be the natural choice for dynamic web pages. There are many web frameworks which use MVC (JSF, Ruby in Rails, Flex, ...).

In our case, we just keep to the simple idea of separating the concerns. Therefore,  the `Ludo` class represents the controller, the `Game` class represents the main model class and the `View` interface represents the view. The `Game` class publishes two kind of events namely, `NotficationEvent` and `RequestForUserInputEvent`. The `NotficationEvent` is for information purposes and leads to a view update. On the other hand, the `RequestForUserInputEvent` requires user input (e.g. Player names) before the game can advance. Further, the `Game` class provides registration methods for any interrested party. The `Ludo` class contains the main method, sets up the game and validates the user input. The `View` interface should be implemented by any class which wants to display something. Currently (with code revision [r8](https://code.google.com/p/ai-ludo/source/detail?r=8)), there is one view available: `ShellView`. It simply prints out the events which occur on the current shell.

# `Ludo` the controller #
As said in the [Introduction](Introduction#Introduction.md), the `Ludo` class reflects the Controller element of the MVC pattern. It covers following responsibilities:
  * [Control flow methods](Introduction#Control_flow_methods.md)
    * [start](Introduction#start.md)
    * [exit](Introduction#exit.md)
  * [Entry point of the application](Introduction#Entry_point_of_the_application.md)
    * [Merging of View and Model](Introduction#Merging_of_View_and_Model.md)
    * [Starting up the IntentDispatcher](Introduction#Starting_up_the_IntentDispatcher.md)
  * [Validation of user input](Introduction#Validation_of_user_input.md)

This chapter is divided into subchapters according to the above responsibilities. So, we will start with exploring the control flow methods `start` and `exit`, advance to the entry point responsibility and end with the validation of user input.

## Control flow methods ##
The control flow mehtods do what they say: The control the program flow. The start method starts the application, while the exit method is called when the application exits.
### `start` ###

### `exit` ###
## Entry point of the application ##
### Merging of View and Model ###
### Starting up the `IntentDispatcher` ###
## Validation of user input ##

# Game rules and their implementation #
## The `IntentDispatcher` as central command executer ##
# Views and rendering #
# The `Player` interface #