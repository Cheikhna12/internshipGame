package com.internshipquest.model.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBank {

    private static final Random random = new Random();
    private static final List<Question> allQuestions = new ArrayList<>();

    static {
        initializeQuestions();
    }

    private static void initializeQuestions() {

        allQuestions.add(new Question(
                "What is a variable in programming?",
                Question.QuestionType.TECH, 2,
                "Explain with examples of types (int, String, etc.)",
                "Use a simple and clear analogy"
        ));

        allQuestions.add(new Question(
                "What is the difference between '==' and '.equals()' in Java?",
                Question.QuestionType.TECH, 3,
                "Explain reference comparison vs content comparison",
                "Give concrete usage examples"
        ));

        allQuestions.add(new Question(
                "Name one advantage of object-oriented programming.",
                Question.QuestionType.TECH, 2,
                "Talk about encapsulation, inheritance, or polymorphism",
                "Explain with a real project example"
        ));

        allQuestions.add(new Question(
                "What is an algorithm?",
                Question.QuestionType.TECH, 1,
                "Give a precise technical definition",
                "Use a real-life metaphor"
        ));


        allQuestions.add(new Question(
                "Explain the concept of inheritance in OOP.",
                Question.QuestionType.TECH, 5,
                "Detail with examples of parent/child classes",
                "Present the benefits for teamwork"
        ));

        allQuestions.add(new Question(
                "What is a REST API?",
                Question.QuestionType.TECH, 5,
                "Explain HTTP, endpoints, and CRUD methods",
                "Describe how you used it in a project"
        ));

        allQuestions.add(new Question(
                "Describe the MVC pattern (Model-View-Controller).",
                Question.QuestionType.TECH, 6,
                "Explain separation of responsibilities",
                "Show how it facilitates collaborative work"
        ));

        allQuestions.add(new Question(
                "How do you handle errors in your code?",
                Question.QuestionType.MIXED, 4,
                "Talk about try-catch, custom exceptions",
                "Explain your debugging and testing process"
        ));


        allQuestions.add(new Question(
                "What is the difference between an abstract class and an interface?",
                Question.QuestionType.TECH, 7,
                "Detail the use cases and limitations of each",
                "Explain with examples of design patterns"
        ));

        allQuestions.add(new Question(
                "Explain what dependency injection is.",
                Question.QuestionType.TECH, 8,
                "Talk about loose coupling and testability",
                "Show how it improves maintainability"
        ));

        allQuestions.add(new Question(
                "How does the Java Garbage Collector work?",
                Question.QuestionType.TECH, 8,
                "Explain GC algorithms and memory management",
                "Describe the impact on application performance"
        ));

        allQuestions.add(new Question(
                "What is time complexity O(n)?",
                Question.QuestionType.TECH, 7,
                "Explain Big O notation with examples",
                "Show its importance for optimization"
        ));


        allQuestions.add(new Question(
                "Do you like working in a team?",
                Question.QuestionType.SOFTSKILL, 2,
                "Talk about agile methodologies and collaborative tools",
                "Share a positive team experience"
        ));

        allQuestions.add(new Question(
                "How do you organize yourself to meet a deadline?",
                Question.QuestionType.SOFTSKILL, 3,
                "Mention project management tools",
                "Explain your task prioritization method"
        ));

        allQuestions.add(new Question(
                "Are you curious about learning new technologies?",
                Question.QuestionType.SOFTSKILL, 2,
                "Mention recent technologies you learned",
                "Talk about your passion and motivation"
        ));

        allQuestions.add(new Question(
                "What do you know about our company?",
                Question.QuestionType.SOFTSKILL, 3,
                "Mention their tech stack",
                "Talk about their culture and values"
        ));


        allQuestions.add(new Question(
                "Describe a situation where you had to manage a conflict.",
                Question.QuestionType.SOFTSKILL, 5,
                "Explain how you found a technical solution",
                "Describe how you handled emotions and communication"
        ));

        allQuestions.add(new Question(
                "How do you react to constructive criticism?",
                Question.QuestionType.SOFTSKILL, 4,
                "Talk about code review and continuous improvement",
                "Show your open-mindedness and adaptability"
        ));

        allQuestions.add(new Question(
                "What is your greatest pride in a past project?",
                Question.QuestionType.SOFTSKILL, 5,
                "Detail the technical challenges you overcame",
                "Discuss the project's impact and team effort"
        ));

        allQuestions.add(new Question(
                "How do you handle stress and pressure?",
                Question.QuestionType.MIXED, 5,
                "Talk about methods and optimization tools",
                "Explain your time management and well-being techniques"
        ));


        allQuestions.add(new Question(
                "How do you motivate other team members?",
                Question.QuestionType.SOFTSKILL, 7,
                "Talk about pair programming and knowledge sharing",
                "Explain your leadership and communication style"
        ));

        allQuestions.add(new Question(
                "Describe a situation where you failed and what you learned from it.",
                Question.QuestionType.SOFTSKILL, 8,
                "Analyze the technical mistakes and solutions found",
                "Show your humility and ability to bounce back"
        ));

        allQuestions.add(new Question(
                "Where do you see yourself in 5 years?",
                Question.QuestionType.SOFTSKILL, 6,
                "Talk about technical evolution (architect, tech lead)",
                "Express your ambitions and professional values"
        ));

        allQuestions.add(new Question(
                "How do you handle technical disagreements with colleagues?",
                Question.QuestionType.MIXED, 7,
                "Talk about benchmarks, tests, and proof of concepts",
                "Explain the importance of dialogue and compromise"
        ));


        allQuestions.add(new Question(
                "What is your biggest flaw?",
                Question.QuestionType.PIEGE, 6,
                "Mention a technical weakness you are improving",
                "Turn a flaw into a strength (perfectionism, etc.)"
        ));

        allQuestions.add(new Question(
                "Why should we choose you over another candidate?",
                Question.QuestionType.PIEGE, 7,
                "Highlight your unique technical skills",
                "Talk about your motivation and cultural fit"
        ));

        allQuestions.add(new Question(
                "If you could be an animal, which one would you be and why?",
                Question.QuestionType.PIEGE, 5,
                "Link qualities of the animal to a developer trait",
                "Show your creativity and sense of humor"
        ));

        allQuestions.add(new Question(
                "Why did you leave your last job/school?",
                Question.QuestionType.PIEGE, 6,
                "Talk about seeking new technical challenges",
                "Stay positive and focus on career growth"
        ));

        allQuestions.add(new Question(
                "How long do you plan to stay with us?",
                Question.QuestionType.PIEGE, 7,
                "Talk about long-term technical projects",
                "Show your commitment while staying honest"
        ));

        allQuestions.add(new Question(
                "What would you do if you disagreed with your manager?",
                Question.QuestionType.PIEGE, 8,
                "Propose data-backed solutions",
                "Explain the importance of dialogue and hierarchy"
        ));


        allQuestions.add(new Question(
                "Tell me about your most complex project.",
                Question.QuestionType.MIXED, 6,
                "Detail the architecture and technologies used",
                "Tell the story of the project and the human challenges"
        ));

        allQuestions.add(new Question(
                "How do you stay up to date with new technologies?",
                Question.QuestionType.MIXED, 4,
                "Mention technical resources (blogs, conferences)",
                "Talk about your curiosity and passion for learning"
        ));

        allQuestions.add(new Question(
                "Describe your ideal development workflow.",
                Question.QuestionType.MIXED, 5,
                "Talk about CI/CD, tests, code review",
                "Explain the importance of collaboration and communication"
        ));
    }

    public static Question getRandomQuestion(Question.QuestionType type, int minDifficulty, int maxDifficulty) {
        List<Question> filtered = new ArrayList<>();

        for (Question q : allQuestions) {
            if (q.getType() == type &&
                    q.getDifficulty() >= minDifficulty &&
                    q.getDifficulty() <= maxDifficulty) {
                filtered.add(q);
            }
        }

        if (filtered.isEmpty()) {

            for (Question q : allQuestions) {
                if (q.getType() == type) {
                    filtered.add(q);
                }
            }
        }

        if (filtered.isEmpty()) {

            return new Question(
                    "Tell me about yourself and your background.",
                    Question.QuestionType.MIXED, 3,
                    "Introduce your technical skills",
                    "Tell your story and motivations"
            );
        }

        return filtered.get(random.nextInt(filtered.size()));
    }

    public static Question getRandomQuestionAny(int minDifficulty, int maxDifficulty) {
        List<Question> filtered = new ArrayList<>();

        for (Question q : allQuestions) {
            if (q.getDifficulty() >= minDifficulty && q.getDifficulty() <= maxDifficulty) {
                filtered.add(q);
            }
        }

        if (filtered.isEmpty()) {
            return allQuestions.get(random.nextInt(allQuestions.size()));
        }

        return filtered.get(random.nextInt(filtered.size()));
    }

    @Deprecated
    public static String getRandomQuestion(String type, String difficulte) {
        int minDiff = 1, maxDiff = 3;

        switch (difficulte.toUpperCase()) {
            case "FACILE":
                minDiff = 1; maxDiff = 3;
                break;
            case "MOYEN":
                minDiff = 4; maxDiff = 6;
                break;
            case "DIFFICILE":
            case "EXTREME":
                minDiff = 7; maxDiff = 9;
                break;
        }

        Question.QuestionType qType;
        switch (type.toUpperCase()) {
            case "TECH":
                qType = Question.QuestionType.TECH;
                break;
            case "SOFTSKILL":
                qType = Question.QuestionType.SOFTSKILL;
                break;
            case "PIEGE":
                qType = Question.QuestionType.PIEGE;
                break;
            default:
                qType = Question.QuestionType.MIXED;
        }

        Question q = getRandomQuestion(qType, minDiff, maxDiff);
        return q.getText();
    }
}
