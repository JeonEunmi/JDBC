package com.emp.domain;

import java.sql.Date;

public class Employees {

   private String empId, name, ssn, phone, reg_name, dept_name, job_title, regId, deptId, jobId;
   private int basicPay, extraPay, pay;
   private Date hireDate;

   public Employees() {

   }

   // 사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여

   public Employees(String empId, String name, String ssn, Date hireDate, String phone, String reg_name,
         String dept_name, String job_title, String regId, String deptId, String jobId, int basicPay, int extraPay,
         int pay) {

      this.empId = empId;
      this.name = name;
      this.ssn = ssn;
      this.phone = phone;
      this.reg_name = reg_name;
      this.dept_name = dept_name;
      this.job_title = job_title;
      this.regId = regId;
      this.deptId = deptId;
      this.jobId = jobId;
      this.basicPay = basicPay;
      this.extraPay = extraPay;
      this.pay = pay;
      this.hireDate = hireDate;
   }

   public String getEmpId() {
      return empId;
   }

   public void setEmpId(String empId) {
      this.empId = empId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSsn() {
      return ssn;
   }

   public void setSsn(String ssn) {
      this.ssn = ssn;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getReg_name() {
      return reg_name;
   }

   public void setReg_name(String reg_name) {
      this.reg_name = reg_name;
   }

   public String getDept_name() {
      return dept_name;
   }

   public void setDept_name(String dept_name) {
      this.dept_name = dept_name;
   }

   public String getJob_title() {
      return job_title;
   }

   public void setJob_title(String job_title) {
      this.job_title = job_title;
   }

   public String getRegId() {
      return regId;
   }

   public void setRegId(String regId) {
      this.regId = regId;
   }

   public String getDeptId() {
      return deptId;
   }

   public void setDeptId(String deptId) {
      this.deptId = deptId;
   }

   public String getJobId() {
      return jobId;
   }

   public void setJobId(String jobId) {
      this.jobId = jobId;
   }

   public int getBasicPay() {
      return basicPay;
   }

   public void setBasicPay(int basicPay) {
      this.basicPay = basicPay;
   }

   public int getExtraPay() {
      return extraPay;
   }

   public void setExtraPay(int extraPay) {
      this.extraPay = extraPay;
   }

   public int getPay() {
      return pay;
   }

   public void setPay(int pay) {
      this.pay = pay;
   }

   public Date getHireDate() {
      return hireDate;
   }

   public void setHireDate(Date hireDate) {
      this.hireDate = hireDate;
   }

   // 사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여
   @Override
   public String toString() {
      return String.format("%s / %s / %s / %tF / %s / %s / %s / %s / %,d / %,d / %,d ", 
            this.getEmpId(), this.getName(), 
            this.getSsn(), this.getHireDate(), 
            this.getPhone(), this.getReg_name(), 
            this.getDept_name(), this.getJob_title(), 
            this.getBasicPay(), this.getExtraPay(),
            this.getPay());
   }
   
   

}