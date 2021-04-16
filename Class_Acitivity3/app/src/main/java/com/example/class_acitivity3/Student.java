package com.example.class_acitivity3;

import java.io.Serializable;

public class Student implements Serializable {

    // adroid는 activity 마다 하나의 네트워크로 본다.
    // java에서 새로운 객체를 생성해서 데이터를 보낼 때, serializable을 통해서 byte로 순치적으로
    // 전송을 한다.

    private long sNo;
    private String sName;
    private String sMajor;

    public Student() {
        super();
    }

    public Student(long sNo, String sName, String sMajor) {
        this.sNo = sNo;
        this.sName = sName;
        this.sMajor = sMajor;
    }

    public long getsNo() {
        return sNo;
    }

    public void setsNo(long sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsMajor() {
        return sMajor;
    }

    public void setsMajor(String sMajor) {
        this.sMajor = sMajor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sNo=" + sNo +
                ", sName='" + sName + '\'' +
                ", sMajor='" + sMajor + '\'' +
                '}';
    }
}
