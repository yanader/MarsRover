# Mars Rover

### Description

Welcome to Mars. This programme will allow you to deploy a Rover and explore the surface of Mars.

### The Plateau

The Plateau is represented by a two-dimensional grid of size x * y. These sizes are provided by the user and create a grid with coordinates (0, 0) through to (x -1, y - 1).
For the purpose of the simulation, (0, 0) is considered the South-West corner and (x-1, y-1) is considered the North-East corner.

### The Rover

The Rover is a vehicle that will have its initial position dictated by the user and then be given move instructions that will allow it to navigate the plateau.
The rover maintains its position which consists of an X coordinate, a Y coordinate and the cardinal compass direction it is currently facing (N/E/S/W).

### Instructions


To start the programme, navigate via terminal to the location of MarsRover.jar and execute with

> `java -jar MarsRover.jar`

or specify the filepath from your current location with

> `java -jar jarfiles/MarsRover.jar`

This will provide you with a custom-built Command Centre which will allow you to control the Mars Rover.

1. Setup
   1. Provide the size of the Plateau to be explored in the format "X Y" where X and Y are positive integers.
   2. Provide the starting position of the Rover in the format "X Y D" where:
      - X -> initial x coordinate from 0 to the length of the X axis minus 1
      - Y -> initial y coordinate from 0 to the length of the Y axis minus 1
      - D -> initial direction the rover is facing where D is one of the four cardinal directions (N/E/S/W)
2. Further Instructions
   1. Provide the Rover with movement instructions with three uppercase characters.
      - L -> rotate 90&deg; left/anti-clockwise
      - R -> rotate 90&deg; right/clockwise
      - M -> move one position forwards
      - Instructions can be provided in a chain. Example:
        - Input: MMRMMLMM
          - Moves forward two spaces
          - Rotates right
          - Moves forward two spaces
          - Rotates Left
          - Moves forward two spaces
      - On providing an instructions set  that would cause a collision or lead the Rover off the edge of the Plateau:
        - If the first step of the instructions caused the collision, the user will be informed the instruction can not be carried out.
        - If a subset of instructions would be valid, the user will be offered a truncated version of their instructions and asked for an input.
          - Y -> Execute the truncated instructions
          - N -> Reject the truncation and resume the programme.
   2. Input 'H' to access help
   3. Input 'Q' to quit the Mars Rover Application


### Documentation

The codebase is split into several packages as follows:

1. dataclasses - provides Classes and Enums to dictate PlateauSizes, Vehicle positions, cardinal directions and allowed instructions
2. parsers - provides Classes for validating and parsing user inputs including conversion from String to Instruction[]
3. logic - provides Classes for Plateau and vehicle types as well as interfaces to dictate vehicle behaviours. Any new vehicle type should extend Vehicle and implement appropriate interfaces
4. userinterface - provides the CommandCentre class which initialises the programme, controls setup and continued programme flow until the user exits.

### Future Inclusions

1. Truncation of user input
   - On receiving an instruction set that would ordinarily be rejected by the Command Center, the option to execute a truncated instruction set will be offered which will execute the instruction up until but not including the individual instruction that is invalid.
2. Additional Vehicle Types with appropriate interfaces. These additions will allow for surface samples to be tested and, if desired, mined for resources
   - Tester implements Testable, Movable
   - Miner implements Minable
3. Inclusion of multiple Plateaus (landing sites) per mission with the ability to move Vehicles between them.
