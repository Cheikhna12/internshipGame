# InternshipQuest – 2D Java Game with LibGDX

Embark on the adventure of a student searching for the perfect internship in a Java Sim-life game where interviews turn into epic duels.

## 📑 Table of Contents
- [Installation & Running](#installation--running)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Features](#features)
    - [World Exploration](#world-exploration)
    - [Interview System (Combat)](#interview-system-combat)
    - [Character & Progression](#character--progression)
    - [Graphics & Animations](#graphics--animations)
    - [Audio & Ambiance](#audio--ambiance)
    - [Technical Architecture](#technical-architecture)
- [Gameplay Details](#gameplay-details)
    - [Activities](#activities)
    - [Events](#events)
    - [Locations](#locations)
    - [Heroes](#heroes)
- [Project Structure](#project-structure)
- [Assets Overview](#assets-overview)
- [Roadmap](#roadmap)
- [Authors](#authors)
- [License](#license)

## Installation & Running

### Prerequisites
- Java 17 or higher
- Maven installed
- LibGDX configured in the project

### Installation
Clone the project and install dependencies:

```bash
git clone git@github.com:EpitechMscProPromo2028/T-JAV-501-REN_5.git
cd internshipquest
mvn clean install
mvn clean compile
mvn exec:java
```

## Features

### World Exploration
- Interactive world map with multiple visitable locations
- Dozens of possible actions through dedicated screens
- Events triggered based on player progression

### Interview System (Combat)
- Interviews presented as RPG-style battles
- Combat power defined by the player's statistics
- Recruiter reactions based on hero's actions
- Success or failure affects game progression
- Victory screen with scoring and dynamic transitions

### Character & Progression
- 3 playable heroes with distinct stats
- Management of stress, energy, and satiety through interactions
- Stats influence interview outcomes and game events

### Graphics & Animations
- Manga/anime-inspired style
- NPCs react dynamically when entering locations

### Audio & Ambiance
- Ambiance varies by location and time (sporty, corporate, night)
- Sound effects for supermarkets, food, radio, etc.
- Epic music for interviews and final boss encounters

### Technical Architecture
- Multi-screen architecture using LibGDX `Screen` interface
- Factory design pattern for `event`, `activity`, and `location` classes
- Centralized asset management for graphics and audio

## Gameplay Details

### Activities
Key activities available to the player:

| Activity                                            | Description                    |
|-----------------------------------------------------|--------------------------------|
| BuyFood / BuyFoodPromotion                          | Replenish food in fridge       |
| EatFood / DrinkAlcool                               | Manage energy and stress       |
| Study / PersonalProject /                           | Increase Coding Skill          |
| PushUps / DeadLifts                                 | gain Endurance                 |
| TalkToStranger / MeetUp / ChessClub                 | Social interactions            |
| SnackDispenserEpitech / SnackDispenserGym           | Pay for small stat boosts      |
| Wait                                                | Time-based activities          |
| ListenRadio / LookFridge / PayLicence / DisplayStat | Optional gameplay interactions |

### Events
Randomized events enrich gameplay:

| Event                                          | Description                                 |
|------------------------------------------------|---------------------------------------------|
| CatRobFood                                     | Encounter with your cat stealing food       |
| EnduranceDreamEvent / StressDreamEvent         | Dream sequences affecting stats             |
| MeetUpEvent / PromoEvent / PickpocketCityEvent | City interactions with rewards or penalties |

### Locations
Playable locations include:

| Location       | Description                     |
|----------------|---------------------------------|
| Bar            | Socialize, drink, reduce stress |
| CloverField    | Search for clovers, rest        |
| Epitech        | Study and go to meet up         |
| FitnessClub    | increase your endurance         |
| IndustrialZone | Search your internship          |
| Shop           | gain money and buy food         |
| Home           | Sleep, eat and see your cat     |
| Sorcerer       | try to gain luck                |

### Heroes
Three playable characters:

| Hero     | Strengths                       |
|----------|---------------------------------|
| Giovanni | High endurance, lower skills    |
| Helmüt   | High intelligence, lower energy |
| Zeldo    | boosted stat for demo           |

### Combat & Interviews
- Managed via `Entretien`, `Entreprise`, `RH`, and `QuestionBank`
- Correct actions/questions increase hiring chances
- higher salary increase difficulty


## Project Structure
```plaintext
src/main/java/com/internshipquest
├── graphics       # Rendering classes (maps, backgrounds)
├── model
│   ├── activity   # All activities
│   ├── combat     # Interview / combat system
│   ├── event      # Random events
│   ├── hero       # Hero classes
│   └── location   # Game world locations
├── screens        # LibGDX screens (world map, interviews, game over)
└── utils          # Constants, SoundManager, etc.
```

## Assets Overview
- **Images**: Hero sprites, NPCs, backgrounds, UI elements
- **Sounds**: Ambiance, music, effects for actions/events
- **Fonts**: DMSerifText and OFL license

## Roadmap
- Planned improvements:
    - Save/load functionality
    - More dynamic NPC interactions
    - Additional mini-games and events


## Authors
- **Thomas Plantevin** – [LinkedIn](https://www.linkedin.com/in/thomas-plantevin-9a47ba175)
- **Mathys Toux** – [LinkedIn](https://www.linkedin.com/in/mathys-toux-69b059234/)
- **Cheikhna mouhamedou Ould** – [LinkedIn](https://www.linkedin.com/in/cheikhna-ould-6a7689232/)

## License
Project developed as part of academic studies. Redistribution is prohibited without permission.