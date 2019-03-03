package SolutionRater;

public class ExecutedOperation {
    int begining;
    int duration;
    int id;

    public ExecutedOperation(int begining, int duration, int id) {
        this.begining = begining;
        this.duration = duration;
        this.id = id;
    }

    public int getBegining() {
        return begining;
    }

    public void setBegining(int begining) {
        this.begining = begining;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExecutedOperation{" +
                "begining=" + begining +
                ", duration=" + duration +
                ", id=" + id +
                '}';
    }
}
