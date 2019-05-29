# SiTE : Simplified Tetris Engine

SiTE is a simplified version of the tetris game engine. This is a console based application, which reads the sequence of shapes as an input to the game engine, and produces the resultant height (in number of rows) from the final state of the game as an output.

Shapes supported by this game engine are represented by letters, the letters used are Q, Z, S, T, I, L, and J. Check the below diagram for the representaion for the shapes currently supported.

![shapes](https://i.imgur.com/toGkYJ5.png)


Features of this game engine.

    - Continously reads the input sequences and produces the output.
    - Logs every state of the game.
    - No libraries used for the game engine, except logging and codox for documentation.

## Installation

Download [tetris-app-0.1.0-SNAPSHOT-standalone.jar](./target/uberjar/tetris-app-0.1.0-SNAPSHOT-standalone.jar)

## Usage

Run it as java standalone application.

    $ java -jar tetris-app-0.1.0-standalone.jar
    $ java -jar tetris-app-0.1.0-standalone.jar < input.txt > output.tx


## Examples
![Example1](https://i.imgur.com/skTZHnW.png)

![Example2-1](https://i.imgur.com/5lndiDU.png)
![Example2-2](https://i.imgur.com/YAONUGS.png)

![Example3](https://i.imgur.com/QnUrfNG.png)

## License

Copyright © 2019 

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
