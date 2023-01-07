# Monopoly Application 

In this project a system is made to play the classic board game 'Monopoly'. 
In this game the objective is to get more money and influence on the board than the rest of the table until the point where the rest of the players go bankrupt, at which point they will stop playing, this will be done through the purchase and sale of properties in turns which will force the rest to pay the owner for falling into them.

## Vídeo para comprender el funcionamiento del juego
Para ver el vídeo haga click <a href="https://youtu.be/rW9G2acyIFU">aquí</a>

## Running petclinic locally
Monopoly is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:


```
git clone https://github.com/gii-is-DP1/dp1-2022-2023-l7-3.git
cd spring-monopoly
./mvnw package
java -jar target/*.jar
```

You can then access monopoly here: http://localhost:8080/

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

## In case you find a bug/suggested improvement for Spring Monopoly
Our issue tracker is available here: https://github.com/gii-is-DP1/dp1-2022-2023-l7-3/issues


## Database configuration

In its default configuration, Monopoly uses an in-memory database (H2) which
gets populated at startup with data. 

## Working with Monopoly in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * [VS Code](https://code.visualstudio.com)

### Steps:

1) On the command line
```
https://github.com/gii-is-DP1/dp1-2022-2023-l7-3.git
```
2) Inside Eclipse or STS
```
File -> Import -> Maven -> Existing Maven project
```

Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to generate the css. Run the application main method by right clicking on it and choosing `Run As -> Java Application`.

3) Inside IntelliJ IDEA

In the main menu, choose `File -> Open` and select the Monopoly [pom.xml](pom.xml). Click on the `Open` button.

CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources`
or right click on the `spring-monopoly` project then `Maven -> Generates sources and Update Folders`.

A run configuration named `MonopolyApplication` should have been created for you if you're using a recent Ultimate
version. Otherwise, run the application by right clicking on the `MonopolyApplication` main class and choosing
`Run 'MonopolyApplication'`.

4) Navigate to Petclinic

Visit [http://localhost:8080](http://localhost:8080) in your browser.


## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [MonopolyApplication](https://github.com/gii-is-DP1/dp1-2022-2023-l7-3/blob/master/src/main/java/org/springframework/monopoly/MonopolyApplication.java) |
|Properties Files | [application.properties](https://github.com/gii-is-DP1/dp1-2022-2023-l7-3/blob/master/src/main/resources) |
|Caching | [CacheConfiguration](https://github.com/gii-is-DP1/dp1-2022-2023-l7-3/blob/master/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java) |

# Contributing

The [issue tracker](https://github.com/gii-is-DP1/dp1-2022-2023-l7-3/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <https://editorconfig.org>. If you have not previously done so, please fill out and submit the [Contributor License Agreement](https://cla.pivotal.io/sign/spring).

# License

The Spring Monopoly sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
