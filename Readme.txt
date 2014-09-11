Author: Sachin Pradhan
This project is an implementation of a 2-player board game Othello.  
The game consists of a 8x8 board with 64 squares.  The first player is assigned a X stone while 
the second is assigned a O stone.  The game starts with two X stones and two O stones placed at the center 
placed squares diagonally. A player needs to place his/her stone on an empty square such that at least one of 
the opponent’s stones is trapped between the player’s stones.  The opponent’s stones can be trapped horizontally, 
vertically or diagonally.  So the trapped stones get converted to the player’s stone.  If a player is unable to 
trap any of the opponent’s stones then it is automatically the opponents turn.  When all 64 squares are filled 
with X and O stones, the player with the most stone wins.   

The game follows a distributed MVC architecture.  The game server runs on a remote computer.  Two players also from
different remote locations connect to the server and play the game against each other.  
When the server is started, it creates a game model and waits for 2 players to join.  The first player to join is assigned
stone X.  The second player is assigned stone O.  The game begins as soon as the second player joins.
The game is implemented using singleton, observer, state and iterator design patterns.

The game board is a singleton.  The client views are observers who register with the the model and get notified
whenever the game state changes.  The state pattern is used to keep track of the state of the game.  The game state object is 
passed between the model on the server to all the observers(views) on the client side as a serialized object.  The clients
then read the state and display an updated view.  The iterator pattern is used to update the score of each player by counting 
stones each player has in the 2 dimensional array.

Othello.Client package contains the client code (client view and client controller)
Othello.Iterator contains the iterator code
Othello.Model contains the game model
Othello.Server contains the server code (server controller)
Othello.State contains the state code
othello.properties contains the host name and port of the game server.  The client code uses these properties to connect to 
the server.

To compile the server code run the command below in the Othello folder.
>javac -d bin -sourcepath src src/Othello/Server/OthelloServer.java

To run the server execute the code below
> java -cp bin Othello.Server.OthelloServer


To compile the client code run the command below in the Othello folder
>javac -d bin -sourcepath src src/Othello/Client/OthelloClient.java

To run the server execute the code below
> java -cp bin Othello.Client.OthelloClient

Follow the prompts in the game.
