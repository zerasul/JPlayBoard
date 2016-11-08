# JPlayBoard
[![Build Status](https://travis-ci.org/zerasul/JPlayBoard.svg?branch=master)](https://travis-ci.org/zerasul/JPlayBoard)

Java NanoPlayBoard Communication Library.

This is de Java Communication Library, for The [NanoPlayBoard Project](http://nanoplayboard.org); With this library,
you can use the NanoPlayBoard with java. Only you need to connect the board via USB, and use the Library.

This library, is tested in Windows, Mac and Linux also with Raspberry Pi ARM Linux Distributions.

## Getting Started

This Library, needs one Skecth to be upload to the board. You need the Arduino Software to upload to the NanoPlayboard.
Get The Arduino Software from the [Arduino Site](https://arduino.cc); and upload the .ino skecth that you can found in
the ino Folder.

For connect to the NanoPLayboard needs to instance a ```JPlayBoard``` Instance.

```java
JPlayBoard jplayboard=new JPlayBoard("/dev/ATMC02");
```

## Use of onChangeListener

The NanoPlayBoard, sends the sensors values to the library peridocadly, for get the values you can use an Listener;
Use the ```StateChangeListener``` for get all the values, and set it to the jplayboardListener.

You can create a class that implements the interface, or use an Anonimous class, and set the Listener to the Jplayboard instance.

```
jplayboard.setOnStateChangeListener(listener);
```

## Reference

This is the jplayboard API Reference.

* ```disconnect()```

Close The communication With The NanoPlayBoard

* ```setOnStateChangeListener(StateChangeListener)```

Set a StateChangeListener to the current instance.

* ```setRgbColor(r,g,b)```

Set the color for the RGB Led from the NanoPlayBoard. Set The R,G and B(Red, Green and Blue) values for change the color(0 to 255 range).

* ```playTone(value)```

Change the tone for the Buzzer.

* ```stopTone()```

Stop the tone for the Buzzer.

* ```setMatrix(character)```

Set a character for the Dots Matrix.

**NOTE:** This is the Alpha version and is not in stable mode.