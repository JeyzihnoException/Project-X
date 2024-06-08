## Социальная сеть 

### Описание

Это приложение представляет собой социальную сеть, построенную с использованием микросервисной архитектуры. Оно состоит из четырёх основных сервисов:

Платформа (platform-service): Каркас приложения, содержащий HTML-шаблоны страниц. Запрашивает данные у других сервисов (auth-service, message-service, social-service) и возвращает пользователю HTML с заполненными данными.
Сервис аутентификации (auth-service): Подключен к базе данных PostgreSQL. Отвечает за аутентификацию пользователей, регистрацию новых пользователей и управление учётными записями.
Сервис сообщений (message-service): Обрабатывает всё, что связано с диалогами, отправкой и получением сообщений между пользователями.
Сервис социальной сети (social-service): Реализует остальную функциональность социальной сети, такую как добавление/удаление друзей, вывод списка друзей и т.д.

### Технологии

Java 
Spring Boot
Thymeleaf
JavaScript
jQuery
Docker
Docker Compose
PostgreSQL

### Запуск локально

1. Предварительные требования:
   Запущенный Docker Engine:Убедитесь, что Docker Desktop (или Docker Engine) запущен и работает.
   Java 17 (или совместимая версия) 

2. Перейдите в директорию сервиса platform-service:
   
   cd platform-service
   

3. Запуск Docker Compose:
   
   docker-compose up -d
   

4. Доступ к приложению:
   Платформа:  http://localhost:8081 
* Если файл init.sql (в корневой директории сервиса platfrom) не исполнился при поднятии контейнера с БД, необходимо его запустить вручную.
### Структура проекта

social-network/
├── platform-service/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── example/
│   │                   └── platform/
│   │                       └── PlatformApplication.java
│   ├── dockerfiles/
│   │   └── AuthDockerfile
│   │   └── MessageDockerfile
│   │   └── SocialDockerfile
│   └── docker-compose.yml 
├── auth-service/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── example/
│   │                   └── auth/
│   │                       └── AuthApplication.java
│   └── target/
│       └── auth-service-0.0.1-SNAPSHOT.jar
├── message-service/
│   ├── ...
│   └── target/
│       └── message-service-0.0.1-SNAPSHOT.jar
├── social-service/
│   ├── ...
│   └── target/
│       └── social-service-0.0.1-SNAPSHOT.jar
