package com.example.Study.bean;

public class Grade {
    private int id;
    private String studentId;
    private String studentName;
    private int grammar;
    private int pronunciation;
    private int vocabulary;
    private int writting;
    private int listening;
    private int reading;
    private int speaking;
    private String date;

    public Grade() {
    }

    public Grade(int id, String studentId, String studentName, int grammar, int pronunciation, int vocabulary, int writting, int listening, int reading, int speaking, String date) {
        this.id=id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.grammar = grammar;
        this.pronunciation = pronunciation;
        this.vocabulary = vocabulary;
        this.writting = writting;
        this.listening = listening;
        this.reading = reading;
        this.speaking = speaking;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", grammar=" + grammar +
                ", pronunciation=" + pronunciation +
                ", vocabulary=" + vocabulary +
                ", writting=" + writting +
                ", listening=" + listening +
                ", reading=" + reading +
                ", speaking=" + speaking +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getGrammar() {
        return grammar;
    }

    public void setGrammar(int grammar) {
        this.grammar = grammar;
    }

    public int getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(int pronunciation) {
        this.pronunciation = pronunciation;
    }

    public int getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(int vocabulary) {
        this.vocabulary = vocabulary;
    }

    public int getWritting() {
        return writting;
    }

    public void setWritting(int writting) {
        this.writting = writting;
    }

    public int getListening() {
        return listening;
    }

    public void setListening(int listening) {
        this.listening = listening;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public int getSpeaking() {
        return speaking;
    }

    public void setSpeaking(int speaking) {
        this.speaking = speaking;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
