# SIT305 3.1C Quiz App

A simple Android quiz app for SIT305 Task 3.1C. The app asks the player for their name, presents a short multiple-choice quiz about Android fundamentals, and shows the final score at the end.

## Features

- Name entry screen before the quiz starts.
- Five multiple-choice Android development questions.
- Immediate answer feedback after each submission.
- Completed-question progress indicator throughout the quiz.
- Results screen with score summary and options to retake or finish.

## Project structure

- `MainActivity.kt` starts the quiz after validating the player's name.
- `QuizActivity.kt` displays questions, handles answer submission, and updates progress.
- `ResultsActivity.kt` shows the final score.
- `QuizRepository.kt` stores the quiz questions.

## Build

Open the project in Android Studio or run the Gradle wrapper from the repository root:

```bash
./gradlew assembleDebug
```
