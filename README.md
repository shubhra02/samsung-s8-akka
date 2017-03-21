Create an akka application to handle multiple purchases of a limited edition smart phone samsung galaxy S8 with limited number of items (1000). The application should do following things:

1. Customer should be allowed to purchase 1 item at a time with info 
 Customer name,  address, credit card number, mobile number.

2. Crate actors to handle following things:
a. Handle all the requests (PurchaseRequestHandler)
b. Validate remaining items availability.(ValidationActor)
c. Purchase (PurchaseActor)

3. After successful purchase there should be a log for successful purchase. Similarly for error.

4. Things to be taken care of:
a. There should be proper logs for every actor activity so that the request and execution can be tracked.
b. Use router for purchase actors.
