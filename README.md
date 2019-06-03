# RPG Game

This is a Text based RPG game.

Its composed of the following modules:

- rpg-engine
- rpg-console-engine
- rpg-commandline
- rpg-residentevil
    
    
## rpg-engine

This is the core. The project that process all game functions (business rules).

Packages:

- domain: *use cases*
- gateways: *gateways interfaces*

## rpg-console-engine

Core structures for console application.

- `RpgConsoleGame`: *the interface that defines the console game data.*

## rpg-commandline

The command line rpg game application. It uses the `rpg-engine`, `rpg-console-engine` and create the Resident Evil game using the `rpg-residentevil`

- `Main`: *the initial class*
- `GameManager`: *responsible to run the presenters and handle the responses*
- `/console`: *console implementation to print with color*
- `/context/ApplicationContext`: *the DI container*
- `/data/gateways`: *the implementation of the rpg-engine gateways*
- `/data/repositories`: *in-memory repositories*
- `/models`:  *the models that will pass through pages and views*
- `/pages`: *the pages of the application*
- `/presenter`: *the presenters that call the use cases, build the models to pass them to the views/pages*
- `/responses`: *the responses of pages*
- `/views`: *the view to be uses on pages*

## rpg-residentevil

A example of game. Depends on `rpg-engine` and `rpg-console-engine`.

