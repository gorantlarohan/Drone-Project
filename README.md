# Drone-Project
Its a drone which acts as a travel guide by suggesting the places which a tourist wants nearby him.It also helps cop locating crime near him.
There is a Drone Class which is inherited by traveller and cop.
Functionalities of Drone as a Tourist Guide:
1)Take location of tourist,then ask him his places of interest-like restaurant,movies,tourist spots,adventure,coffee shops,etc.
2)Then based on his input give names of places,distance from his current place,landmarks,show all places within 3km radius and among them show top 3 best ones based on star rating.
3)Then if traveller sees a crime then he will call admin with parameters of image and location to admin ,then admin will call emergency cop(Emergency Cop  is inherited from  Main Cop) and Emergency cop should not appear on login page, 
Emergency cop is automatically called from admin and we display image and location in console from emergency cop.(using Jframe)
Functionalities of a drone in helping a cop:
1)When user logs in,we will be asking his city and zone,then using his location we will show images from database,based on segments of the city.
