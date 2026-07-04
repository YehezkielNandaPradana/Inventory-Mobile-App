# NewInventaris2

A modern Android inventory management application built with Jetpack Compose and Material Design 3. This application allows users to manage items, track borrowing history, and maintain inventory levels with a clean, intuitive interface.

## Features

- **Item Management**: Add, view, and manage inventory items with categories
- **Borrowing System**: Borrow and return items with email validation
- **History Tracking**: View borrowing history with status filtering (Borrowed/Returned)
- **Search & Filter**: Search items by name and filter by category
- **Responsive Design**: Built with Jetpack Compose for modern Android UI
- **Material Design 3**: Clean, modern interface following Material Design guidelines
- **REST API Integration**: Communicates with backend service for data persistence

## Screens

### Beranda Screen (Home)
- Displays inventory items in a scrollable list
- Search functionality to find items by name
- Category filtering chips
- Floating action button to add new items
- Item cards showing name, category, and availability
- Borrow functionality with email validation

### Riwayat Screen (History)
- Displays borrowing history
- Filter by status (Borrowed/Returned)
- Item cards showing borrow/return dates and status
- Return functionality for borrowed items

## Architecture

The application follows a MVVM (Model-View-ViewModel) architecture:

- **UI Layer**: Jetpack Compose screens and components
- **ViewModel Layer**: Handles business logic and state management
- **Data Layer**: Repository pattern with Retrofit for API communication
- **Model Layer**: Data classes representing entities

### Key Components

- **Navigation**: Compose Navigation for screen transitions
- **State Management**: ViewModels with StateFlow and LiveData
- **Networking**: Retrofit for REST API calls
- **Dependency Injection**: Manual ViewModel creation with `viewModel()`
- **UI Components**: Custom reusable components (cards, buttons, text fields, etc.)

## Technology Stack

- **Minimum SDK**: 27
- **Target SDK**: 36
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Networking**: Retrofit 2 + Gson
- **Coroutines**: Kotlin Coroutines for async operations
- **Design**: Material Design 3
- **Build System**: Gradle KTS

## Dependencies

### Core Dependencies
- AndroidX Core KTX
- AndroidX Lifecycle Runtime KTX
- AndroidX Activity Compose
- Jetpack Compose BOM (2024.09.00)
- Jetpack Compose UI
- Jetpack Compose Material3
- Jetpack Compose Navigation

### Networking
- Retrofit 2
- Gson Converter

### Utilities
- Kotlin Coroutines Android
- Coil (for image loading)
- Datastore Preferences

## Setup Instructions

### Prerequisites
- Android Studio Flamingo or later
- JDK 11
- Android SDK 36
- Emulator or physical device (API 27+)

### Installation
1. Clone the repository
2. Open the project in Android Studio
3. Wait for Gradle sync to complete
4. Ensure your backend API is running at `http://10.0.2.2:5248/`
5. Run the application on an emulator or device

### Configuration
The API endpoint is configured in `ApiClient.kt`:
```kotlin
baseUrl("http://10.0.2.2:5248/")
```
Update this URL if your backend runs on a different address.

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── io.github.ComposeKit2/     # Shared components and utilities
│   │   │   │   ├── Api/                   # API client and service definitions
│   │   │   │   ├── Component/             # Reusable UI components
│   │   │   │   ├── Helper/                # Utility helpers and extensions
│   │   │   │   ├── Model/                 # Data models
│   │   │   │   ├── Navigate/              # Navigation configuration
│   │   │   │   ├── ViewModel/             # ViewModels for state management
│   │   │   │   └── ViewModel/ResourceState/ # Resource state handling
│   │   │   └── io.github.naxx.newinventaris2/ # Main application package
│   │   │       ├── MainActivity.kt        # Entry point
│   │   │       └── ui/                    # UI screens and theme
│   │   │           ├── Activities/        # Screen implementations
│   │   │           │   ├── BerandaScreen/ # Home screen
│   │   │           │   └── RiwayatScreen/ # History screen
│   │   │           └── theme/             # Material Design theme
│   │   └── res/                           # Resources
│   └── AndroidManifest.xml
├── build.gradle.kts                       # App-level build configuration
└── proguard-rules.pro
├── build.gradle.kts                       # Root build configuration
├── gradle.properties
├── settings.gradle.kts
└── gradle/libs.versions.toml              # Dependency versions
```

## API Endpoints

The application expects the following REST API endpoints:

- `GET /api/Item` - Retrieve all items
- `GET /api/Categories` - Retrieve all categories
- `POST /api/borrowings/borrow` - Borrow an item
- `POST /api/Item/AddItem` - Add a new item
- `GET /api/borrowings` - Retrieve borrowing history
- `PUT /api/borrowings/return/{id}` - Return a borrowed item

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Jetpack Compose team for the modern UI toolkit
- Material Design team for the design system
- Retrofit and Gson teams for networking solutions
- Kotlin coroutines team for async programming support
