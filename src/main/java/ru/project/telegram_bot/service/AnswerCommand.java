package ru.project.telegram_bot.service;

public class AnswerCommand {


    public static String array_numbers() {
        String response = """
                        int [] array = {1,2,3,4,5,6,7,8,9,10};
                        """.trim();
        return response;

    }

    public static String menu() {
        String commands =
                """
                        ----arrays----
                        /array_number
                        /array_names
                        
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
