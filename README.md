### Hexlet tests and linter status:
[![Actions Status](https://github.com/bjrunning/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/bjrunning/java-project-78/actions)
[![Actions Status](https://github.com/bjrunning/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/bjrunning/java-project-78/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/9e92548ed8cad348b70d/maintainability)](https://codeclimate.com/github/bjrunning/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/9e92548ed8cad348b70d/test_coverage)](https://codeclimate.com/github/bjrunning/java-project-78/test_coverage)

## Target

Data Validator is a project aimed at improving the design of architecture in an object-oriented style. Here you will need to apply almost everything you learned in OOP courses: designing class structure, object composition, possible inheritance and, of course, the fluent interface. You will have to think about global and local state, think about code extensibility without rewriting it, and adhere to SOLID principles.

## Description

Data validator is a library that can be used to check the correctness of any data. There are many similar libraries in every language, since almost all programs work with external data that needs to be checked for correctness. First of all, we are talking about data from forms filled out by users. The project is based on the [yup](https://github.com/jquense/yup) library.

## Usage example

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

public class Example {
    public static void main(String[] args) {

        Validator v = new Validator();

        // строки
        StringSchema schema = v.string().required();

        schema.isValid("what does the fox say"); // true
        schema.isValid(""); // false

        // числа
        NumberSchema schema = v.number().required().positive();

        schema.isValid(-10); // false
        schema.isValid(10); // true

        // объект Map с поддержкой проверки структуры
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().

                required());
        schemas.put("age", v.number().

                positive());

        MapSchema schema = v.map().sizeof(2).shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        schema.isValid(human1); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "");
        human2.put("age", null);
        schema.isValid(human1); // false
    }
}
```
## Domain Specific Languages ​​(DSLs)

The validation library interface is a prime example of a DSL, a specialized language that allows you to declaratively (descriptively) describe what you want from your code. Code written in this style is much easier to read than working with direct object creation. In many ways, this approach is based on the fluent interface pattern.

## Architecture

A key part of the internal architecture is the organization of validators. This problem can be solved in many different ways, but only a few of them provide a truly convenient and extensible structure without unnecessary complexity. When designing architecture, it is extremely easy to overdo it and make something very complex.

## Testing and Debugging

Automated tests are an integral part of professional development. Data validator is an ideal project for improving your testing skills. It is simple and convenient enough to write tests, and complex enough to understand the importance of these tests during refactoring and debugging. Unlike the Hexlet practice, here you have to write tests yourself. Moreover, this can be done before code, practicing TDD.

The [JUnit](https://junit.org/junit5/) framework is used to write tests