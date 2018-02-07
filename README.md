## Test task
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

-----------------------------
P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.

### Краткое описание

В проекте используются: Java 8, Maven, Hibernate, Spring(Security, Data JPA, MVC), Jackson.

База данных HSQLDB.

С помощью Ehcache кешируются запросы на выборку:
* пользователей по email,
* ресторанов для пользователей и администраторов,
* ресторанов вместе с меню для голосования пользователей.

*Создание нового меню удаляет из кеша не все страницы ресторанов с меню, а только за определенную дату, с помощью CacheEvictionService*

Для запуска нужно установить Tomcat8 и Maven.

Проект упаковать командой

`mvn package`

Полученный mivoto.war развернуть с помощью Tomcat8

Ниже приведены примеры curl команд

##### vote for restaurant
`curl -i -d "id=100005" -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:8080/mivoto/rest/user/votes/restaurants --user user@yandex.ru:pass`

##### create new menu
`curl -i -X POST -H "Content-Type: application/json" -d '{"date": "2018-01-25","dishes": [{"price": 5.95,"description": "Tiramisu"},{"price": 14.75,"description": "British crab spaghetti"},{"price": 13.95,"description": "Oxtail Lasagne"},{"price": 12.95,"description": "Squash Cannelloni"},{"price": 11.95,"description": "The carbonara"}],"restaurantId": 100002}' http://localhost:8080/mivoto/rest/admin/menu --user admin@gmail.com:password`

##### update menu
`curl -i -X PUT -H "Content-Type: application/json" -d '{"id": 100015,"date": "2018-12-31","dishes": [{"price": 5.95,"description": "Tiramisu"},{"price": 14.75,"description": "British crab spaghetti"},{"price": 13.95,"description": "Oxtail Lasagne"}],"restaurantId": 100002}' http://localhost:8080/mivoto/rest/admin/menu/100015 --user admin@gmail.com:password`

##### delete menu
`curl -i -s -X DELETE "http://localhost:8080/mivoto/rest/admin/menu/100018" --user admin@gmail.com:password`

##### create new restaurant
`curl -i -X POST -H "Content-Type: application/json" -d '{"name": "Winil Wine Bar ","address": "Zubovskaya st., 5/36","phone": "+74992468438"}' http://localhost:8080/mivoto/rest/admin/restaurants --user admin@gmail.com:password`

##### update restaurant
`curl -i -X PUT -H "Content-Type: application/json" -d '{"id": 100005,"name": "Bosco Cafe","address": "Улица Пушкина, дом Колотушкина","phone": "+74956203182"}' http://localhost:8080/mivoto/rest/admin/restaurants/100005 --user admin@gmail.com:password`

##### delete restaurant
`curl -i -s -X DELETE "http://localhost:8080/mivoto/rest/admin/restaurants/100013" --user admin@gmail.com:password`

##### get menu by date and restaurantId
`curl -i -s "http://localhost:8080/mivoto/rest/admin/menu/by?date=2017-12-30&restaurantId=100003" --user admin@gmail.com:password`

##### get menu by id
`curl -i -s "http://localhost:8080/mivoto/rest/admin/menu/100015" --user admin@gmail.com:password`

##### get page with restaurants
`curl -i -s "http://localhost:8080/mivoto/rest/common/restaurants/by?page=0" --user admin@gmail.com:password`

`curl -i -s "http://localhost:8080/mivoto/rest/common/restaurants/by?page=0" --user user@yandex.ru:pass`

##### get page with restaurants and menu
`curl -i -s "http://localhost:8080/mivoto/rest/admin/restaurants/menu/by?page=0&date=2017-12-30" --user admin@gmail.com:password`

`curl -i -s "http://localhost:8080/mivoto/rest/admin/restaurants/menu/by?page=1&date=2017-12-30" --user admin@gmail.com:password`

##### get page with restaurants and menu for current date (if it exists)
`curl -i -s "http://localhost:8080/mivoto/rest/user/restaurants/menu/by?page=0" --user user@yandex.ru:pass`

`curl -i -s "http://localhost:8080/mivoto/rest/user/restaurants/menu/by?page=1" --user user@yandex.ru:pass`
