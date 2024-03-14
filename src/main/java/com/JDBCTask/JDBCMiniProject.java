package com.JDBCTask;
import java.util.*;

import com.JDBCTask.BeanClasses.Course;
import com.JDBCTask.BeanClasses.Student;
import com.JDBCTask.BeanClasses.Department;
import com.JDBCTask.DatabaseManager.DatabaseManager;

/**
 * Hello world!
 *
 */



public class JDBCMiniProject {
    public static void main(String[] args) throws Exception {
    	Scanner ob=new Scanner(System.in);
    	
    	char ch='T';
    	do {
    		System.out.println("\n1.Department\n2.Course\n3.Student\n4.Exit\nEnter Selection:");
    		int mainSel=ob.nextInt();
    		
    		if(mainSel==1){
    			char a='T';
    			do {
    				System.out.println("\n1.GetAllDepartments\n2.GetDepartmentById\n3.AddDepartment\n4.UpdateDepartment\n5.DeleteDepartment\n6.Back\nEnter Selection:");
    				int depSel=ob.nextInt();
    				
    				switch(depSel) {
    					case 1:
    						    DatabaseManager databaseManager = new DatabaseManager();
    						    
	    						List<Department> b = DatabaseManager.getAllDepartment();
	    						
	    						if(b.size()>0) {
	    							  System.out.println("DepartmentId\tDepartmentName");
	    							b.forEach(dpt -> {
	      							  System.out.println(dpt.getDepartmentId()+"\t\t"+dpt.getDepartmentName());
	      						});
	    						}else {
	    							System.out.println("\nNo Department Found");
	    						}
    						   break;
    					case 2:
    							System.out.print("Enter Department Id:");
    							int departmentId=ob.nextInt();
    							
    					        Department dpt=DatabaseManager.getByDepartmentId(departmentId);
    					        if(dpt!=null) {
    					        	System.out.println("\nDepartment ID\tDepartment Name");

    					    	    System.out.println(dpt.getDepartmentId()+"\t\t"+dpt.getDepartmentName());
    					        }else {
    					        	System.out.println("\nNo Department Found");
    					        }
						        break;
    					case 3:
	    						try {
	    							System.out.print("Enter Department Id:");
		    						int dptId=ob.nextInt();	
		    						System.out.print("Enter Department Name:");
									String departmentName=ob.next();
									
									DatabaseManager.addDepartment(dptId,departmentName);
									System.out.println("Successfully Inserted");
	    						}catch(Exception e) {
									System.out.println("\nDepartment Id is Duplicate Key");	    						
    							}
						   		break;
    					case 4:
    						
    							System.out.print("Enter Department Id:");
	    						int deptId=ob.nextInt();
	    						System.out.print("Enter Department Name:");
								String departmentName=ob.next();
		    					try {
		    						Department dept=DatabaseManager.getByDepartmentId(deptId);
	    					        if(dept!=null) {
	    					        	DatabaseManager.updateDepartment(deptId,departmentName);
	    					    		System.out.println("Successfully Updated");
	    					        }else {
	    					        	System.out.println("\nDepartment with given id  not Exist");
	    					        }
		    					}catch(Exception e){}    						
							   	break;
    					case 5: 
    						    System.out.print("Enter Department Id Which You want to delete:");
								int depId=ob.nextInt();
								try {	
								Department dep1= DatabaseManager.getByDepartmentId(depId);
									if(dep1.getDepartmentName()!=null) {
										DatabaseManager.deleteDepartment(depId);	
										System.out.println("Successfully Deleted");
									}
								}catch(Exception e) {
									System.out.println("No Department Found");
								}
						   break;
    					case 6:
    						   a='F';
    						   break;
						
    				}
    			}while(a=='T');
    			
    		}//end main sel 1
    		
    		if(mainSel==2){
    			char b='T';
    			do {
    				System.out.println("\n1.GetAllCourses\n2.getCourseByDepartmentId\n3.AddCourse\n4.UpdateCourse\n5.DeleteCourse\n6.Back\nEnter Selection:");
    				int depSel=ob.nextInt();
    				
    				switch(depSel) {
    					case 1:
							    DatabaseManager.getAllCourse();
    						    break;
    					case 2: 
	    						System.out.print("Enter Department Id:");
								int deptId=ob.nextInt();
						        List<Course> course=DatabaseManager.getCourseByDepartmentId(deptId);
	    						
						        if(course.size()>0) {
						        	System.out.println("\nCourse ID\tCourse Name\tDepartmentID");
						        	course.forEach(course1->{
						        		  System.out.println(course1.getCourseId()+"\t\t"+course1.getCourseName()+"\t\t"+course1.getDepartmentId());
						        	});
						        }else {
						        	System.out.println("\nNo Department Found");
						        }
    							break;
    					case 3: 
	    						try {
	    							System.out.print("Enter CourseId:");
		    						int courseId=ob.nextInt();
		    						System.out.print("Enter Course Name:");
		    						String courseName=ob.next();
		    						System.out.print("Enter Department Id:");
		    						int departId=ob.nextInt();

									DatabaseManager.addCourse(courseId,courseName,departId);
									System.out.println("Successfully Inserted");
	    						}catch(Exception e) {
									System.out.println("\nCourse Id is Duplicate Key");	    				
								}
							    break;
    					case 4: 
    							System.out.print("Enter CourseId:");
								int courId=ob.nextInt();
								System.out.print("Enter Course Name:");
								String courName=ob.next();
								System.out.print("Enter Department Id:");
								int deparId=ob.nextInt();
								try {
		    						Course cour=DatabaseManager.getCourseByCourseId(courId);
		    						Department dept=DatabaseManager.getByDepartmentId(deparId);
		    				
	    					        if(dept!=null) {
	    					        	if(cour!=null) {
		    					        	DatabaseManager.updateCourse(courId,courName,deparId);
		    					    		System.out.println("Successfully Updated");
		    					    	
	    					        	}else {
	    					        		System.out.println("\nCourse Not Found");
	    					        	}
	    					        }else 
	    					        	System.out.println("\nDpt Not Found");
		    					}catch(Exception e){}
							    break;
    					case 5: 
    							System.out.print("Enter COURSE Id Which You want to delete:");
    							int corsId=ob.nextInt();
									try {	
										Course cour= DatabaseManager.getCourseByCourseId(corsId);
										if(cour.getCourseName()!=null) {
											DatabaseManager.deleteCourse(corsId);
											System.out.println("Successfully Deleted");
										}
									}catch(Exception e) {
										System.out.println("No Course Found");
									}
    							break;
    					case 6:
    						 
    					
	    						  b='F';
	    						  break;
    				}
    			}while(b=='T');
    			
    		}//end main sel 2
    		
    		if(mainSel==3){
    			char c='T';
    			do {
    				System.out.println("\n1.GetAllStudents\n2.GetStudentByDepartmentId\n3.GetStudentByCourseID\n4.AddStudent\n5.UpdateStudent\n6.DeleteStudent\n7.Back\nEnter Selection:");
    				int depSel=ob.nextInt();
    				
    				switch(depSel) {
    					case 1:
    						   DatabaseManager.getAllStudent();
    						   break;
    					case 2: System.out.print("Enter Department Id:");
								int deptId=ob.nextInt();
						        List<Student> std=DatabaseManager.getStudentByDepartmentId(deptId);
						        if(std.size()>0) {
						        	System.out.println("\nStudent ID \tDepartment ID\tStudent Name\tRoll NO");
						        	std.forEach(stdu->{
						        		  System.out.println(stdu.getStudentId()+"\t\t"+stdu.getDepartmentId()+"\t\t"+stdu.getStudentName()+"\t\t"+stdu.getRollNO());
						        	});
						        }else {
						        	System.out.println("\nNo Student Found");
						        }
							    break;
    					case 3: 
	    						try {
	    						System.out.print("Enter course Id:");
								int courseId=ob.nextInt();	
	   							List<Student> std1=DatabaseManager.getStudentByCourseID(courseId);
									
							        if(std1.size()>0) {
							        	System.out.println("\nStudent ID \tDepartment ID\tStudent Name\tRoll NO");
							        	std1.forEach(stdue->{
							        		  System.out.println(stdue.getStudentId()+"\t\t"+stdue.getDepartmentId()+"\t\t"+stdue.getStudentName()+"\t\t"+stdue.getRollNO());
							        	});
							        }else {
							        	System.out.println("\nNo Course Found");
							        }
	    						}catch(Exception e) {}
    							break;
    					case 4:	
	    						try {
			    						System.out.print("Enter Student Id:");
			    						int stdId=ob.nextInt();
			    						System.out.print("Enter Department Id:");
			    						int depatId=ob.nextInt();
			    						System.out.print("Enter Student Name :");
			    						String studentName=ob.next();
			    						System.out.print("Enter Roll NO:");
			    						int rollNo=ob.nextInt();
			    						
											DatabaseManager.addStudent(stdId,depatId,studentName,rollNo);
											System.out.println("Successfully Inserted");		
		    						}catch(Exception e) {
										System.out.println("\nStudent Id is Duplicate Key");	
									}
    							break;
    					case 5: 
    							System.out.print("Enter Department Id:");
    							int depatId=ob.nextInt();
    							Department dept=DatabaseManager.getByDepartmentId(depatId);
    							if(dept!=null) {
						        
						        		System.out.print("Enter Student Id:");
										int stdId=ob.nextInt();
										
										Student stu=DatabaseManager.getStudentByStudentId(stdId);
										if(stu!=null) {
											System.out.print("Enter Student Name :");
											String studentName=ob.next();
											System.out.print("Enter Roll NO:");
											int rollNo=ob.nextInt();
		    					        	DatabaseManager.updateStudent(depatId,stdId,studentName,rollNo);
		    					        	System.out.println("Successfully Updated");
										}else {
								        	System.out.println("\nStudent Not Found");
										}
    							} else {
	    								System.out.println("\nDpt Not Found");
						        
						        	}
    							
    							break;
		    			case 6:
		    					System.out.print("Enter Student Id Which You want to delete:");
								int studentId=ob.nextInt();
								
			    				try {	
										Student stu= DatabaseManager.getStudentByStudentId(studentId);
										if(stu.getStudentName()!=null) {
											DatabaseManager.deleteStudent(studentId);
											System.out.println("\nSuccessfully Deleted");
										}
									}catch(Exception e) {
										System.out.println("\nNo Student Found In Student Id");
									}
							    break;
    					case 7:
    						   c='F';
    						   break;
						
    				}
    			}while(c=='T');
    			
    		}//end main sel 3
    		
    		if(mainSel==4){
    			System.out.println("Good Bye");
    			System.exit(0);
    		}//end main sel 4
    		
    	}while(ch=='T');
    }
}
