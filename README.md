## Apache Flink - Exercises (Basic Use)

### Batch (DataSet API)
#### Exercises
For the batch exercises I've used a csv concerning winners Oscar's films 
downloaded in this link https://cs.uwaterloo.ca/~s255khan/files/pictures.csv.

The csv modelling using a case class is the following:
 ```scala
case class Film (name: String, year: String, nominations: String, rating:String, duration: String, genre1: String, genre2: String, release: String, metacritic: String, synopsis: String)
```
1. Average of film´s nominations

2. Metacritic average, grouped by film´s genres.

3. Average duration of winning films by ages

4. How many winners films include at least their title at the synopsis?

5. How many winners films include all the words from the title?

6. Which is the standar deviation of the winners film´s rating in XXI century?



### Streaming (DataStream API)
For these exercises I've used a websocket for the generation 
of events about meetup (http://meetup.com). The source 
captures people that are inscribed to events in real-time.
Moreover the websocket is implemented using a RichFunction(API Flink)

1. Delete the bad formed objects

2. Number of users that have confirmed the event in the last 10 seconds.

3. Number of users  users that have confirmed the event in the last 20 seconds updating each 5 seconds. 

4. Number of users by country each 5 seconds. 

5. Calculate Trending topics knowing that is a last minute information and updating the result each 10 seconds. 

### Documentation
All exercises are implemented using v1.2.0 https://ci.apache.org/projects/flink/flink-docs-release-1.2/

The documentation of Apache Flink is located on the website: http://flink.apache.org