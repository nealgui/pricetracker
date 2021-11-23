Iteration 2 Worksheet
=====================

Paying off technical debt
-----------------

(1) [Tech debt issue 1](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/merge_requests/10/diffs?commit_id=92429b42dcb375351b11401e2cf7b808d6798a4b#834f6b89707f8a7bd06e878dbf0417817dab0895_50_63): This is an example of prudent and deliberate technical debt. We wanted to get the UI integration up and running so other members could work on features dependent on this, so we had a data file being fed into an input stream in the UI layer (instead of in a more appropriate layer, like in the data handling section). We implemented this intentionally, as we needed to get everything running together, and we planned to deal with the consequences later, by redoing our process once the database and logic layer interfaces were completely up-to-date. We started paying off this technical debt just a few days later, with commits [reading in the data layer](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/ccfdbd1653382f1bf9c779ca921619f624657487#2aa4cfe72965e8111cc55d0084a5d370ddf2ad18_82_115) and [context injection](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/ccfdbd1653382f1bf9c779ca921619f624657487#834f6b89707f8a7bd06e878dbf0417817dab0895_49_48). 

(2) [Tech debt issue 2](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/97c9b7d0503fc32d38f760166fcbadff6539ee07): This was an example of reckless and deliberate technical debt, unfortunately, as we thought it would be easier to assess class changes at a glance without having them subdivided into a proper package hierarchy. We *did* acknowledge that we would need to deal with this eventually, but since we waited for all other major classes and interfaces to settle before we did this, we ended up having to resolve this issue fairly last-minute, in a commit where we [moved files]((https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/ef62a5b5e37f3c8f5426aa1d436d93ea8e721cc0)) into appropriate directories and cleared away the loose files in [this commit](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/29bc94d9c35b7c22a1593ac22ddbfb308eedd392). It should be resolved in [this merge](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/merge_requests/17).

(3) [Side Note](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/merge_requests/20/diffs?commit_id=f17447e899e39cb302710b256fa1a4c309fcd781): We've been using the android specific imports of SQLite rather than the general java version (android.database.sqlite vs java.sql.sqlite). This is technical debt that we will pay off in iteration 3 by switching to the vanilla version to reduce coupling.

SOLID
-----------------

[Dependency Inversion Issue](https://code.cs.umanitoba.ca/3350-winter-2021-a03/group-1/-/issues/81). In this SOLID violation, multiple methods in MainActivity all have repeated code that depend on a TaskView object. It may be a good idea to implement dependency injection; a class that can retrieve the TaskView (and convert it to a String, as the methods are doing) before passing it along to those methods.

Retrospective
-----------------

In our retrospective after iteration 1, as a group we decided to have a mental deadline of a day before the actual due date, so that we would have a day for fine-tuning and double-checking. We also decided to have more frequent meetings and to communicate more often so that we could all stay on the same page. As one of our group members left the course near the end of iteration 1, we also needed to readjust our plans for what we could accomplish, and to make sure we would work on features together (as opposed to independently and in isolation).

As far as the double-checking goes, every merge request is reviewed by someone other than the submitter (and often multiple people), to allow for peer review and to make sure all tests still pass with each new merge. Examples of these include the [UI integration merge](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/merge_requests/10) and the [purchase feature merge](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/merge_requests/11).

We also managed to adjust our time estimates to be more realistic, as shown by our estimates for our [UI search feature](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/issues/18), and the [comparison feature](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/issues/6). (Although the timing of when these were updated in GitLab looks as though we made estimates after the time spent, we *did* actually discuss this earlier, just in vague terms of expected time spent).

We commited to remote branches more frequently throughout this iteration, and over the course of more days than in the last one (where we left it until late). In our [branch graph](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/network/master) we can see that our communication improved, as merge conflicts were not an issue.

Design patterns
-----------------

We used the structural design pattern *Adapter* in our [itemAdapter class](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/dd1c0989afe59461a7f23423d6e77f85d6dfd08b#3811adc2ea4be1bfacc80c1f14ca574692fad506_0_16), which acts as a kind of bridge between our user interface and the *Item* data being passed around in an ArrayList. The adapter binds our data to the view holder; it is linked to the UI [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/dd1c0989afe59461a7f23423d6e77f85d6dfd08b#834f6b89707f8a7bd06e878dbf0417817dab0895_54_55) and helps us fill the rest of the data to show in the UI. We could technically go directly from data to UI in our case, since the ArrayList is not incompatible with our UI components. Using a *RecyclerView* (and therefore needing an adapter) means we have more flexibility and control over the UI, and improving on the views does not become too complex even as the data we pass into it will change (for example, when we start passing images).

Iteration 1 Feedback fixes
-----------------

[Issue noted by grader](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/issues/19): We originally manually created our own Linked List data structure, with ItemList and Node classes, and in the feedback found that we could use an existing structure like an ArrayList instead. We did so, passing either *Item* objects or ArrayLists containing different items. As the *ItemList* was one of our domain-specific objects, we had to refactor the interfaces as well as our methods for sorting and data storage handling. Within each feature branch, we individually phased out unnecessary classes, and were eventually able to remove them from the master branch without issue. We removed the now unnecessary test [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/09dfbddd1834544fbed20b8d7fa82be6e170ba92#7e14a29b1f1b526378c30daa781905972b270e25_12_0), replaced *ItemList*s with ArrayLists [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/be5092f1678d4c5a02a41e139e539cc81617f485), and fully resolved the issue [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/e9848a4a2688fdbbe7881f5c638034c22ebd1adb).

