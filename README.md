# State-Machine
State Machine Created Using Java. The application takes 2 command line parameters(a state machine filename, then a filename of inputs to validate).
For example: validator myStateMachine.txt myInputs.txt <br/> 

#### The state machine will come from a text file in the following space delimited format
0 A 1 <br/>
0 a 1 <br/>
1 B 2 <br/>
1 b 2 <br/>
2 C 999 

#### There are three terminal situations
999 – success – when this state is reached, print “Success” and end. <br/>
failure (no transitions match) – print “Failure at position ___, found character _.” <br/>
failure (input string ends early) – print “Input string ended before success transition.” 

#### Input File: The inputs to validate is some number (1 or more) of lines of text
abc <br/>
AbC 

# What I Learned 
* How to use a try-catch statement optimize my application
* How to create a buffered reader to read lines through a text file 
* How to use an enhanced for-loop to iterate over hashmap keys, entries or values
