Ohmer Zoo
---

<center>
    <img src="https://avatars0.githubusercontent.com/u/1446536?v=3&s=80" alt="Kotlin" valign="10">
    <img src="https://avatars0.githubusercontent.com/u/12171519?v=3&s=100" alt="Strongback">
    <img src="https://avatars1.githubusercontent.com/u/124156?v=3&s=100" alt="Gradle">
    <img src="http://junit.org/junit5/assets/img/junit5-logo.png" alt="Junit 4" height="80" valign="10">
    <img src="https://avatars3.githubusercontent.com/u/2054056?v=3&s=100" alt="Mockito">
    <img src="https://avatars1.githubusercontent.com/u/10563009?v=3&s=100" alt="GradleRIO">
</center>

> A *laissez-faire* ("let them do") FRC robot created to explore and  evaluate several libraries and practices by playing with them.

This robot is a rewrite of [Ohmer 2016](https://github.com/teamresistance/ohmer-2016), our robot for the 2016 Stronghold game.

We looked back at our code and—naturally—found parts we didn't like. Some of these problems (though not all) could have been avoided had we been stricter with our code quality. The new code takes a radical step in the other direction, for fun and *learning*.

**A note of warning:** Parts of the code are intentionally (or maybe unintentionally) overkill. This project tries to find the fine balance between power and ease-of-use, which entails constantly pushing the line in hopes of reaching some "Whoa dude, worth" moment and then writing about it. On a real robot, it is much wiser to abide by the KISS principle: [Keep it simple stupid](https://en.wikipedia.org/wiki/KISS_principle).

---

### Libraries

##### FRC

* **[Strongback](https://github.com/strongback/strongback-java)** for a more fluent and intuitive WPILib :heart:
* **[GradleRIO](https://github.com/Open-RIO/GradleRIO/)** for deploying robot code through Gradle :ok_hand:

##### Java
* **[Gradle](https://gradle.org/)** for build and dependency management **blog post forthcoming*
* **[Dagger 2](https://google.github.io/dagger/)** for dependency injection **turned out to be absolutely unnecessary*
* **[Kotlin](https://kotlinlang.org/)** for a better Java **biggest commitment of this list, but worth considering*
* **[JUnit 5](http://junit.org/junit5/)** for unit testing **JUnit 4 may be safer due to ubiquity alone*
* **[Mockito](http://mockito.org/)** for mocking **easy to learn, but [tricky to properly use](https://8thlight.com/blog/uncle-bob/2014/05/10/WhenToMock.html)*