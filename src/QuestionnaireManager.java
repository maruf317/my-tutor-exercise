import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QuestionnaireManager {

    private Map<Integer, TutorInfo> tutorMap;

    public QuestionnaireManager() {
        this.tutorMap = new HashMap<>();
    }

    public TutorInfo processSubmission(int tutorId, Map<Question, Set<Integer>> questionnaireSubmission) {
        TutorInfo tutorInfo = tutorMap.get(tutorId);
        if(tutorInfo == null) {
            tutorInfo = new TutorInfo(questionnaireSubmission);
        }
        tutorInfo.setScore(calculateScore(questionnaireSubmission));
        tutorMap.put(tutorId, tutorInfo);
        return tutorInfo;
    }

    private int calculateScore(Map<Question, Set<Integer>> questionnaireSubmission) {
        int score = 0;
        // for every question, get the set of answers the user selected (could be 0 or all options)
        for(Question question : questionnaireSubmission.keySet()) {
            Set<Integer> selectedAnswers = questionnaireSubmission.get(question);
            if(selectedAnswers.isEmpty()) {
                continue;
            }
            // get the points map for the current question
            Map<Integer, Integer> pointsMap = question.getAnswerPointMap();
            for(int selectedAnswer : selectedAnswers) {
                score += pointsMap.get(selectedAnswer);
            }
        }
        return score;
    }

    public int getScore(int tutorId) {
        TutorInfo tutorInfo = tutorMap.get(tutorId);
        if(tutorInfo == null) {
            return -1;
        }
        return tutorInfo.getScore();
    }
}
