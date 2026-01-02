# CipherMaster Pro

Professional JavaFX encryption and decryption application supporting multiple classical cipher algorithms with bilingual support for Ukrainian and English alphabets.

## Features

### Supported Cipher Algorithms

#### Caesar Cipher
Classic shift-based substitution cipher with adjustable offset (1-32 positions). Each letter in the plaintext is replaced by a letter a fixed number of positions down the alphabet.

**Use Cases:**
- Educational cryptography demonstrations
- Simple text obfuscation
- Historical cipher analysis

#### Vigenere Cipher
Polyalphabetic substitution cipher using a keyword to determine different shift amounts for different positions in the text. Significantly more secure than Caesar cipher due to varying shift patterns.

**Use Cases:**
- Enhanced text encryption
- Multi-key cryptographic systems
- Complex pattern obfuscation

#### Atbash Cipher
Mirror/reflection cipher that reverses the alphabet (A↔Z, B↔Y, etc.). A simple yet elegant substitution cipher.

**Use Cases:**
- Quick text transformation
- Symmetric encryption/decryption
- Alphabet reversal operations

### Application Highlights

- **Bilingual Support**: Full support for both Ukrainian (АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ) and English (A-Z) alphabets
- **Modern UI**: Clean, intuitive interface with professional styling
- **Real-time Processing**: Instant encryption/decryption feedback
- **Flexible Configuration**: Adjustable parameters for each cipher type
- **Cross-platform**: Runs on Windows, macOS, and Linux

## Technology Stack

- **Java 22** - Latest Java LTS features and performance improvements
- **JavaFX 22.0.1** - Modern UI framework for rich desktop applications
- **Maven** - Dependency management and build automation
- **JUnit 5.10.2** - Unit testing framework

## Prerequisites

- JDK 22 or higher
- Maven 3.6+
- JavaFX SDK 22.0.1 (automatically managed by Maven)

## Installation & Running

### Clone the Repository

```bash
git clone https://github.com/sergiyscherbakov/cipher-master-pro.git
cd cipher-master-pro
```

### Build & Run with Maven

```bash
# Compile the project
mvn clean compile

# Run the application
mvn clean javafx:run

# Build executable package
mvn clean package
```

### Running from IDE

1. Import as Maven project
2. Ensure JDK 22 is configured
3. Run `CipherMaster.java` main class

## Usage Guide

### Encrypting Text

1. Enter your plaintext in the input area
2. Select desired cipher method from dropdown
3. Choose "Зашифрувати" (Encrypt) option
4. Configure cipher parameters if needed:
   - **Caesar**: Adjust shift slider (1-32)
   - **Vigenere**: Enter keyword
   - **Atbash**: No configuration needed
5. Click "ВИКОНАТИ" (Execute) button
6. View encrypted result in output area

### Decrypting Text

1. Enter encrypted text in the input area
2. Select the cipher method used for encryption
3. Choose "Дешифрувати" (Decrypt) option
4. Match the original encryption parameters
5. Click "ВИКОНАТИ" (Execute) button
6. View decrypted result in output area

## Project Structure

```
cipher-master-pro/
├── src/main/java/
│   ├── com/scherbakov/ciphermaster/
│   │   └── CipherMaster.java      # Main application class
│   └── module-info.java            # Java module descriptor
├── src/main/resources/             # Application resources
├── pom.xml                         # Maven configuration
├── .gitignore                      # Git ignore rules
└── README.md                       # This file
```

## Algorithm Details

### Caesar Cipher Implementation
- Maintains case sensitivity (uppercase/lowercase preserved)
- Non-alphabetic characters pass through unchanged
- Supports both positive (encrypt) and negative (decrypt) shifts
- Modular arithmetic ensures valid alphabet positions

### Vigenere Cipher Implementation
- Keyword-based polyalphabetic substitution
- Key repeats cyclically for texts longer than key
- Independent processing of Ukrainian and English characters
- Automatic key normalization to lowercase

### Atbash Cipher Implementation
- Simple alphabet reversal mapping
- First letter maps to last, second to second-last, etc.
- Symmetric operation (encrypt = decrypt)
- Separate handling for Ukrainian (33 letters) and English (26 letters) alphabets

## Security Notice

⚠️ **Educational Purpose Only**

These classical ciphers are **NOT suitable for actual security applications**. They are easily broken with modern cryptanalysis techniques and should only be used for:

- Educational demonstrations
- Understanding cryptographic concepts
- Historical cipher analysis
- Non-sensitive text obfuscation

For real-world security needs, use modern cryptographic standards like AES, RSA, or ChaCha20.

## Development

### Code Quality
- Clean, readable code structure
- Comprehensive inline documentation
- Separation of UI and cipher logic
- Modular method design

### Compilation Settings
- Source: Java 22
- Target: Java 22
- Encoding: UTF-8

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.

## License

This project is licensed under the MIT License - free to use and modify for educational purposes.

## Author

**Sergiy Scherbakov**
- GitHub: [@sergiyscherbakov](https://github.com/sergiyscherbakov)
- Email: sergiyscherbakov@ukr.net
- Repository: [cipher-master-pro](https://github.com/sergiyscherbakov/cipher-master-pro)

## 💳 Support This Project

If you find this project helpful, consider supporting its development:

### Donate USDT (Binance Smart Chain):

```
🔥 0xDFD0A23d2FEd7c1ab8A0F9A4a1F8386832B6f95A 🔥
```

Your support helps maintain and improve this project. Thank you!

## Acknowledgments

- Built with JavaFX for cross-platform desktop applications
- Implements classical cryptographic algorithms for educational purposes
- Ukrainian localization for enhanced accessibility
- Inspired by historical cryptography and information security education

---

*Professional cryptography education tool demonstrating classical cipher implementations with modern Java technologies*
