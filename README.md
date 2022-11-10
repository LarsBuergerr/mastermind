![bannerImage](src/main/resources/mastermind_git_header.png)

---

![MAIN status](https://github.com/LarsBuergerr/mastermind/actions/workflows/codecov.yml/badge.svg)
[![codecov](https://codecov.io/gh/LarsBuergerr/mastermind/branch/07-DesignPattern/graph/badge.svg?token=PUIFJ9PH30)](https://codecov.io/gh/LarsBuergerr/mastermind/tree/develop)
![MAIN status](https://github.com/LarsBuergerr/mastermind/actions/workflows/coveralls.yml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/LarsBuergerr/mastermind/badge.svg?branch=07-DesignPattern)](https://coveralls.io/github/LarsBuergerr/mastermind?branch=07-DesignPattern)

![RepoSize](https://img.shields.io/github/repo-size/LarsBuergerr/mastermind)
[![License](https://img.shields.io/github/license/LarsBuergerr/mastermind?color=green)](https://cdn130.picsart.com/272563229032201.jpg?r1024x1024)
![Forks](https://img.shields.io/github/forks/LarsBuergerr/mastermind?color=green&style=social)
![Watcher](https://img.shields.io/github/watchers/LarsBuergerr/mastermind?style=social)
[![Reddit](https://img.shields.io/reddit/subreddit-subscribers/ich_iel?color=green&style=social)](https://www.reddit.com/r/ich_iel/comments/aje7qh/ich_iel/)

---

## Contributors
| [LarsBuergerr](https://github.com/LarsBuergerr)  |  [Smokey95](https://github.com/Smokey95) | 
|---|---|
| ![image](https://github-readme-streak-stats.herokuapp.com/?user=LarsBuergerr) | ![image](https://github-readme-streak-stats.herokuapp.com/?user=Smokey95)  |

---

<!--- BRANCH 07 ONLY --->
## 07-Design Pattern Info

The following pattern are implemented:
### Strategy:
The strategy pattern was used to select a gamemode according to userinput. See the implementation [here](https://github.com/LarsBuergerr/mastermind/blob/07-DesignPattern/src/main/scala/de/htwg/se/mastermind/util/GameMode.scala)

### Factory:
The factory pattern replaces the original enum declaration of the stones. To show the difference the game-stones are implemented using the pattern, the hint-stones are still implemented as enum. See the implementation [here](https://github.com/LarsBuergerr/mastermind/blob/07-DesignPattern/src/main/scala/de/htwg/se/mastermind/model/Stone.scala)

### State:
The state pattern was used to keep track over current game state. There are events defined to switch the state of a game instance and methods to check the current state. See the implementation of the states [here](https://github.com/LarsBuergerr/mastermind/blob/07-DesignPattern/src/main/scala/de/htwg/se/mastermind/model/State.scala). The Events are defined [here](https://github.com/LarsBuergerr/mastermind/blob/07-DesignPattern/src/main/scala/de/htwg/se/mastermind/util/Event.scala). To switch between states there is a handler (called request) implemented in game class [here](https://github.com/LarsBuergerr/mastermind/blob/07-DesignPattern/src/main/scala/de/htwg/se/mastermind/model/Game.scala)

### Chain of Responsibility:
The Cain-of-Responsibility is used so analyze users input. See the implementation [here](https://github.com/LarsBuergerr/mastermind/blob/07-DesignPattern/src/main/scala/de/htwg/se/mastermind/util/Request.scala) and in the game class

---

## Description

      ERROR 404 (DescriptionNotFound)