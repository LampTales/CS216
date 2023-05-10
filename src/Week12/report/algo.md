## Assignment 05: Reduction Report

#### $3-SAT$ $\le_{p}$ $3-Color$

​	To prove the above problem, we just have to prove that for any arbitrary instance of $3-SAT$, we can construct a $3-Color$ instance, whose true value is the same the instance of $3-SAT$.

​	To achieve this, we need to make use of the ability of $3-Color$ for encoding Boolean expressions. In a $3-Color$ graph, if we want the colors of two nodes, we can add an edge between them, so we just have to take the logic variables as nodes and connect the nodes with different true values together.

​	First, we define three special nodes $True$, $False$, $Base$. For each logic variable $v_{i}$ in $3-SAT$, we connect $v_{i}$, $\bar{v}_{i}$, $Base$ together as an triangle. Also connect $True$, $False$, $Base$ together as an triangle. Now we have a graph ensuring that no pairs of $v_{i}$, $\bar{v}_{i}$ have the same color, so they will not be both true or both false at the same time. And the node $Base$ has a color different from all the other nodes. Also we can see that $True$ and $False$ get the different color. We simply name the color of $True$ as true color, the other one as false color. For this reason, each pair of  $v_{i}$, $\bar{v}_{i}$ can have the true color for one node, and the false color for another node.

`png`

​	Now we need to deal with the graph according to the clauses in the $3-SAT$ instance. For each clause, we need to guarantee that at least one of the three nodes can get the true color. For such a clause with $v_{1}\vee v_{2}\vee v_{3}$, we the following sub-graph can ensure the property. This is easy to prove. We have already know that in the main graph, any  $v_{i}$, $\bar{v}_{i}$ cannot have the base color, so all the $v_{i}$ here can only be true or false. Assume that all the three $v_{i}$ are false.
