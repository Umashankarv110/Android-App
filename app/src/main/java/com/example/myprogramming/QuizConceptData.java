package com.example.myprogramming;

public class QuizConceptData {
    private String conceptTitle,concept_definitation, conceptAnswer, conceptExample, conceptKey;

    public QuizConceptData() {
    }

    public QuizConceptData(String conceptTitle, String concept_definitation, String conceptAnswer, String conceptExample) {
        this.conceptTitle = conceptTitle;
        this.concept_definitation = concept_definitation;
        this.conceptAnswer = conceptAnswer;
        this.conceptExample = conceptExample;
    }

    public String getConceptTitle() {
        return conceptTitle;
    }

    public void setConceptTitle(String conceptTitle) {
        this.conceptTitle = conceptTitle;
    }

    public String getConcept_definitation() {
        return concept_definitation;
    }

    public void setConcept_definitation(String concept_definitation) {
        this.concept_definitation = concept_definitation;
    }

    public String getConceptAnswer() {
        return conceptAnswer;
    }

    public void setConceptAnswer(String conceptAnswer) {
        this.conceptAnswer = conceptAnswer;
    }

    public String getConceptExample() {
        return conceptExample;
    }

    public void setConceptExample(String conceptExample) {
        this.conceptExample = conceptExample;
    }

    public String getConceptKey() {
        return conceptKey;
    }

    public void setConceptKey(String conceptKey) {
        this.conceptKey = conceptKey;
    }
}
