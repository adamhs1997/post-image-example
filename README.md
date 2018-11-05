# post-image-example
Small example showing how to post image from Android app and recieve the data on a Flask server.

## About
This app is a simple prototype for sending an image via POST request from an Android app to a Flask server. Upon opening the app, it will automatically post the image you specify in the MainActivity.java file. It will also send some text over to the Flask server. Upon reciveing your post, the Flask server will (1) save the image file you post to the local machine and (2) return any text data you sent back to the Android app. The Android app simply shows any returned data, in raw JSON, as a Toast.

## Usage
To run this, you will need the HTTP Components libraries. Download these here: https://hc.apache.org/downloads.cgi. I tested this using version 5 beta 2. Simply download the zip file and place all the jars into your Android Studio project (in the app->libs directory). 

The file svr.py in the root of the project is the Flask server. It is configured to expose the API to any device on your home network. You will need to install Flask (via pip) to make this work. I used a virtualenv, so that should work fine.

In MainActivitiy.java, make note of the Todos. You will want to edit the IP address to be the IP of the computer you are running the Flask server on. The file path can be any file on your phone. It should support either PNG or JPEG images, but if you use anything that's not a PNG, you will have to edit the svr.py file to write your data out as that file type.
