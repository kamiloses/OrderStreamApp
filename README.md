Reactive api that allows a customer to order products.
Once product is ordered ,an admin can  change  order status.
This will activate kafka that will inform emailSenderService and will Inform database about changing order status.

Below is a screenshot from the Kafka plugin demonstrating that the Kafka consumer is receiving messages correctly.



![image](https://github.com/user-attachments/assets/83cc11b7-4c7a-4376-a4a0-522e1a5eaa59)

I have also integrated Spring Batch to automatically remove orders 7 days after their status is updated to "shipped". Currently, this task needs to be triggered manually to clean up redundant records from database, but I’ve tested it and it works as expected.

All requests 

![image](https://github.com/user-attachments/assets/54894220-8285-478a-91d1-b28fc041ddc5)

Frameworks/Libraries used:

- Reactive MongoDB
- Project Reactor
- Spring Batch
- Spring Security
- Apache Kafka
- Testcontainers for Kafka
- MySQL (for Spring Batch)
- JUnit
