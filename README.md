# 2048 AI
## About
This is a 2048 clone on a 6 x 6 grid, with an AI developed to play it. Currently, the AI does sufficiently well
and exceeds the standard performance of a human player, in both speed (1024 moves a second) and average
highest tile.

This project was developed in Java using [Processing](https://processing.org/) for GUI rendering.

## Sample Run
![](https://github.com/danielbatchford/2048AI/blob/master/sample.gif)

## AI implementation
Currently, the AI obeys the following logic:
- For each board state `s`, `s_left`,`s_right`,`s_up` and `s_down` are calculated, where the subscript represents
one move from the state `s`. A cost function `cost(s_move)` is calculated and `min(cost(move))` is chosen as the
next move.
#### The Cost Function

`cost(s_move)` is calculated in the following way:  
If there are no empty tiles on the grid, the cost is an arbitrarily large value.
Else, the cost is a weighted sum of the board column index of the tile multiplied the tile's value, plus the
sum of the number of adjacent diagonal tiles with matching values.
## How To Run
Clone the repository and run `java -jar 2048AI.jar` in the cloned directory. Settings can be changed by
modiying the `Constants` interface located at `src/danielbatchford/Constants`.

#### Controls
- Use number keys `0-9` to control the game speed.
- Use `P` to pause the game
- Use `R` to restart the game 

## Contributing
Feel free to submit a pull request to contribute to this project.

## Credits
[Processing for Java](https://processing.org/) 
