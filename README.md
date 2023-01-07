## hh.ru 
#### Test for Advanced Search Page: https://hh.ru/search/vacancy/advanced 
#### Технологии и инструменты:
<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="/design/Intelij_IDEA.png" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="/design/Java.png" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="/design/GitHub-Mark.png" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="/design/JUnit5.png" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="/design/Gradle.png" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="/design/Selenide.png" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="/design/Selenoid.png" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="/design/Allure_Report.png" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="/design/Jenkins.png" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://telegram.org/"><img src="/design/Telegram.png" width="50" height="50"  alt="Telegram"/></a>
</p>


#### Gradle dependencies:
```
testImplementation (
            'org.junit.jupiter:junit-jupiter:5.9.1',
            'com.codeborne:selenide:6.11.0',
            'io.qameta.allure:allure-selenide:2.20.1',
            'org.slf4j:slf4j-simple:2.0.5'
    )
```
## Запуск тестов из терминала
#### Локальный запуск тестов:
```
gradle clean advanced_search
```
#### Удаленный запуск:
```
clean advanced_search_tests
-Dremote=${REMOTE}
-Dbase_url=${BASE_URL}
-Dbrowser=${BROWSER}
-Dbrowser_ver=${BROWSER_VERSION}
-Dscreen_resolution=${SCREEN_RESOLUTION}
-Dvideo_url=${VIDEO_URL}"
```
#### Параметры сборки:
<code>REMOTE</code> – адрес удаленного сервера, на котором будут запускаться тесты. </br>
<code>BASE_URL</code> – ссылка сайта по умолчанию. </br>
<code>TASK</code> – выбор набора тестов по тегу. </br>
<code>BROWSER</code> – браузера, на котором будут выполняться тесты. </br>
<code>BROWSER_VERSION</code> – версия браузера, на которой будут выполняться тесты. </br>
<code>SCREEN_RESOLUTION</code> – размер окна браузера, на котором будут выполняться тесты. </br>
<code>VIDEO_URL</code> – путь хранения видео результатов тестов. </br>
## Подключение Allure
#### build.gradle:
```
plugins {
    id "io.qameta.allure" version "2.11.2"
}
allure {
    report {
        version.set("2.20.1")
    }
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.20.1")
            }
        }
    }
}
```
#### jenkins:
- В разделе "Послесборочные операции" указать Path: build/allure-results

## Подключение нотификаций о результатах тестов в телеграм
#### В телеграм:
- создать бота (сохранить токен)
- добавить бота в нужный чат
- сделать бота админом
- получить chatId при помощи: https://api.telegram.org/bot{secret_bot}/getUpdates

#### В структуру проекта добавить:
[notifications/allure-notifications-4.2.1.jar](https://github.com/glazmaikh/hh/blob/master/notifications/allure-notifications-4.2.1.jar)
#### jenkins:
- В разделе "Сборка" добавить шаг сборки "Create/Update Text File"
- Указать File Path: notifications/telegram.json
- Проставить галки для Create at Workspace и Overwrite file
- Добавить settings.json:
```
{
  "base": {
    "project": "${JOB_BASE_NAME}",
    "environment": "some env",
    "comment": "@Glazmaikh",
    "reportLink": "${BUILD_URL}",
    "language": "en",
    "allureFolder": "allure-report/",
    "enableChart": true
  },
  "telegram": {
    "token": "5966641338:AAGtd9TIa_bCx75K2w0FZe2R6JRhQAIyg80",
    "chat": "-1001337521442",
    "replyTo": ""
  }
}
```
![hh_allure_report](https://user-images.githubusercontent.com/5861141/211144262-65726454-4f8c-425f-830e-5a80cc599195.jpg)
![hh_telegram_report](https://user-images.githubusercontent.com/5861141/211144264-306e63e7-5c2d-402d-9c5b-51dba746ba56.jpg)
