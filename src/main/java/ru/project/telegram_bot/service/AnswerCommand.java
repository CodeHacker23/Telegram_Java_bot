package ru.project.telegram_bot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


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
                        /list_users
                        
                        ----clases----
                        /class_user
                        /class_auto
                        /class_student               
                        
                        """.trim();

        return commands;
    }
}
