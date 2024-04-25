# SMA Game - A JavaFX Adventure

SMA Game is an engaging JavaFX-based desktop adventure game that runs on Windows, macOS, and Linux. It utilizes Maven for dependency management and build processes. The game features various levels of play, incorporating dynamic and static transportation means, obstacles, and power-ups in a rich graphical environment.

## Prerequisites

Before you begin, ensure you have the following installed:
- Java JDK 11 or later
- Maven 3.6.0 or later

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Cloning the Repository

To get started, clone the repository to your local machine:

```bash
git clone https://github.com/BharatiyaJavaParty/sma_game
cd sma_game
```

### Building the Project

Navigate to the project directory and use Maven to compile and build the project:

```bash
cd sma_game
mvn clean install
```

This command will download all dependencies, compile the project, and create an executable jar file in the `target` directory.

### Running the Game

After building the project, you can run the game using:

```bash
java -jar target/sma_game-1.0-SNAPSHOT.jar
```

Replace `sma_game-1.0.jar` with the actual name of the jar file generated in the `target` directory.

## Features

- Multiple transport types including bikes, buses, and Luas.
- Engaging obstacles and gems collection.
- Rich graphical interfaces with custom audio and image assets.

## Game Controls

- **Arrow keys**: Move the player around the map.
- **Space**: Interact with transports and collect gems.

## Development

### Adding New Levels

To add new levels, create a new level class in `src/main/java/bjp/constants` and define the level layout and dynamics.

### Extending Functionality

- **Controllers**: Extend game functionalities by modifying controllers in `src/main/java/bjp/controller`.
- **Utilities**: Add new utilities or modify existing ones in `src/main/java/bjp/utility` for enhanced gameplay.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Your Name - [@your_twitter](https://twitter.com/your_twitter) - email@example.com

Project Link: [https://github.com/BharatiyaJavaParty/sma_game](https://github.com/yourusername/sma_game)

## Project Structure

Below is the structure of the project detailing the directories and files contained within:
```
sma_game
├── .idea
├── .vscode
├── src
│   └── main
│       ├── java
│       │   ├── bjp
│       │   │   ├── constants
│       │   │   │    ├── AppConstants.java
│       │   │   │    ├── 
│       │   │   │    └── Level1.java
│       │   │   ├── controller
│       │   │   │    ├── CityMapController.java
│       │   │   │    ├── GameOverController.java
│       │   │   │    ├── LaunchController.java
│       │   │   │    ├── PlayerNameController.java
│       │   │   │    ├── PopupController.java
│       │   │   │    └── ScoreBoardController.java
│       │   │   ├── utility
│       │   │   │    ├── Bike.java
│       │   │   │    ├── Bus.java
│       │   │   │    ├── Luas.java
│       │   │   │    ├── StaticTransport.java
│       │   │   │    ├── StaticTransportConfig.java
│       │   │   │    ├── Player.java
│       │   │   │    ├── Gem.java
│       │   │   │    ├── Obstacles.java
│       │   │   │    ├── SoundEffects.java
│       │   │   │    ├── Location.java
│       │   │   │    ├── DynamicTransport.java
│       │   │   │    └── GameEngine.java
│       │   │   ├── Main.java
│       │   │   └── MainWithoutApplication.java
│       │   └── module-info.java
│       └── resources
│           ├── audio // contains audio files
│           ├── bip // contains fxml files
│           ├── img // contains image files
│           └── styles // contains css files
├── target
├── .gitignore
├── Notes.txt
├── pom.xml
└── Score.json
```