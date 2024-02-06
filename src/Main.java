import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running tests...\n");
        runTests();
    }

    private static void runTests() {
        testSetup();

        // 1 -
        calcScore_submissionContainsComboAnswers_correctResult();

        // 2 -
        retrieveScore_tutorIdDoesNotExist_returnNegativeOne();

        // 3 -
        calcScore_submissionContainsNoAnswers_returnsZero();

        // 4 -
        calcScore_submissionContainsOnlyMultiSelectAnswer_correctResult();

        // 5 -
        calcScore_submissionContainsOnlySingleSelectAnswer_correctResult();

        // 6 -
        calcScore_multipleSubmissions_getLatestScore();

    }

    public static void calcScore_submissionContainsComboAnswers_correctResult() {
        Map<Question, Set<Integer>> submission = setupSubmission();
        int tutorId = 1;
        questionnaireManager.processSubmission(tutorId, submission);

        int result = questionnaireManager.getScore(tutorId);
        System.out.println("Test 1 -- Calculate score, valid Input, valid result.");
        System.out.printf("Expected 4, got %d\n\n", result);
    }

    public static void retrieveScore_tutorIdDoesNotExist_returnNegativeOne() {
        int tutorId = 30;
        int result = questionnaireManager.getScore(tutorId);
        System.out.println("Test 2 -- Valid Input, tutor has no submissions.");
        System.out.printf("Expected -1, got %d\n\n", result);
    }

    public static void calcScore_submissionContainsNoAnswers_returnsZero() {
        Map<Question, Set<Integer>> submission = setupEmptySubmission();
        int tutorId = 1;
        questionnaireManager.processSubmission(tutorId, submission);

        int result = questionnaireManager.getScore(tutorId);
        System.out.println("Test 3 -- Calculate score, submission contains no answers, score is 0.");
        System.out.printf("Expected 0, got %d\n\n", result);
    }

    private static void calcScore_submissionContainsOnlyMultiSelectAnswer_correctResult() {
        Map<Question, Set<Integer>> submission = setupMultiSelectAnswerSubmission();
        int tutorId = 3;
        questionnaireManager.processSubmission(tutorId, submission);

        int result = questionnaireManager.getScore(tutorId);
        System.out.println("Test 4 -- Calculate score, submission contains only multiselect answers, score is 2.");
        System.out.printf("Expected 3, got %d\n\n", result);
    }

    private static void calcScore_submissionContainsOnlySingleSelectAnswer_correctResult() {
        Map<Question, Set<Integer>> submission = setupSingleSelectAnswerSubmission();
        int tutorId = 4;
        questionnaireManager.processSubmission(tutorId, submission);

        int result = questionnaireManager.getScore(tutorId);
        System.out.println("Test 5 -- Calculate score, submission contains only single select answer, score is 2.");
        System.out.printf("Expected 2, got %d\n\n", result);
    }

    private static void calcScore_multipleSubmissions_getLatestScore() {
        int tutorId = 5;
        Map<Question, Set<Integer>> submission = setupSingleSelectAnswerSubmission();
        questionnaireManager.processSubmission(tutorId, submission);
        submission = setupMultiSelectAnswerSubmission();
        questionnaireManager.processSubmission(tutorId, submission);

        int result = questionnaireManager.getScore(tutorId);
        System.out.println("Test 6 -- Calculate score, multiple questionnaire submissions, get latest score.");
        System.out.printf("Expected 3, got %d\n\n", result);
    }

    private static Map<Question, Set<Integer>> setupSubmission() {
        Map<Question, Set<Integer>> submission = new HashMap<>();
        // q1 responses - i.e. for q1, tutor chose answers 1,2,3
        submission.put(multiSelectQuestion, new HashSet<>(Arrays.asList(1,2,3)));
        // q2 responses - for q2, tutor chose answers 1
        submission.put(singleSelectQuestion, new HashSet<>(Arrays.asList(2)));
        return submission;
    }

    private static Map<Question, Set<Integer>> setupEmptySubmission() {
        Map<Question, Set<Integer>> submission = new HashMap<>();
        // q1 responses - empty
        submission.put(multiSelectQuestion, new HashSet<>());
        // q2 responses - empty
        submission.put(singleSelectQuestion, new HashSet<>());
        return submission;
    }

    private static Map<Question, Set<Integer>> setupMultiSelectAnswerSubmission() {
        Map<Question, Set<Integer>> submission = new HashMap<>();
        // q1 responses only
        submission.put(multiSelectQuestion, new HashSet<>(Arrays.asList(1,2,3,4)));
        return submission;
    }

    private static Map<Question, Set<Integer>> setupSingleSelectAnswerSubmission() {
        Map<Question, Set<Integer>> submission = new HashMap<>();
        // q2 response only
        submission.put(singleSelectQuestion, new HashSet<>(Arrays.asList(3)));
        return submission;
    }

    private static void testSetup() {
        generateQuestions();
        questionnaireManager = new QuestionnaireManager();
    }

    private static void generateQuestions() {
        // q1 points
        Map<Integer, Integer> z = new HashMap();
        z.put(1,1);
        z.put(2,1);
        z.put(3,1);
        z.put(4,0);
        multiSelectQuestion = new Question(1, z);
        // q2 points
        Map<Integer, Integer> zz = new HashMap();
        zz.put(1, 0);
        zz.put(2, 1);
        zz.put(3, 2);
        zz.put(4, 0);
        singleSelectQuestion = new Question(2, zz);
    }

    private static QuestionnaireManager questionnaireManager;
    private static Question multiSelectQuestion;
    private static Question singleSelectQuestion;
}