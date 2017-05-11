package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
public class Sequence {
    String sequence;
    double score;

    public Sequence(String sequence, double score) {
        this.sequence = sequence;
        this.score = score;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
