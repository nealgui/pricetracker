Overall Architecture
=====================

We have a 3-tier architecture with a presentation layer, logic layer, and a data layer. An *ArrayList* is passed across the layers, and the elements refer to objects of class *Item*, our real domain-specific object. Attributes of the *Item* class include a product name, price, store name, description, image (using sample images for now), a review of the product, and an item stock value to indicate availability. 

In the data layer, we have *ItemStorageSQL* which implements an interface *IDBLayer* and accesses the database. In the *search* method, a query is passed over from the UI and a list of corresponding items is built from the database and returned. This (ArrayList) list of items can then be read and processed in the logic layer, before being passed forward to the UI (through an adapter) and results are shown to the user for selection and purchase.

The logic layer contains a "logicLayer" class, a corresponding logic interface, and an adapter class for binding the DSO to a view that can be shown to the user (as well as adapters for binding recycler views to their relevant adapters). The *itemAdapter* inherits from the *RecyclerView* adapter class, and updates items in the view the user sees (the *itemAdapter* itself is kept in the presentation layer because it handles intents and the actual views). The *logicLayer* class handles updating item information (on the individual item level), adding items to a cart, using item count and cost to calculate total price, and handles regional taxes as well. It uses both the *logic* interface and the *IDBLayer* interface. 

The presentation layer has several activity classes, one for each major operation. They cover the presentation handling for the welcome activity, where the user can set a region; the main activity, where the user can search for products and sort by price or name; the purchasing activity, for buying an item directly or adding multiple products to a cart first and checking out later; as well as the history activity, where the user can see their order history.


```
             +---------------------------------------+   ^
 Presentation|  - cart activity, cart adapter        |   |
  Layer      |  - history activity, history adapter  |   |
             |  - main activity                      |   |
             |  - item adapter                       |   |
             |  - purchase item activity             |   |
             |  - welcome activity                   |   |
             +---------------------------------------+   |
                                                         |
                                                         |
             +----------------------------------------+  |  Item (List)
 Logic Layer |  - logic layer class                   |  |  (DSO)
             |  - logic layer interface               |  +
             |  - item adapter interface              |  |
             |  - general adapter interface           |  |
             +----------------------------------------+  |
                                                         |
                                                         |
             +----------------------------------------+  |
 Data Layer  |  - item storage (SQL)                  |  |
             |  - item storage (stub)                 |  |
             |  - IDB layer interface                 |  |
             |                                        |  |
             +----------------------------------------+  v
```