import java.util.Map;
import java.util.Set;

/*
* Extensions - tutorInfo can track "lastUpdated" DateTime so that a tutor can redo the test and override
*              their previous results after some configured "cool-off" period.
* */

public class TutorInfo {
    private Map<Question, Set<Integer>> questionnaireSubmission;
    private int score;

    TutorInfo(Map<Question, Set<Integer>> questionnaireSubmission) {
        this.questionnaireSubmission = questionnaireSubmission;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
