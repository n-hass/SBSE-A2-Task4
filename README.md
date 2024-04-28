# About
Written by Nicholas Hassan (a1794075) for Task 4 of SBSE Assignment 2.

Other group members:

Yen-Han (Stephen) Chen, a1755874

Alex Hu, a1766749


# Running
- Assumes Java 17
- Assumes Gradle 8.7

Use the gradle wrapper in the root to run Part 1 of Task 4:
`./gradlew partI:run`

Use the gradle wrapper in the root to run Part 2 of Task 4:
`./gradlew partII:run`

These will generate results in the form of plots and txt files into `partI/results` and `partII/results` respectively.

## Setup
Uncomment line 19 of `partII/src/main/java/uoa/partII/Main.java` (`InitialSetup.setupFirstImage()`) if you want to re-initialise the original baseline image. This will launch the test application with the `base_color1.csv` colour profile and save a screenshot to `partII/original.png`.


Images may alternatively be generated in SVG format by altering the static variable in the Plot generation classes for each part, but this was flakey at time of testing due to internal MOEA errors and its recomended to just leave it as PNGs to prevent random exceptions being thrown while generating the plots.