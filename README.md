## Процедура запуска автотестов
### Необходимое окружение
- **IntelliJ IDEA**
- **Java: OpenJDK 11**
- **Docker**
- **Google Chrome**
  
### MySQL
  1. _Клонировать репозиторий командой_ git clone https://github.com/ktshva/QA_Diplom.git
  2. _Открыть проект в_ **IntelliJ IDEA**
  3. _Запуск сервиса Docker Desktop
  4. _Запуск docker-контейнера:_ из директории с файлом ```docker-compose.yml``` запустить команду ```docker-compose up```
  5. _Запуск приложения командой:_ из директрии с файлом ```application.properties``` запустить команду ```java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app```
  6. _Проверить работу приложения в браузере:_ открыть URL http://localhost:8080/
  7. _Запуск тестов:_ в терминале ввести команду ```./gradlew clean test --info```
  8. _Формирование отчетов Allure:_ в терминале ввести команду ```./gradlew allureReport```
  9. _Просмотр отчета:_ из директории с сформированным отчетом ```\QA_Diplom\build\reports\allure-report\allureReport``` открыть в Google Chrome файл ```index.html```
  10. _Остановить контейнер:_ из директории с файлом ```docker-compose.yml``` запустить команду ```docker-compose down```

### PostgreSQL
  1. _Клонировать репозиторий командой_ git clone https://github.com/ktshva/QA_Diplom.git
  2. _Открыть проект в_ **IntelliJ IDEA**
  3. _Запуск сервиса_ Docker Desktop
  4. _Запуск docker-контейнера:_ из директории с файлом ```docker-compose.yml``` запустить команду ```docker-compose up```
  5. _Запуск приложения командой:_ из директрии с файлом ```application.properties``` запустить команду ```java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app```
  6. _Проверить работу приложения в браузере:_ открыть URL http://localhost:8080/
  7. _Запуск тестов:_ в терминале ввести команду ```./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app --info```
  8. _Формирование отчетов Allure:_ в терминале ввести команду ```./gradlew allureReport```
  9. _Просмотр отчета:_ из директории с сформированным отчетом ```\QA_Diplom\build\reports\allure-report\allureReport``` открыть в Google Chrome файл ```index.html```
  10. _Остановить контейнер:_ из директории с файлом ```docker-compose.yml``` запустить команду ```docker-compose down```
