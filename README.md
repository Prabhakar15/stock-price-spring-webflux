Real-Time Event Streaming Using Spring WebFlux

Overview
===========
Here we are going to implement a short example of real-time events streaming using Spring WebFlux. 
A dashboard showing real-time pricing information about the stock market.


Data Model
===========
Let’s start by creating a model for the data we’re going to be streaming. We’ll hold four pieces of information for every stock we’ll maintain:

=> symbol: corporation’s shortcode
=> name: corporation’s name
=> currentPrice: in the stock market, in USD
=> lastUpdated: when the price was last updated


REST Controller
==================
REST controller to serve real-time content for our example. Our controller will contain a hashmap field defined to serve as an in-memory database for our content:

This controller has mainly two roles:

1) Accepting content updates, which are stock objects in our example, from authorized tiers
2) Real-time streaming of content to the consumer, which is the dashboard in our example.


Accepting Content Updates
============================
Now, let’s allow tiers in charge to update stocks pricing by performing PUT request to /stocks:

The method expects a request body with JSON array of updated stocks. We’ve just stored each updated stock object we received directly into the hashmap database, using stock’s symbol as its corresponding key. This’ll either add a new stock entry to the map if the key doesn’t already exist or update the existing stock’s data otherwise.

We’ve finally updated the stock’s last updated time to the host system’s current one, in order to make it detectable as updated by the streaming method.


Streaming Real-Time Content
===========================
This is the key part of this article, which would make us able to satisfy the main requirement of our example. We’d create a Flux REST operation mapped to GET /stocks that does two things:

1) Returning updates about changes happened in the content (the stock market) using a reactive programming methodology
2) Keeping pushing real-time updates to the client continuously using server-sent events API

In other words, we’re going to mix Spring WebFlux together with Server-Sent Events to achieve our final goal. Therefore, we’ll create the REST method with a return of a Flux of Server-Sent events produced in text/event-stream MIME-type format, each event will include a collection of stocks objects, representing the data of newly updated ones:



The Client
============
Now, we’ll go on to the front-end part. Let’s create dashboard page /stocks/dashboard.html with a stocks container div, where stock widgets are be going to be displayed:

Our main task now is connecting to our streaming REST operation, and keep receiving stocks updates and displaying them on the dashboard. To do this in JavaScript, we’ll use an EvenSource object to connect to our endpoint, then we’ll use addEventListener() to keep track of all updates sent on the events channel stocks-changed


Running the Example
=======================
=> Build and run the project using maven
=> Navigate to http://localhost:8080/dashboard.html
=> You’d see a dashboard with initial arbitrary stock market data
=> To trigger a live update, call PUT /update with the following curl command/Postman:

curl --header "Content-Type: application/json" \
  --request PUT \
  --data '[{"symbol":"FB", "name":"Facebook", "currentPrice":273}]' \
  http://localhost:8080/stocks
  
