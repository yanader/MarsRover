# Mars Rover

### Description

Welcome to Mars. This programme will allow you to deploy Vehicles and explore the surface of Mars.

### The Plateau

The Plateau is represented by a two-dimensional grid of size x * y. These sizes are provided by the user and create a grid with coordinates (0, 0) through to (x -1, y - 1).
For the purpose of the simulation, (0, 0) is considered the South-West corner and (x-1, y-1) is considered the North-East corner.

## Vehicles

### The Rover

The Rover is a vehicle that will have its initial position dictated by the user and then be given move instructions that will allow it to navigate the plateau.
The Rover maintains its position which consists of X and Y coordinates and the cardinal compass direction it is currently facing (N/E/S/W).

### The Miner

A Miner can be dropped on the surface at a location dictated by the user. The Miner is unable to move but can dig for valuable resources below the surface.
The Miner maintains its position which consists of X and Y coordinates.

## The simulation

### Instructions

To start the programme, navigate via terminal to the location of MarsRover.jar and execute with

> `java -jar MarsRover.jar`

or specify the filepath from your current location with

> `java -jar jarfiles/MarsRover.jar`

This will provide you with a custom-built Command Centre which will allow you to control the Mars Rover.

1. Setup
   1. Provide the size of the Plateau to be explored in the format "X Y" where X and Y are positive integers.
   2. Provide the starting position of your first Rover in the format "X Y D" where:
      - X -> initial x coordinate from 0 to the length of the X axis minus 1
      - Y -> initial y coordinate from 0 to the length of the Y axis minus 1
      - D -> initial direction the rover is facing where D is one of the four cardinal directions (N/E/S/W)
2. Further Instructions. The user will be given a choice:
   1. Launch Vehicle
      - The user can land a new vehicle on the surface.
      - You will be prompted for the vehicle type and then asked for appropriate Position details
      - The created vehicle will be your active vehicle
   2. Activate Vehicle
      - The user will be provided with a list of all Vehicles on the surface and be able to choose which to activate
   3. Control Vehicle
      - Depending on the vehicle type, different commands can be used to control the vehicle.
        1. Rover:
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
        2. Miner:
           - D -> Dig
   - Input 'H' to access help
   - Input '0' to quit the Mars Rover Application


### Documentation

The codebase is split into several packages as follows:

1. dataclasses - provides Classes and Enums to dictate PlateauSizes, Vehicle positions, cardinal directions and allowed instructions
2. parsers - provides Classes for validating and parsing user inputs including conversion from String to Instruction[]
3. logic - provides Classes for Plateau and vehicle types as well as interfaces to dictate vehicle behaviours. Any new vehicle type should extend Vehicle and implement appropriate interfaces
4. userinterface - provides the CommandCentre class which initialises the programme, controls setup and continued programme flow until the user exits and the UserInterface that handles user inputs

#### In memory database

To run with an in memory, H2 database, create a local config file at:

```/config/db.properties```

Include the following properties. (Note: The generic H2 username is 'sa')

1.  `db.url=jdbc:h2:mem:test`
2.  `db.username=<USERNAME>`
3.  `db.password=<PASSWORD>`

### Future Inclusions

1. ~Truncation of user input~
   - ~On receiving an instruction set that would ordinarily be rejected by the Command Center, the option to execute a truncated instruction set will be offered which will execute the instruction up until but not including the individual instruction that is invalid.~
2. Additional Vehicle Types with appropriate interfaces. These additions will allow for surface samples to be tested and, if desired, mined for resources
   - Tester implements Testable, Movable
   - ~Miner implements Minable~
3. Inclusion of multiple Plateaus (landing sites) per mission with the ability to move Vehicles between them.
4. Include an in memory H2 database that compiles a mission report that will output to a file on termination of the programme
   - In memory database is in place. Report writer is under construction on a separate branch.