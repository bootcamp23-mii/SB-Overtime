/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pandu
 */
@Entity
@Table(name = "tb_t_overtime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Overtime.findAll", query = "SELECT o FROM Overtime o")
    , @NamedQuery(name = "Overtime.findById", query = "SELECT o FROM Overtime o WHERE o.id = :id")
    , @NamedQuery(name = "Overtime.findByDate", query = "SELECT o FROM Overtime o WHERE o.date = :date")
    , @NamedQuery(name = "Overtime.findByTimeduration", query = "SELECT o FROM Overtime o WHERE o.timeduration = :timeduration")
    , @NamedQuery(name = "Overtime.findByKeterangan", query = "SELECT o FROM Overtime o WHERE o.keterangan = :keterangan")})
public class Overtime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "timeduration")
    private Integer timeduration;
    @Size(max = 100)
    @Column(name = "keterangan")
    private String keterangan;
    @Lob
    @Column(name = "signature")
    private byte[] signature;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "overtime", fetch = FetchType.LAZY)
    private List<Task> taskList;
    @JoinColumn(name = "timesheet", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TimeSheet timesheet;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Status status;

    public Overtime() {
    }

    public Overtime(String id, Date date, Integer timeduration, String keterangan, TimeSheet timesheet, Status status) {
        this.id = id;
        this.date = date;
        this.timeduration = timeduration;
        this.keterangan = keterangan;
        this.timesheet = timesheet;
        this.status = status;
    }

    public Overtime(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTimeduration() {
        return timeduration;
    }

    public void setTimeduration(Integer timeduration) {
        this.timeduration = timeduration;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    @XmlTransient
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TimeSheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(TimeSheet timesheet) {
        this.timesheet = timesheet;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Overtime)) {
            return false;
        }
        Overtime other = (Overtime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.overtime.entity.Overtime[ id=" + id + " ]";
    }
    
}
