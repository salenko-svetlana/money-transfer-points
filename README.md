**Исходные требования:**
Сервис, реализующий функционал справочной системы для поиска и отображения списка пунктов, в которых можно получить/отправить денежный перевод в заданном городе.
Приложение состоит из двух частей:
1.	Backend
БД информации о географии сервиса (страны, города, банки, пункты). Структуру БД и отношения сущностей необходимо разработать самостоятельно.
RESTful-сервис реализующий API для доступа к информации в БД. API необходимо разработать самостоятельно.
2.	Web-приложение
Взаимодействует c backend и реализует web-интерфейс отзывчивого поиска стран и городов, в которых есть пункты банков и показывающее список пунктов выдачи/отправки переводов в указанном городе. Пункты необходимо группировать по принадлежности к банкам.


**Инструкция по сборке и запуску:**
0) Перед сборкой

В src\main\resources\application.yml установить актуальные значения для 
        url: jdbc:oracle:thin:@localhost:1521:xe	-	путь до БД Oracle 11 XE
        username: ss 					-	Логин пользователя для подключения к БД
        password: ss					-	Пароль пользователя для подключения к БД

Обратите внимание, что у данного пользователя должны быть права на присоединение и изменение схемы, т.е. роли CONNECT, RESOURCE

1) Запустить сборку проекта build.bat или mvn clean package **из корня проекта**
2) Запустить исполнение проекта start.bat или java -jar -Dspring.profiles.active=test target\money-transfer-point-0.1.0-SNAPSHOT.jar из корня проекта
3) Открыть в браузере url http://localhost:9009
4) Отобразиться основное окно приложения с перечнем банков

Далее см. инструкцию по использованию системы.

**ПРИМЕЧАНИЯ:**
В связи с нехвкаткой времени
1) Не реализованы юнит-тесты 
FIXED - 2) Не выделен слой сервисов,  которые содержат бизнес-логику сервиса и обрабатывают запросы от контроллеров
FIXED - 3) Не выделены DTO для общения с внешним миром. Данные DTO должны использоваться в контроллерах вместо непосредственно Entity в
качестве входных и выходных параметров.
FIXED - 4) Взаимодействие с Entity должно осуществляться только на уровне сервисного слоя
FIXED - 5) Мало javadoc-ов
6) Приложение сделано единым артефактом, не разбито на бэкенд и фронтенд для независимого их запуска и работы
7) Фронтенд должен общаться с бэкендом через REST вместо прямых вызовов  
   