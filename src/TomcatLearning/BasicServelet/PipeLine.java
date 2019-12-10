package TomcatLearning.BasicServelet;

public interface PipeLine {
    void invoke();

    void addValve(Valve v);

    void setbasic(Valve v);

}
