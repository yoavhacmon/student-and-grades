package yoav.student.and.grades.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Builder
@ToString
public class Grade {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Topic topic;
    private int score;

    public Grade() {}

    public Grade(long id, Topic topic, int score) {
        this.id = id;
        this.topic = topic;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
