import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class quizProgram {
    public static void main(String[] args) throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose an option");
        System.out.println("\nOption 1: Add Quiz\n");
        System.out.println("Option 2: Start Quiz");

        System.out.println("Please select the option : ");

        int Option = sc.nextInt();

        switch (Option){
            case 1: addQuiz();
                break;
            case 2: startQuiz();
                break;

        }
    }
    public static void addQuiz() throws IOException, ParseException {

        char ch = 'y';


        String fileName = "./src/main/resources/Questions.json";


        Scanner input = new Scanner(System.in);

        do {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));
            JSONObject addQuiz=new JSONObject();
            JSONArray jsonArray = (JSONArray) obj;

            System.out.println("\nPlease add a question here:");
            addQuiz.put("Question: ", input.nextLine());

            System.out.println("\nPlease enter the options:");

            System.out.println("Option a: ");
            addQuiz.put("Option a", input.nextLine());

            System.out.println("Option b: ");
            addQuiz.put("Option b", input.nextLine());

            System.out.println("Option c: ");
            addQuiz.put("Option c", input.nextLine());

            System.out.println("Option d: ");
            addQuiz.put("Option d", input.nextLine());


            System.out.println("Please give the correct answer: ");
            addQuiz.put("Correct Ans ", input.nextLine());


            jsonArray.add(addQuiz);

            FileWriter file = new FileWriter(fileName);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();



            System.out.println("\nYour Quiz is saved. Do you want to add more questions? (y/n)");
            ch = input.nextLine().charAt(0);
        }

        while (ch != 'n');
        input.close();

    }

    public static void startQuiz() throws IOException, ParseException {

        System.out.println("\nYou will be asked 5 questions. Are You Ready? (y/n)");
        int count = 0;
        Scanner input = new Scanner(System.in);
        char ready = input.nextLine().charAt(0);
        Random ran = new Random();


            for (int i = 1; i <= 5; i++) {

                int queNo = ran.nextInt(25);


                String fileName = "./src/main/resources/Questions.json";

                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(new FileReader(fileName));

                JSONArray jsonArray = (JSONArray) obj;

                JSONObject jsonObject = (JSONObject) jsonArray.get(queNo);

                System.out.println("\nQuestion - " + i + ": " + (String) jsonObject.get("Question: "));

                String option1 = (String) jsonObject.get("Option a");
                System.out.println("Option a: " + option1);

                String option2 = (String) jsonObject.get("Option b");
                System.out.println("Option b: " + option2);

                String option3 = (String) jsonObject.get("Option c");
                System.out.println("Option c: " + option3);

                String option4 = (String) jsonObject.get("Option d");
                System.out.println("Option d: " + option4);


                System.out.println("\nPlease put your answer");

                String userAns = input.nextLine();
                String realAns = (String) jsonObject.get("Correct Ans ");

                if (userAns.equals(realAns)) {
                    System.out.println("\nYour answer is correct ");
                    count++;
                } else {
                    System.out.println("\nYour answer is incorrect ");
                }

            }
            System.out.println("\nYou Got " + count + " marks out of 5.");



    }

}
