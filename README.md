
# RemitConnect

## Introduction
RemitConnect is a streamlined remittance application that facilitates international money transfers with an integrated currency converter for real-time currency exchange rates. This app leverages the power of Jetpack Compose to provide a modern, efficient user experience.

## Getting Started
### Prerequisites
- Android Studio
- Gradle Plugin 8.4
- Minimum SDK version: 21

### Building the App
To build the app, sync the project with Gradle files and run it through Android Studio.

## Architecture and Decisions
### Project Structure
The project follows a logical and modular structure:
- `api`: Contains `RemitConnectApiService` for network operations using Retrofit.
- `model`: Data models for network responses.
- `repository`: Manages data operations and network interactions.
- `ui`:
    - `components`: Custom UI elements.
    - `navigation`: Navigation graph for app routing.
    - `screens`: Different app screens.
    - `theme`: Theming options including colors and fonts.
- `utils`: Utility functions used across the app.
- `viewmodel`: Contains `SendMoneyToAfricaFlowViewModel` for managing UI state.

### Key Decisions
- **Architecture**: MVVM for separation of concerns and scalability.
- **User Experience**: Single ViewModel (`SendMoneyToAfricaFlowViewModel`) for the 'Send Money to Africa' flow, ensuring a cohesive user journey.
- **Network Response Handling**: `NetworkResult<T>` handles all network responses with three states: `Success`, `Error`, and `Loading`.

## Challenges Faced
### Technical Challenges
- **Concurrency in Network Calls**: Managing simultaneous network requests and ensuring thread safety was challenging. This was addressed by implementing coroutine flows and managing the lifecycle of these network calls.
- **Dynamic Currency Conversion**: Implementing real-time currency conversion required the combination of the currency rate and input amount as a single LiveData, so that any update in the currency rate from the server could be reflected in real-time on the UI.
- **Error Handling and Resilience**: Developing robust error handling mechanisms for various network scenarios, like server downtime  was critical. Custom error classes were established.
- **UI Responsiveness**: Ensuring the app remains responsive and efficient, particularly during complex data operations and screen transitions, was achieved by optimizing Jetpack Compose components and judicious use of state management in the ViewModel.

### Learning Experiences
- Gained in-depth understanding of Jetpack Compose, enhancing skills in building modern, reactive UIs for Android.
- Advanced knowledge in handling network operations and error management.

## Additional Notes
Your contributions and feedback are highly welcome!