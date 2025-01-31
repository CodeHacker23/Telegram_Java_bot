package ru.project.telegram_bot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
