# Commute Range System

## 1.   OVERVIEW
This application allows to find reachable towns/cities from a starting point in a given amount of time.

### 2.	Model

Each city is represented as graph vertex and the path between each two cities is the graph edge.
For searching shortest paths between cities was used modified Dijkstra Algorithm.

### 3.	Database design
#### 3.1.	City table
| Column name | Type | Description |
| ------ | ------ | ------ |
| id|	INT(11)|	Unique city id|
|name|	VARCHAR(255)|	City name|

#### 3.2. Route table
| Column name | Type  | Description |
| ------ | ------ | ------ |
|id|	INT(11)|	Unique route index|
|city_id_from|	INT(11)|	city id route starts|
|city_id_to|	INT(11)|city id route ends|
|time_distance|	Double|	time distance between cities city_id_from and city_id_to|


### 4.	Algorithm description

#### 4.1. Original Dijkstra Algorithm description:

1. Build graph in memory.  Mark each vertex as unvisited.
2. Set initial(start) node. 
3. Create list of passed nodes. Set initial node as passed.
4. Create heap as supporting data structures. Add all nodes to heap. Assign a tentative distance value for every node (0 for initial, positive infinity for others).
5. Take node with min distance and remove it from heap. Check if destination to this node less than timeLimit. add it to result list if yes. Add it to passed list. Take all its neighbours from the graph and count time destination for each of them. Update heap (update time destination for nodes if it less than value in heap).
6. Do point 5 until heap is not empty.

For memory savings, in modified Dijkstra Algorithm graph isn't building in memory, but all neighbours takes directly from database.

#### 4.1. Modified Dijkstra Algorithm description:

1. Added start city to heap(used TreeSet). Add its id to list of passed cities.
2. Take closest city (graph vertex) from heap (current city). Get from database all neighbour cities(that wasn't passed before) for this city (graph edges) that are less than remaining time limit (entered time limit minus time limit in current city). Add these city IDs to reacheable city IDs list.
3. For each city count new destination time. Add it to heap or update it in heap if new destination less than old destination stored in heap.
4. Do points 2-3 until heap is not empty.

Asymptotic upper bound of the algorithm is O(m log(n)) where n –number of nodes and m – number of edges.


## 5.	ARCHITECTURE
Commute range problem application designed as REST API application.
Technologies: java 8, Spring Boot 2.0.5, H2 database.

API: http://localhost:8080/reachable-cities/{cityId}?timeLimit={timeLimit}
where cityId - is id of start city, timeLimit - limit of time destination to cities.
example: 
http://localhost:8080/reachable-cities/9?timeLimit=30
example response:

>[
>    {
>        "id": 1,
>        "name": "New York"
>    },
>    {
>        "id": 2,
>        "name": "Long Island"
>    },
>    {
>        "id": 5,
>        "name": "Hartford"
>    }
>]