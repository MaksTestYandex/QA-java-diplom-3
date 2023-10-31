# QA-java-diplom-3
В проекте тестируется программа, которая помогает заказать бургер в Stellar Burgers.
В данной части произведено покртытие ее UI-тестами.

## Использованные технологии и зависимости
Проект выполнен с использованием Java 11, Junit 4.13.2, rest-assured 5.3.1, gson 2.10.1, 
allure-junit4 и allure-rest-assured 2.24.0, webdrivermanager 5.3.1, selenium-java и selenium-http-jdk-client 4.9.1

## Список тестов
* RegistrationWebTest
* LoginWebTest
* AccountProfileWebTest
* ConstructorWebTest

## Запуск тестов
Для запуска тестов необходимо запустить команду
```shell
mvn clean test
```

## Генерация отчета о тестировании
Для генерации отчета необходдимо запустить команду в терминале находясь в папке проекта
```shell
allure serve target/surefire-reports/
```
Откроется окно браузера с отчётом.