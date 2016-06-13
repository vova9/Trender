/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Vladimir.Avtushko
 */
@Embeddable
public class AnswerPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "answer_id")
    private long answerId;
    @Basic(optional = false)
    @Column(name = "question_question_id")
    private long questionQuestionId;

    public AnswerPK() {
    }

    public AnswerPK(long answerId, long questionQuestionId) {
        this.answerId = answerId;
        this.questionQuestionId = questionQuestionId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getQuestionQuestionId() {
        return questionQuestionId;
    }

    public void setQuestionQuestionId(long questionQuestionId) {
        this.questionQuestionId = questionQuestionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) answerId;
        hash += (int) questionQuestionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswerPK)) {
            return false;
        }
        AnswerPK other = (AnswerPK) object;
        if (this.answerId != other.answerId) {
            return false;
        }
        if (this.questionQuestionId != other.questionQuestionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trender.model.entity.AnswerPK[ answerId=" + answerId + ", questionQuestionId=" + questionQuestionId + " ]";
    }
    
}
