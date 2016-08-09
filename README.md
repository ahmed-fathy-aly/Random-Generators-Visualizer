About
--------
A visualizer for different random functions. Could be used to explain different random functions.


Screen Shot
-----------------
<a href="http://i.imgur.com/OMcBXff.png"><img src="http://i.imgur.com/OMcBXff.png" title="source: imgur.com" height= "500" /></a>

Features
------------
- Dynamic Histogram custom view
- Interface to change the scale of the drawn histogram
- Interface to choose the number of generated values and the number of histogram periods
- Interface to select one of 5 distributions and change their parameters (Uniform – Normal - Exponential
– Weibull – Geometric)

How to Use
----------------
- Enter the number of period in the “Periods” spinner
- Enter the number of generated values in the “Values” spinner
- Click on one of the distributions button
- You’ll see the histogram drawn on the right (The figure has a normal distribution drawn)
- To Change the scale of the histogram uncheck the “Dynamic Scale” Check Box and enter the x range
and y’s max value in the spinners next to it.

How to build
------------------
- Import the project using eclipse
- Add the SyntheticaBlackEye look and feel to the project, or comment the following line in the class MainApplication

```
// set look and feel(comment this part if you don't want to use Synthetica)
UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
```
