# Leap Motion Dimensions Visualiser
---

by Luke T. Hadley (06/2020)

This project interfaces with a Leap Motion Controller to create a JFrame window displaying the X/Y/Z positions of a hand reletive to the controller.

In order to use this program you must have setup a workspace in your IDE for Leap Motion Projects.
Use 'https://developer-archive.leapmotion.com/documentation/java/devguide/Project_Setup.html' as a reference if you need help.
The project comes as a set up InteliJ project with the API .jar file inside the ```/lib/``` directory, but you must also have the .jar and .dll files available for your ```java.libary.path``` location.

**Please note**: if you are planning on using this you must have the leap motion software installed/calibrated. 

The project was built around a Model 'LM-010' Leap Motion Controller, firmware revision '1.7.0', running software version '2.3.1+31549' on a Mac 'OS X 10.11.6' system.
Although these constraints in itself will not hinder anybody's usage of this visualisation program, you may have to configure your system with the software version and API version

### What the class does

The class takes an instance of a Leap Motion ```Controller``` object and displays a ```JFrame``` window displaying the reletive values of where the hand is in relation to the sensor.

![alt text](/WindowGIF.gif "Frame Window Example GIF")


***Note***: The program 'favors' a left hand (only because I am left handed), in future I will implement and change the program so that the values of two hands can be visualised.

example. if you are using your right hand, and the sensor then picks up your left, it will only display the values reletive to your left hand untill it leaves the frame.


### How it works/usage

The ```VisualiserFrame``` object has three public methods used to interact with it, all other methods are private and inside a wrapper class.

#### Object initilisation

VisualiserFrame takes an instance of a Leap Motion Controller, creates a
Frame and displays the X/Y/Z values the Controller has on a graph.

```java
public VisualiserFrame(Controller leapMotionController)
```

A ```VisualiserFrame``` object must be passed a ```com.leapmotion.leap.Controller``` object at initialisation.

example:
``` java
    public static void main(String[] args) {
        Controller controller = new Controller();
        VisualiserFrame vf = new VisualiserFrame(controller);
        vf.startWindow(); 
    }
}
```

#### Starting a window

Starts a VisualiserFrame window.
Adds a listener to the device which on every frame, will refresh the content of the window with the current values.

```java
public void startWindow()
```

#### Closing a window

Closes a VisualiserFrame window.
Removes controller listener and releases the object.

```java
public void closeWindow()
```

### To Do

* Modify implementation to allow for two hand objects to be visualised.
* Change the JComponent methods so that ```repaint()``` does not change and redraw everything on the frame each time, only items that should be changed are the text x/y/z values and the two dot items.
