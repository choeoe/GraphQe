# GraphQE: Proving Cypher Query Equivalence 

GraphQE is an automated prover to determine whether two Cypher queries are semantically equivalent.
In GraphQE, we model Cypher queries as U-semiring based Cypher expressions, and prove the equivalence of Cypher queries using constraint solvers theory.
We further construct an equivalent Cypher query dataset CyEqSet that contain 145 pairs of equivalent Cypher queries. 
We evaluate GraphQE on this dataset, and have proved 139 pairs of equivalent Cypher
queries.

## CyEqSet

We construct a dataset CyEqSet with 148 equivalent Cypher query pairs from the following two approaches.
 + By translating the open-source equivalent SQL query pairs from Calcite into equivalent Cypher query pairs, we obtain 77 equivalent Cypher query pairs. 
 + By constructing equivalent Cypher query pairs by rewriting Cypher queries using existing equivalent rewriting rules, we obtain 68 equivalent Cypher query pairs.
 
## Requirements

To run GraphQE, ensure your system meets the following requirements:

- **Java Development Kit (JDK)**: Version 17
- **Maven**: Version 3.8 or higher
- **Operator System**: Ubuntu 1804

## Installation

1. **Clone the repository**:

    ```sh
    git clone https://github.com/choeoe/GraphQe
    cd GraphQE
    ```

2. **Build the project using Maven and run**:

    ```sh
    mvn clean package
    cd dataset
    java -Djava.library.path=./ -cp GraphQe-1.0-SNAPSHOT-jar-wi-dependencies.jar:z3/com.microsoft.z3.jar graphqe.Main
    ```

3. **Or run the application using our script**:

    ```sh
    bash run.sh
    ```

## Usage

To use GraphQE, follow these steps:

1. **Prepare your queries**: Write the Cypher queries you want to compare into /dataset/dataset.xlsx.
2. **Run GraphQE with the queries**:

    if you have compiled the project:
    ```
    cd dataset
    java -Djava.library.path=./ -cp GraphQe-1.0-SNAPSHOT-jar-wi-dependencies.jar:z3/com.microsoft.z3.jar graphqe.Main
    ```
    otherwise
    ```sh
    bash run.sh
    ```

3. **Review the results**: GraphQE will write the verification result back into the
   dataset. Please check the result at the end of the Cypher query pair. Note
   that if a Cypher query pair is proven NQE, it still might be equivalent,
   since GraphQE is incomplete.

## Test Result
GraphQE has successfully proved 139/145 pairs of equivalent Cypher queries in CyEqSet.
