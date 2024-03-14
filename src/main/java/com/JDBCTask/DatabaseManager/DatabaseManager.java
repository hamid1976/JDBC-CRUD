package com.JDBCTask.DatabaseManager;

import java.sql.*;
import java.util.*;

import com.JDBCTask.BeanClasses.Course;
import com.JDBCTask.BeanClasses.Department;
import com.JDBCTask.BeanClasses.Student;
import com.JDBCTask.BeanClasses.StudentCourse;



public class DatabaseManager {
	private static Connection con;

    
	static {
	    try{
	        init();
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}

	private static void init() throws Exception{
	     String jdbcURL="jdbc:mysql://localhost:3306/jdbcrud";
	     String username="root";
	     String password="root";
	      //  System.out.println("connect");
	         con = DriverManager.getConnection(jdbcURL,username,password);
	            System.out.println("Successfully Connected with Database");
	     
	}
	
	
	public static List <Department> getAllDepartment() throws Exception{
        String  query = "select * from department ";
    //    System.out.println(query);
        
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			List <Department> v=new ArrayList();
			
		while(result.next()){
		    Department bean = new Department();
		    bean.setDepartmentId(result.getInt("department_id"));
		    bean.setDepartmentName(result.getString("department_name"));
		    v.add(bean);
		}
		return v;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getAllDepartment without Argument

	public static  Department getByDepartmentId(int departmentId) throws Exception{
        String  query = "select * from department where department_id="+departmentId;
      //  System.out.println(query);
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Department bean =null;
			
		while(result.next()){
			bean=new Department();
		    bean.setDepartmentId(result.getInt("department_id"));
		    bean.setDepartmentName(result.getString("department_name"));
		    
		}
		return bean;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end department with argument
	
	public static int  addDepartment(int departmentId,String departmentName) throws Exception{
	    String query= "insert into department(department_id,department_name) values('"+departmentId+"','"+departmentName+"')"; 
	 //  System.out.println(query);
	    
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end addDepartment
	
	public static int  updateDepartment (int departmentId,String departmentName) throws Exception{
	    String query= "update  department set department_name ='"+departmentName+ "' where department_id = " +departmentId ;
	 //  System.out.println(query);
	    
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end updateDepartment

	public static int  deleteDepartment(int departmentId) throws Exception{
	    String query= "delete from department where department_id=" +departmentId ;
	  //  System.out.println(query);
	   
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end delete department by id
	
	public static Vector  getAllCourse() throws Exception{
        String  query = "select * from course";
    //    System.out.println(query);
  
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v= new Vector();
		    
			
		while(result.next()){
		    Course bean = new Course();
		    bean.setCourseId(result.getInt("course_id"));
		    bean.setCourseName(result.getString("course_name"));
		    bean.setDepartmentId(result.getInt("department_id"));
		    v.addElement(bean);
		    System.out.println(bean.getCourseId()+"\t"+bean.getCourseName()+"\t"+bean.getDepartmentId());
//		    System.out.println("Course Id     :"+bean.getCourseId());
//		    System.out.println("Course Name   :"+bean.getCourseName());
//		    System.out.println("Department ID :"+bean.getDepartmentId());
//		    
//		    System.out.println("*****************************************");
		}
		return v;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getCourse with no argument
	public static List<Course>  getCourseByDepartmentId(int departmentId) throws Exception{
        String  query = "select * from course where department_id ="+departmentId;
     //   System.out.println(query);
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			List <Course> v=new ArrayList();
	
		while(result.next()){
			Course bean = new Course();
		    bean.setCourseId(result.getInt("course_id"));
		    bean.setCourseName(result.getString("course_name"));
		    bean.setDepartmentId(result.getInt("department_id"));
		    v.add(bean);

		}
		return v;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getCourseByDepartmentId
	public static Course  getCourseByCourseId(int courseId) throws Exception{
        String  query = "select * from course where course_id ="+courseId;
     //   System.out.println(query);
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Course bean=null;
	
		while(result.next()){
		    bean = new Course();
		    bean.setCourseId(result.getInt("course_id"));
		    bean.setCourseName(result.getString("course_name"));
		    bean.setDepartmentId(result.getInt("department_id"));

		}
		return bean;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getCourseByDepartmentId
	
	
	public static int  addCourse(int courseId,String courseName,int departmentId) throws Exception{
	    String query= "insert into course(course_id,course_name,department_id) values('"+courseId+"','"+courseName+"','"+departmentId+"')"; 
	//    System.out.println(query);
	    
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end addDepartment
	
	
	public static int  updateCourse(int courseId,String courseName,int departmentId) throws Exception{
		String query= "update  course set course_name = '"+courseName+"',department_id ='"+departmentId+ "'where course_id =" +courseId ;
	  
	    
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end updateDepartment

	public static int  deleteCourse(int courseId) throws Exception{
	    String query= "delete from course where course_id=" +courseId ;
	//    System.out.println(query);
	   
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end delete department by id
	public static Vector  getAllStudent() throws Exception{
        String  query = "select * from student";
      //  System.out.println(query);
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v= new Vector();
			
		while(result.next()){
		    Student bean = new Student();
		    bean.setStudentId(result.getInt("student_id"));
		    bean.setStudentName(result.getString("student_name"));
		    bean.setRollNO(result.getInt("roll_no"));
		    bean.setDepartmentId(result.getInt("dep_id"));
		    v.addElement(bean);
		    
		    System.out.println("Student Id    :"+bean.getStudentId());
		    System.out.println("Department ID :"+bean.getDepartmentId());
		    System.out.println("Student Name  :"+bean.getStudentName());
		    System.out.println("Roll No ID    :"+bean.getRollNO());
		   
		    
		    System.out.println("*****************************************");
		}
		return v;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getStudent
	public static Student  getStudentByStudentId(int studentId) throws Exception{
        String  query = "select * from student where student_id="+studentId;
     //   System.out.println(query);
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Student bean=null;
			
			
		while(result.next()){
		    bean = new Student();
		    bean.setStudentId(result.getInt("student_id"));
		    bean.setDepartmentId(result.getInt("dep_id"));
		    bean.setStudentName(result.getString("student_name"));
		    bean.setRollNO(result.getInt("roll_no"));
		    
		   // v.add(bean);
		    System.out.println("*****************************************");
		}
		return bean;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getStudent
	public static List<Student>  getStudentByDepartmentId(int departmentId) throws Exception{
        String  query = "select * from student where dep_id="+departmentId;
       // System.out.println(query);
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			List <Student> v=new ArrayList();
			
			
		while(result.next()){
		    Student bean = new Student();
		    bean.setStudentId(result.getInt("student_id"));
		    bean.setDepartmentId(result.getInt("dep_id"));
		    bean.setStudentName(result.getString("student_name"));
		    bean.setRollNO(result.getInt("roll_no"));
		    
		    v.add(bean);
		    System.out.println("*****************************************");
		}
		return v;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getStudent
	
	
	
	public static List<Student>  getStudentByCourseID(int courseId) throws Exception{
        String  query ="SELECT s.student_id,s.dep_id, s.student_name, s.roll_no FROM student s JOIN course_student sc ON s.student_id = sc.student_id JOIN Course c ON sc.course_id = c.course_id WHERE c.course_id ="+courseId;

       // System.out.println(query);
        Statement st = null;
        ResultSet result=null;
	
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			List <Student> v=new ArrayList();
			
		while(result.next()){
		    Student bean = new Student();
		
		    bean.setStudentId(result.getInt("student_id"));
		    bean.setDepartmentId(result.getInt("dep_id"));
		    bean.setStudentName(result.getString("student_name"));
		    bean.setRollNO(result.getInt("roll_no"));
		  
		    v.add(bean);
		
		   
		    
		 
		   
		}
		return v;
		        
		        }finally{
		    if (result!=null)
		        result.close();
		    if(st!=null)
		        st.close();
		    }
		
	}//end getStudent
	
	public static int  addStudent(int studentId,int departmentId,String studentName,int rollNo) throws Exception{
	    String query= "insert into student(student_id,dep_id,student_name,roll_no) values('"+studentId+"','"+departmentId+"','"+studentName+"','"+rollNo+"')";
	 //   System.out.println(query);
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end addDepartment
	
	
	public static int  updateStudent(int departmentId,int studentId,String studentName,int rollNo) throws Exception{
		//String query= "update  course set course_name = '"+courseName+"',department_id ='"+departmentId+ "'where course_id =" +courseId ;
	    String query= "update  student set dep_id ='"+departmentId+"',student_name='"+studentName+ "',roll_no='"+rollNo+ "' where student_id = " +studentId ;
	    System.out.println(query);
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end updateDepartment

	public static int  deleteStudent(int studentId) throws Exception{
	    String query= "delete from student where student_id=" +studentId ;
	  //  System.out.println(query);
	    Statement st=null;
	    try{
	        st=con.createStatement();
	        int rows=st.executeUpdate(query);
	        return rows;
	    }finally{
	        if(st!=null)
	            st.close();
	    }
	}//end delete department by id
	
	public static void closeConnection() {
	    if (con != null) {
	        try {
	            con.close();
	            System.out.println("Database connection closed.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void main(String arg[]) throws Exception{
		DatabaseManager ob=new DatabaseManager();
		getAllDepartment();
	}

}
