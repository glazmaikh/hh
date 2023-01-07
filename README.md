## hh.ru
#### Test for Advanced Search Page: https://hh.ru/search/vacancy/advanced 
#### Reposiotry: https://github.com/glazmaikh/hh

#### Список технологий:
Java Gradle IntelliJ IDEA Selenide Selenoid JUnit5 Jenkins Allure Report Telegram

![This is an image](/design/Java.png)![This is an image](/design/Gradle.png)![This is an image](/design/Intelij_IDEA.png)![This is an image](/design/Selenide.png)![This is an image](/design/Selenoid.png)![This is an image](/design/JUnit5.png)![This is an image](/design/Jenkins.png)![This is an image](/design/Allure_Report.png)![This is an image](/design/Telegram.png)


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
#### Локальный запуск тестов
```
gradle clean advanced_search
```
#### Удаленный запуск 
```
clean advanced_search_tests
-Dremote=${REMOTE}
-Dbase_url=${BASE_URL}
-Dbrowser=${BROWSER}
-Dbrowser_ver=${BROWSER_VERSION}
-Dscreen_resolution=${SCREEN_RESOLUTION}
-Dvideo_url=${VIDEO_URL}"
```
### Параметры сборки
<code>REMOTE</code> – адрес удаленного сервера, на котором будут запускаться тесты. </br>
<code>BASE_URL</code> – ссылка сайта по умолчанию. </br>
<code>TASK</code> – выбор набора тестов по тегу. </br>
<code>BROWSER</code> – браузера, на котором будут выполняться тесты. </br>
<code>BROWSER_VERSION</code> – версия браузера, на которой будут выполняться тесты. </br>
<code>SCREEN_RESOLUTION</code> – размер окна браузера, на котором будут выполняться тесты. </br>
<code>VIDEO_URL</code> – путь хранения видео результатов тестов. </br>
## Подключение Allure:
#### build.gradle
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
#### jenkins
![hh_allure](https://user-images.githubusercontent.com/5861141/211138908-87df07cc-e649-43fe-8ad3-2297cb0101f3.jpg)

## Подключение нотификаций о результатах тестов в телеграм:
#### В структуре проекта
