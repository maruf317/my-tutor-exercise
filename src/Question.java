import java.util.Map;

public class Question {

    private Map<Integer, Integer> answerPointMap;
    private Integer id;

    public Question(Integer id, Map<Integer, Integer> answerPointMap) {
        this.answerPointMap = answerPointMap;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public Map<Integer, Integer> getAnswerPointMap() {
        return this.answerPointMap;
    }

    @Override
    public boolean equals(final Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof Question))
            return false;
        Question question = (Question) obj;
        return question.getId() == this.id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
