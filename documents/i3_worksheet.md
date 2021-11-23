What technical debt has been cleaned up
========================================

*Show links to a commit where you paid off technical debt. Write 2-5 sentences that explain what debt was paid, and what its classification is.*

[Tech debt](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/commit/a0a16d29621b913d07e6220d824b44b1fdc5fab6): We did not have much testing coverage of our persistence layer, and initially left it as something that would not be problematic (reckless and deliberate, unfortunately). When attempting to use mockito to test it, however, it became very clear that there was tight coupling between sections that made it hard to test any one part, or the behaviour of different methods. Some refactoring was necessary to get a more loosely coupled layer where we tried to keep each method abiding by the Solid Responsibility Principle.

What technical debt did you leave?
==================================

*What one item would you like to fix, and can't? Anything you write will not
be marked negatively. Classify this debt.*

We left some inadvertent and prudent technical debt in terms of the libraries we used. For loading images, and for developing the graphs (for showing price history), we used older libraries that may not be maintained in the future, and may rely on dependencies we cannot use someday. There may also be faster options available eventually, but at this time they were the best options for providing the features we needed. 


Discuss a Feature or User Story that was cut/re-prioritized
============================================

*When did you change the priority of a Feature or User Story? Why was it re-prioritized? Provide a link to the Feature or User Story. This can be from any iteration.*

[Re-prioritized feature](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/issues/13): We re-prioritized using an actual product image from a high priority task to a low one. This was because the product information we were using were randomly generated (not gotten through web scraping) so finding a relevant image for each product would be a much bigger task than we could accomplish along with the other features. This in particular we made a low priority because there were no other features or user stories depending on the images being real, so there was not any immediate need to finish this one (as opposed to more critical tasks like dealing with testing).

Acceptance test/end-to-end
==========================

*Write a discussion about an end-to-end test that you wrote. What did you test, how did you set up the test so it was not flaky? Provide a link to that test.*

One of the end-to-end tests that we wrote was for the [Welcome Activity](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/blob/master/app/src/androidTest/java/pricetracker/Presentation/WelcomeActivityTest.java), where we tested that selecting each of the four buttons available on the welcome page would set the region correctly. To make sure this would happen and so we could test it without needing any special data retrieval, we added a text output on the (main) search bar page with the newly updated region. This way we could check that the region being used by the app matched the value on the button selected on the previous page. By [enabling developer options](https://developer.android.com/studio/debug/dev-options#enable) and following the tip for [device settings](https://www.tutorialspoint.com/espresso_testing/espresso_testing_setup_instructions.htm) at the bottom of the page, we managed to prevent this test from being flaky.

Acceptance test, untestable
===============

*What challenges did you face when creating acceptance tests? What was difficult or impossible to test?*

Testing the sorting was difficult (but not impossible, once we gave it enough time and research). Initially, we were checking that this was done correctly using visual inspection, but to complete the end-to-end test we needed the check to be done on its own (with no user necessary).
It took the addition of specialized Matchers and separate adapter handling to read from a view into an ArrayList so we could check that after sorting it separately (either by price or alphabetically), the result was the same as what was shown on the screen. 

Velocity/teamwork
=================

*Did your estimates get better or worse through the course? Show some
evidence of the estimates/actuals from tasks.*

Our estimates got better over each iteration. Part of this was probably because we initially assumed that every task would take weeks, but as we got more used to Android Studio and app development, our turnaround time for features and testing got faster, and we had a better idea of how much time each task would actually take. We can see this in our [velocity chart](velocity_chart_final.png), as our initial estimates included much more time than we actually needed, and by the last iteration were on average within just a few days of time spent.