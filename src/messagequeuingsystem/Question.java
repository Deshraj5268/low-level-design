package messagequeuingsystem;



/*
*
* Design a message broker supporting producer-consumer model.

Mandatory Requirements:

We should have multiple Queues in the system
Producer publishes messages to the queue.
Consumer fetches messages from the consumer. once consumer ack the message, queue should mark it as delivered.
Whenever a producer publishes a message, all the consumers of the queue should receive the message. ( fan-out model)
Messages should be persistent.
Additional requirements:

Consumers can ask the queue to give the most recent X messages.
How do you handle delivering duplicate messages to the same consumer?
APIS:

createQueue(queueName) -> queueId
subscribe(queueId, consumer) -> boolean
publish(queueId, message) -> boolean
readOffset(topidId, subscriber, offset) -> boolean
Expectation:

Your code should be executable, readable & should be clean.
Your code should be adequately refactored, and exceptions should be gracefully handled.
Your code should cover all the functionality
You code should handle all the concurrency related issues
Guidelines:

You should use the in-memory data structure of your preferred language to store the data but have the right abstractions so that other persistent stores can be plugged in.
Bonus Points:

Translations of messages between different protocols, at least once delivery , Transformations between format of the data between sender and receiver
How to make sure we are Delivering to right receiver (P2P)
How to manage Distribution of events from different sources to different destinations
Evaluation criteria:

Executable code.
Code readability and testability
Refactored code
Abstraction
Object-Oriented concepts.
Language proficiency.
[execution time limit] 3 seconds (java)

[memory limit] 1 GB
*
* */
public class Question {
}
