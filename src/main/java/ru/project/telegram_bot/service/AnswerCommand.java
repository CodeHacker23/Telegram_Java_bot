package ru.project.telegram_bot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
@AllArgsConstructor
public class AnswerCommand {
   private RandomService randomService;

    public String array_numbers() {
        int size = randomService.getNumber(10,20);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("int [] arrayNumber = {");
        for (int i = 0; i <= size; i++) {
            if (i < size){
                stringBuilder.append(randomService.getNumber(1,5000)).append(",");
            }else {
                stringBuilder.append(randomService.getNumber(1,5000)).append("};");
            }
        }
        return stringBuilder.toString();

    }

    public String array_names() {
        StringBuilder stringBuilder = new StringBuilder(); // for build string
        String[] names = {"Алексей", "Иван", "Дмитрий", "Егор", "Сергей", "Андрей", "Максим", "Владимир", "Николай", "Павел",
                "Олег", "Виктор", "Юрий", "Станислав", "Артур", "Григорий", "Роман", "Тимур", "Константин", "Василий",
                "Федор", "Анатолий", "Ярослав", "Валентин", "Борис", "Савелий", "Геннадий", "Михаил", "Леонид", "Георгий",
                "Анна", "Екатерина", "Мария", "Ольга", "Татьяна", "Наталья", "Алиса", "Дарья", "Виктория", "София",
                "Арина", "Ксения", "Юлия", "Вероника", "Полина", "Елена", "Людмила", "Надежда", "Злата", "Алёна"
        };

        int size = randomService.getNumber(7, names.length);
        stringBuilder.append("String [] arrayNames = {");

        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int indexRandom = 0;

            while (true) {
              indexRandom = randomService.getNumber(0, names.length - 1);
                if (indexList.contains(indexRandom) == false) {
                    indexList.add(indexRandom);
                    break;
                }
            }
            stringBuilder.append( names[indexRandom]);
            if (i == size-1) stringBuilder.append("};");
            else stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    public String getClassCity(){
        return """
                import lombok.AllArgsConstructor;
                import lombok.Data;
                
                @Data
                @AllArgsConstructor
                public class City {
                    private Long id;
                    private String name;
                    private long population;
                    private String timeZone;
                }
                """;
    }

    public String array_city(){
        return """
        City[] cities = new City[] {
                new City(1L, "Москва", 12000000, "UTC+3"),
                new City(2L, "Санкт-Петербург", 5400000, "UTC+3"),
                new City(3L, "Новосибирск", 1600000, "UTC+7"),
                new City(4L, "Екатеринбург", 1500000, "UTC+5"),
                new City(5L, "Казань", 1250000, "UTC+3"),
                new City(6L, "Нижний Новгород", 1250000, "UTC+3"),
                new City(7L, "Челябинск", 1200000, "UTC+5"),
                new City(8L, "Омск", 1170000, "UTC+6"),
                new City(9L, "Самара", 1150000, "UTC+4"),
                new City(10L, "Ростов-на-Дону", 1100000, "UTC+3")
        }
        """.trim();
    }

    public String list_city(){
        return """
                 List<City> listCity = new ArrayList<>();
                        listCity.add(new City(1L, "Москва", 12000000, "UTC+3"));
                        listCity.add(new City(2L, "Санкт-Петербург", 5400000, "UTC+3"));
                        listCity.add(new City(3L, "Новосибирск", 1600000, "UTC+7"));
                        listCity.add(new City(4L, "Екатеринбург", 1500000, "UTC+5"));
                        listCity.add(new City(5L, "Казань", 1250000, "UTC+3"));
                        listCity.add(new City(6L, "Нижний Новгород", 1250000, "UTC+3"));
                        listCity.add(new City(7L, "Челябинск", 1200000, "UTC+5"));
                        listCity.add(new City(8L, "Омск", 1170000, "UTC+6"));
                        listCity.add(new City(9L, "Самара", 1150000, "UTC+4"));
                        listCity.add(new City(10L, "Ростов-на-Дону", 1100000, "UTC+3"));
               
                """.trim();
    }

    public String list_nums(){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(10,30); i++) {
            list.add(random.nextInt(1,1000));
        }
        String answer = "List<Integer> list = Arrays.asList " +  list.toString()
                .replaceAll("\\[","(").replaceAll("]",")") + ";";

        return answer;
    }



    public String menu() {
        String commands =
                """
                        ----arrays----
                        /array_number
                        /array_names
                        /array_city
                        
                        ----list----
                        /list_nums
                        /list_names
                        /list_city
                        /list_users
                        /list_student
                        
                        ----clases----
                        /class_city
                        /class_user
                        /class_student
                        
                        """.trim();

        return commands;
    }
}
