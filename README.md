# **README**
<br>

[Link to vision statement](VISION.md)</br>
[Link to worksheet](documents/i3_worksheet.md)</br>
[Link to branching strategy](documents/branching_strategy.md)</br>
[Link to architecture](documents/architecture.md)


### **Group Members:** 
### Ming Tai Lin, Neal Guingcangco, Vasena Jayamanna, Sebastien Pichon
<br>

### **Project Name:** Price Tracker
<br>

### **Overview**
Price Tracker is an application designed to compare the price of products from different online retailers.
<br><br>

### **What is the Purpose of this Project?**
Our goal is to provide users with the ability to compare products across various websites using different metrics, where price comparison is the most important aspect. 
Users will be able to search for specific items by name, and retrieve information such as price, description, delivery details, and images of items. 
Effectively, we want to provide an application where users can obtain a clear view of the ways in which a product can differ when it is available on numerous websites.
<br><br>

### **How to run the app**
(1) Download the source code and open the project in Android Studio. Click *Build* > *Make Project* to build - note the database (sqlite) requirements as well as the necessary dependencies (which can be found in the *build.gradle* file in the *Gradle Scripts* directory). These should get set up on their own during the build.
</br>
</br>
(2) When the app starts running, select your region (if available) from the welcome screen buttons. This is to assess taxes, and will take you to the search bar page.
</br>
</br>
(3) Type a product name into the search bar to look for items. Click on the three dots in the top right (indicating *More Options*) to sort the results by increasing price or by product name.
</br>
</br>
(4) Click on a result to be taken to the product page, where you can see the price, price history, availability, and image item (if available) and a description of the product.
</br>
</br>
(5) You can purchase directly from the product item page by clicking "Buy Direct" or instead click "Add to Cart" and navigate back to the search bar page, then click on the cart icon in the top right to be taken to a checkout page, where all your items will be tallied and taxed.
</br>
</br>
(6) After purchasing, you can click on the clock icon at the top of the search page to see your order history.
