/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vladimir.Avtushko
 */
@Entity
@Table(name = "answer", catalog = "tender", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAnswerId", query = "SELECT a FROM Answer a WHERE a.answerPK.answerId = :answerId"),
    @NamedQuery(name = "Answer.findByValue", query = "SELECT a FROM Answer a WHERE a.value = :value"),
    @NamedQuery(name = "Answer.findByQuestionId", query = "SELECT a FROM Answer a WHERE a.questionId = :questionId"),
    @NamedQuery(name = "Answer.findByQuestionQuestionId", query = "SELECT a FROM Answer a WHERE a.answerPK.questionQuestionId = :questionQuestionId")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AnswerPK answerPK;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "question_id")
    private long questionId;
    @JoinTable(name = "user_has_answer", joinColumns = {
        @JoinColumn(name = "answer_answer_id", referencedColumnName = "answer_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_user_id", referencedColumnName = "user_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> userList;
    @JoinColumn(name = "question_question_id", referencedColumnName = "question_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question question;

    public Answer() {
    }

    public Answer(AnswerPK answerPK) {
        this.answerPK = answerPK;
    }

    public Answer(AnswerPK answerPK, String value, long questionId) {
        this.answerPK = answerPK;
        this.value = value;
        this.questionId = questionId;
    }

    public Answer(long answerId, long questionQuestionId) {
        this.answerPK = new AnswerPK(answerId, questionQuestionId);
    }

    public AnswerPK getAnswerPK() {
        return answerPK;
    }

    public void setAnswerPK(AnswerPK answerPK) {
        this.answerPK = answerPK;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerPK != null ? answerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerPK == null && other.answerPK != null) || (this.answerPK != null && !this.answerPK.equals(other.answerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trender.model.entity.Answer[ answerPK=" + answerPK + " ]";
    }
    
}
