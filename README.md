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
|time_distance|	Double|	time distance between cities city_id_from and city_id_to (in minutes)|


### 4.	Algorithm description

#### 4.1. Original Dijkstra Algorithm description:

1. Build graph in memory.
2. Create list of passed nodes. Set initial(start) node as passed.
3. Create heap as supporting data structures. Add start node to heap (with time distance as 0).
4. Take node with min time distance and remove it from heap (current node). Add it to passed list. 
5. Take all neighbours of current node from the graph and count time destination for each of them that are not passed.
6. For each node count new destination time. Add it to heap or update it in heap if new destination less than old destination stored in heap and time limit. Add these nodes to result list.
7. Do points 4-6 until heap is not empty.

For memory savings, in modified Dijkstra Algorithm graph isn't building in memory, but all neighbours takes directly from database.

#### 4.2. Modified Dijkstra Algorithm description:

1. Create heap as supporting data structures. Add start node to heap (with time distance as 0).
2. Take node with min time distance and remove it from heap (current node). Add it to passed list. 
3. Get from database all neighbours of current node(that are not in passed list and which time_distance is no more then remaining time distance(entered time limit minus time distance to current node).
4. For each node count new destination time. Add it to heap or update it in heap if new destination less than old destination stored in heap. Add these nodes to result list.
5. Do points 2-4 until heap is not empty.

Asymptotic upper bound of the algorithm is O(m log(n)) where n –number of nodes and m – number of edges.


## 5.	ARCHITECTURE
Commute range problem application designed as REST API application.
Technologies: java 8, maven, Spring Boot 2.0.5, H2 database.
To run application go to project directory, than in command line call "mvn spring-boot:run"
To run unit tests go to project directory, than in command line call "mvn test"
Data: used immutable dataset that inserts when application runs (see data.sql file)

API: http://localhost:8080/reachable-cities/{cityId}?timeLimit={timeLimit}
where cityId - is id of start city, timeLimit - limit of time destination to cities(in minutes).
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