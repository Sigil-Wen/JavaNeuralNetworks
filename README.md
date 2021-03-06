# JavaNeuralNetworks by Sigil Wen
 Neural Network Class In Java. Back Propagation in development.
 
## Creating a NeuralNetwork Object
NeuralNetwork nameOfNeuralNetwork = new NeuralNetwork(int[], boolean);

#### initNodes(int[])
Initializes the nodes of the neural network and stores it in instance variable nodes

#### initWeights()
Randomizes Weights and biases for the neural networks nodes

## Object Instance Variables
#### nodes 
An ArrayList<Matrix>() that contains the weights and biases for each node of the network

#### int[] dim
Integer Array containing the dimensions of the neural network

#### softmax
Boolean Value to specify if the network uses softmax activation. Default value is false.

## Methods
#### displayNet()
Displays each layer of the network with corresponding weight and bias values

#### displayResults()
Displays the output of the neural network given a dataset

#### forwardPass()
Forward Propagation through the neural network given a dataset. Returns a 2D array of oututs

#### softmax()
Applies softmax function to output

## Loss Functions
#### meanSquareError()
Mean Square Error Loss
#### logisticError()
Logistic Error Loss 
