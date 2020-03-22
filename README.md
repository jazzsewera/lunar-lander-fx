# Lunar Lander

## First stage

Project design brief and first stage details are specified in
[design_brief](design_brief.md) Markdown file.


## Description

A simple 2D game written in Java 11 and JavaFX 11 in which you
have to touch down on the Moon with low enough velocity and
angle to the ground until you run out of fuel.

A GUI mockup is shown in `gui.png`.


## Steering

Left/Right Arrows - control the rotation of the lander
Up Arrow - control the thrust of the lander's engine


## Usage

Project is build with [Gradle](https://gradle.org/). To build it:
```bash
./gradlew build
```

To run it:
```bash
./gradlew run
```


## Gradle integration

If more JavaFX modules are needed, add them to `build.gradle` file:

```groovy
javafx {
    version = '11.0.2'
    modules = [
        'javafx.controls',
        'javafx.add_module_here'
    ]
}
```


## IntelliJ IDEA integration

To connect Gradle Build System to IntelliJ, you have to add a module. To do this:

Go to `File > New > Module from Existing Sources...` and select `[project_folder]/build.gradle`.

You also don't have to use the terminal to execute Gradle tasks. You can open Gradle pane (on the right)
and go to `lunarlander > Tasks > [task_folder] > [task]`, for example `[application] > [run]`.


## Known bugs

On Arch Linux you cannot have JDK and JavaFX installed with pacman.
To fix this, download [JDK](https://jdk.java.net/java-se-ri/11)
and [JavaFX](https://gluonhq.com/products/javafx/), put them in a new
directory, e.g. `/home/<your_username>/java` and set the environment variables
as follows:

File with env variables, e.g. `/etc/environment`:
```
JAVA_HOME=/home/<your_username>/java/jdk-11
PATH_TO_FX=/home/<your_username>/java/javafx-sdk-11.0.2/lib
```
