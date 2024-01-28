# Yandex Api Геокодер

### Описание
Небольшой геокодер с использованием api яндекс карт.
Redis и rest api развёрнуты в докере, прописаны тесты, обработаны ошибки, результаты кэшируются с использованием редиски.

### Процесс получения данных
1) Поступает запрос на получение координат или адреса.
2) Отправляется запрос к api яндекса.
3) Получаемый Json парсится, в зависимости от найденных результатов по запросу, создаётся определённое количество адресов или координат.
4) Каждая сущность выводится в формате Json.

### Документация:
* /geo/api/getAddresses?coordinates=... - принимает координаты, возвращает найденные адреса в формате Json
* /geo/api/getCoordinates?address=... - принимает адрес, возвращает координаты найденного объекта в формате Json

### Возможные ошибки:
1. 409 CONFLICT - ошибка парсинга. Может возникнуть, если яндекс решит изменить структуру своего Json.
2. 404 NOT FOUND - яндекс апи не нашёл объектов по запросу. Можно обойтись и без этого, яндекс не бросает ошибок, если ничего не найдено, но это однозначно лучше пустых ответов.
3. 403 FORBIDDEN - ошибка яндекс апи. Причина будет указана в сообщении. Либо не валидный ключ, либо яндекс изменил структуру своего апи.

### Полный стек:
* java 21
* Spring boot
* Docker
* Redis
* Jackson
* lombok
* Тесты - RestAssured