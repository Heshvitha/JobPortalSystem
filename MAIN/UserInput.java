package MAIN;

import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

import USER.User;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Job;
import USER.RECRUITER.Recruiter;

public  class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static JobSeeker scanJobSeekerDetails(){
        JobSeeker jobSeeker = new JobSeeker();
        System.out.print("Enter First Name : ");
        jobSeeker.setFirstName(scanner.next());
        System.out.print("Enter Last Name : ");
        jobSeeker.setLastName(scanner.next());
        System.out.print("Enter Email : ");
        jobSeeker.setEmail(scanner.next());
        System.out.print("Enter Password : ");
        jobSeeker.setPassword(scanner.next());
        System.out.print("Enter Date Of Birth : ");
        jobSeeker.setDateOfBirth(scanner.next());
        System.out.print("Enter age : ");
        jobSeeker.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter gender : ");
        jobSeeker.setGender(scanner.next());
        System.out.print("Enter mobile number : ");
        jobSeeker.setMobileNumber(scanner.next());
        scanner.nextLine();
        System.out.print("Enter qualification : ");
        jobSeeker.setQualification(scanner.nextLine());
        System.out.print("Enter college : ");
        jobSeeker.setCollege(scanner.nextLine());
        System.out.print("Enter percentage : ");
        jobSeeker.setPercentage(scanner.nextDouble());
        scanner.nextLine();
        System.out.print("Enter skill1 : ");
        jobSeeker.setSkill1(scanner.nextLine());
        System.out.print("Enter skill2 : ");
        jobSeeker.setSkill2(scanner.nextLine());
        System.out.print("Enter skill3 : ");
        jobSeeker.setSkill3(scanner.nextLine());
        System.out.print("Enter experience : ");
        jobSeeker.setExperience(scanner.nextInt());
        jobSeeker.setLoggedIn("true");
        return jobSeeker;
    }

    public static Recruiter scanRecruiterDetails(Recruiter recruiter){
       Recruiter recruiter = new Recruiter();
        System.out.print("Enter First Name : ");
        recruiter.setFirstName(scanner.next());
        System.out.print("Enter Last Name : ");
        recruiter.setLastName(scanner.next());
        System.out.print("Enter Email : ");
        recruiter.setEmail(scanner.next());
        System.out.print("Enter Password : ");
        recruiter.setPassword(scanner.next());
        System.out.print("Enter Date Of Birth : ");
        recruiter.setDateOfBirth(scanner.next());
        System.out.print("Enter gender : ");
        recruiter.setGender(scanner.next());
        System.out.print("Enter mobile number: ");
        recruiter.setMobileNumber(scanner.next());
        System.out.print("Enter Company name : ");
        recruiter.setCompanyName(scanner.next());
        System.out.print("Enter Designation : ");
        recruiter.setDesignation(scanner.next());
        recruiter.setLoggedIn("true");
        return recruiter;
    }

    public static String scanPassword(){
        System.out.print("Enter Password : ");
        String password = scanner.next();
        return password;
    }

    public static int scanChoice(){
        System.out.print("Enter Choice : ");
        int choice = scanner.nextInt();
        return choice;
    }
    public static String scanEmail(){
        System.out.print("Enter Email : ");
        String email = scanner.next();
        return email;
    }
    public static void applyjobs(JobSeeker user) throws SQLException{
       System.out.print("Enter job ID : ");
       String ID = scanner.next();
       boolean flag=false;
        user.setEligibleJobs();
       for(Job i : user.getEligibleJobs()){
        if(i.getId().equals(ID)){
            if(user.applyForJob(i)==true){
             System.out.println("****Application successful****"); 
            }
            else {
             System.out.println("****Application failed****");   
            }
            flag=true;
            break;
        }
        if(!flag){
         System.out.println("you aren't eligible for the job");   
        }
       }
    }

    public static void modifyJobseeker(JobSeeker jobSeeker) throws SQLException {
       UserOutput.printUpdatesRequirement(jobSeeker);
       int choice = scanner.nextInt();
       switch(choice){
        case 1 : {
            System.out.print("Enter new firstname : ");
            String fn= scanner.next();
            jobSeeker.setFirstName(fn);
            break;
        }
        case 2 : {
          System.out.print("Enter new last name : ");  
          String ln = scanner.next();
          jobSeeker.setLastName(ln);
          break;
        }
        case 3 :{
           System.out.print("Enter new email : ");
           String email=scanner.next();
           jobSeeker.setEmail(email);
           break; 
        }
        case 4 : {
          System.out.print("Enter new password : ");
          String password = scanner.next();
          jobSeeker.setPassword(password);
          break;  
        }
        case 5 : {
           System.out.print("Enter new date of birth(YYYY-MM-DD) : ");
           String date = scanner.next();
           jobSeeker.setDateOfBirth(date);
           break;  
        }
        case 6 : {
          System.out.print("Enter age : ");
           int age = scanner.nextInt();
           jobSeeker.setAge(age);
           break;  
        }
        case 7 : {
            System.out.print("Enter experience : ");
            int exp=scanner.nextInt();
            jobSeeker.setExperience(exp);
            break;
        }
        case 8 : {
            System.out.print("Enter new mobile number : ");
            String mn = scanner.next();
            jobSeeker.setMobileNumber(mn);
            break;
        }
        case 9 : {
            Main.jobSeekerMenu();
        }
    }
        if(choice!=9){
          jobSeeker.deleteUser();
          jobSeeker.Register();  
          System.out.println("Successfully updated");
        }
        Main.jobSeekerMenu();
    }
   
     public static void modifyRecruiter(Recruiter recruiter) throws SQLException{
        UserOutput.printUpdatesRequirement(recruiter);
        System.out.print("Enter the field you wish to update : ");
        int choice = scanner.nextInt();
        switch(choice)
        {
            case(1):
            {
                System.out.print("Enter the new First Name : ");
                String name=scanner.next();
                recruiter.setFirstName(name);
                break;
            }
            case(2):
            {
                System.out.print("Enter new Last Name : ");
                String name=scanner.next();
                recruiter.setLastName(name);
                break;
            }
            case(3):
            {
                System.out.print("Enter new email : ");
                String email=scanner.next();
                recruiter.setEmail(email);
                break;
            }
            case(4):
            {
                System.out.print("Enter new password : ");
                String password=scanner.next();
                recruiter.setEmail(password);
                break;
            }
            case(5):
            {
                System.out.print("Enter new Date Of Birth (yyyy-MM-dd) : ");
                String dob=scanner.next();
                recruiter.setDateOfBirth(dob);
                break;
            }
            case(6):
            {
                System.out.print("Enter new Mobile Number : ");
                String mn=scanner.next();
                recruiter.setMobileNumber(mn);
                break;
            }
            case(7):
            {  
                Main.recruiterMenu();
            }
            
        }
           if(choice!=7)
           {
                recruiter.deleteUser();
                recruiter.Register();
                System.out.println("Successfully updated");
           }
           Main.jobSeekerMenu();
        
    }
  
}
