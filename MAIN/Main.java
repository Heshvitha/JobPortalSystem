package MAIN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DATABASE.AdministratorDb;
import DATABASE.JobSeekerDb;
import DATABASE.RecruiterDb;
import DATABASE.INFO.Info;
import USER.User;
import USER.ADMINISTRATOR.Administrator;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Job;
import USER.RECRUITER.Recruiter;

public class Main{
   private static AdministratorDb administratorDb  = new AdministratorDb();
   private static RecruiterDb recruiterDb = new RecruiterDb();
   private static User user;
   private static JobSeeker jobseeker;
   private static Recruiter recruiter;
    private static Job job;
   private static Administrator  administrator;
   private static Info info = new Info();

    public static void main(String[] args) throws SQLException {
       if(args.length==0)userMenu();
       else userMenu(args);
    }


    public static void userMenu() throws SQLException{
            UserOutput.printUserLoginMenu();
            int choice = UserInput.scanChoice();
            switch(choice){
                case 1 : {
                   user= login();
                   if(user==null)return;
                   if(user.getUserType().toLowerCase().equals("jobseeker")){
                   jobseeker = (JobSeeker) administratorDb.getUser(user.getEmail());
                    jobSeekerMenu();
                }
                    else if(user.getUserType().toLowerCase().equals("recruiter")){
                        recruiter = (Recruiter) administratorDb.getUser(user.getEmail());
                        recruiterMenu();
                    }
                   else if(user.getUserType().toLowerCase().equals("administrator")){
                    administratorMenu();
                }
                   break;
                }
                case 2 : {
                   register();

                }
                
                case 0 : {
                     return;
                }
            }
        
    }

    public static void jobSeekerMenu() throws SQLException{
        UserOutput.printJobSeekerMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : {
                profileMenu();
                break;
            }
            case 2 : {
                jobSeekerJobMenu();
                break;
            }
            case 3 : {
                logout();
                userMenu();
                break;
            }
        }
    }

    public static void profileMenu() throws SQLException{
        UserOutput.printProfileMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : {
                
                viewUserProfile();
                break;
            }
            case 2 : {
                modifyUserProfile();
                break;
            }
            case 3 : {
               exitProfile();
            } 
        }
    }

    public static void viewUserProfile() {
         if(user.getUserType().equalsIgnoreCase("jobseeker")){
           jobseeker.getDetails(); 
         }
         if(user.getUserType().equalsIgnoreCase("recruiter")){
            recruiter.getDetails();
         }
         if(user.getUserType().equalsIgnoreCase("administrator")){
            administrator.getDetails();
         }
     }
    public static void modifyUserProfile() throws SQLException{
        if(user.getUserType().equalsIgnoreCase("jobseeker")){
            UserInput.modifyJobseeker(jobseeker); 
          }
        if(user.getUserType().equalsIgnoreCase("recruiter")){
            UserInput.modifyRecruiter(recruiter); 
          }
          if(user.getUserType().equalsIgnoreCase("administrator")){
            UserInput.modifyAdministrator(administrator);
        }  
    }
    public static void exitProfile() throws SQLException{
        if(user.getUserType().equalsIgnoreCase("jobseeker")){
            jobSeekerMenu(); 
          }
          if(user.getUserType().equalsIgnoreCase("recruiter")){
              recruiterMenu();        
        }
        if(user.getUserType().equalsIgnoreCase("administrator")){
            administratorMenu();
        }
    }

    public static void jobSeekerJobMenu() throws SQLException{
        while(true){
        UserOutput.printJobSeekerJobMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : info.display_jobs();
            break;
            case 2 : UserInput.applyjobs(jobseeker);
            break;
            case 3 : jobseeker.getAppliedJobs();
            break;
            case 4 : jobSeekerMenu();
            break;
            default : System.out.println("Invalid credential! try again");
        }
        if(choice==4){
           break; 
        }
        }
    }

    public static void recruiterMenu() throws SQLException{
        UserOutput.printRecruiterMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : {
               profileMenu();
               break;
            }
            case 2 : {
                recruiterJobMenu();
                break;
            }
            case 3 : {
                applicationsMenu();
                break;
            }
            case 4 : {
                logout();
                userMenu();
                break;
            }
        }
    }

    public static void recruiterJobMenu() throws SQLException{
        UserOutput.printRecruiterJobMenu();
        int choice = UserInput.scanChoice();
        
        switch(choice){
            case 1 :{
                viewJobs(recruiter);
                break;
            }
            case 2 : {
                job = UserInput.scanJobDetails();
                recruiter.postJob(job);
                break;
            }
            case 3 : {
                UserInput.modifyJob(recruiter,job); 
                break;
            }
            case 4 : {
                UserInput.deleteJob(recruiter);
                break;
            }
            case 5 : {
                recruiterMenu();
                break;
            }
        }
    }

    public static void applicationsMenu(){

    }

    public static void administratorMenu() throws SQLException{
        UserOutput.printAdministratorMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : profileMenu();
            break;
            case 2 : info.count(administrator);
                     info.dispay_users(administrator);
                     break;
            case 3 : logout();         
        }
    }

    public static void userMenu(String[] args) throws SQLException{
        String arg = commandArgs(args);
        switch(arg){
            case "help" : {
                UserOutput.printHelp();
                break;
            }
            case "login" : {
              user = login(args);
                break;
            }
            case "login1" : {
               user = login1(args);
                break;
            }
            case "logout" : {

            }
            case "registeradministrator" : {
                registerAdministrator(args[2]);
                break;
            }
            case "registerrecruiter" : {
                registerRecruiter(args[2]);
                break;
            }
            case "registerjobseeker" : {
                registerJobSeeker(args[2]);
                break;
            }
            case "viewjobs" : {
                viewJobs();
                break;
            }
            case "viewprofile" : {

            }
            case "viewappliedjobs" : {

            }
            case "viewpostedjobs" : {
                recruiter = (Recruiter) recruiterDb.getUser(args[1]);
                if(recruiter == null) {

                }else viewJobs(recruiter);
                break;
            }
            case "searchprofile" : {

            }
            case "searchjob" : {

            }
            case "searchjobs" : {

            }
            case "searchjobsltminexperience" :{

            }
            case "searchjobsgtnumberofvacancies" : {

            }
            case "applyjob" : {

            }
            case "postjobs" : {

            }
            case "updatejobs" : {

            }
            case "deletejobs" : {

            }
           
        }
    }

    public static String commandArgs(String[] args){
        if(args.length == 0)return null;
        else if(args.length == 1){
            if(args[0].toLowerCase().equals("help"))return "help";
            else if(args[0].toLowerCase().equals("viewjobs"))return "viewjobs";
        }
        else if(args.length == 2){
            if(args[0].toLowerCase().equals("login"))return "login1";
            else if(args[0].equalsIgnoreCase("logout"))return "logout";
            else if(args[0].equalsIgnoreCase("viewprofile"))return "viewprofile";
            else if(args[0].equalsIgnoreCase("viewappliedjobs"))return "viewappliedjobs";
            else if(args[0].equalsIgnoreCase("searchjob"))return "searchjob";
            else if(args[0].equalsIgnoreCase("searchjobs"))return "searchjobs";
            else if(args[0].equalsIgnoreCase("viewpostedjobs"))return "viewpostedjobs";
            
        }
        else if(args.length==3){
            if(args[0].toLowerCase().equals("login"))return "login";
            else if(args[0].equalsIgnoreCase("register") && args[1].equalsIgnoreCase("administrator"))return "registeradministrator";
            else if(args[0].equalsIgnoreCase("register") && args[1].equalsIgnoreCase("recruiter"))return "registerrecruiter";
            else if(args[0].equalsIgnoreCase("register") && args[1].equalsIgnoreCase("jobseeker"))return "registerjobseeker";
            else if(args[0].equalsIgnoreCase("searchprofile"))return "searchprofile";
            else if(args[0].equalsIgnoreCase("applyjob"))return "applyjob";
            else if(args[0].equalsIgnoreCase("postjobs"))return "postjobs";
            else if(args[0].equalsIgnoreCase("updatejobs"))return "updatejobs";
            else if(args[0].equalsIgnoreCase("deletejobs"))return "deletejobs";
        }
        else if(args.length==4){
            if(args[0].equalsIgnoreCase("searchjobs") && args[2].equalsIgnoreCase("lt"))return "searchjobsltminexperience";
            else if(args[0].equalsIgnoreCase("searchjobs") && args[2].equalsIgnoreCase("gt"))return "searchjobsgtnumberofvacancies";
        }
        return null;
    }

    public static void logout() throws SQLException{
        user.Logout();
    }

    public static void registerRecruiter(String csvFilePath) throws SQLException{
        recruiter = new Recruiter();
        if(recruiter.Register(csvFilePath)){
            
            System.out.println("Registration Successfull!");
            recruiter.getDetails();        }
        else System.out.println("Registration failed!");
    }

    public static void registerJobSeeker(String csvFilePath) throws SQLException{
        jobseeker = new JobSeeker();
        if(jobseeker.Register(csvFilePath)){
            System.out.println("Registration Successfull!");
            jobseeker.getDetails();
        }
        else System.out.println("Registration failed!");
    }

    public static void registerAdministrator(String csvFilePath) throws SQLException{
        administrator = new Administrator();
        if(administrator.Register(csvFilePath)){
            System.out.println("Registration Successfull!");
           
        }
        else System.out.println("Registration failed!");
    }

    public static User login() throws SQLException{
        String email = UserInput.scanEmail();
        String password = UserInput.scanPassword();
        User user = administratorDb .getUser(email);
        if(user==null){
            System.out.println("Invalid Credentials");
           
        }
       else if(user.Login(email,password )){
            System.out.println("Login Successful!");
            
        }else{
            System.out.println("Wrong Password! Try Again!");
        }
        return user;
    }

    

    public static User login(String[] args) throws SQLException{
        User user = administratorDb .getUser(args[1]);
        if(user==null){
            System.out.println("Invalid Credentials");
           
        }
        if(user.Login(args[1],args[2] )){
            System.out.println("Login Successful!");
            
        }else{
            System.out.println("Try Again!");
        }
        return user;
    }
    
    public static void register() throws SQLException{
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("1.Jobseeker  2.Recruiter 3.Administrator");
               System.out.println("Choose the usertype :");
               int choice= in.nextInt();
               if(choice==1){
                jobseeker=UserInput.scanJobSeekerDetails();
                jobseeker.Register();
                jobSeekerMenu();
               }
               if(choice==2){
                recruiter=UserInput.scanRecruiterDetails();
                recruiter.Register();
                recruiterMenu();
               }
               if(choice==3){
                administrator=UserInput.scanAdministratorDetails();
                administrator.Register();
                administratorMenu();
               }
        }
    }

    public static User login1(String[] args) throws SQLException{
        String password = UserInput.scanPassword();
        User user = administratorDb .getUser(args[1]);
        if(user==null){
            System.out.println("Invalid Credentials");
        
        }
        if(user.Login(args[1], password)){
            System.out.println("Login Successful!");
        }else{
            System.out.println("Try Again!");
        }
        return user;
    }

    public static void viewJobs() throws SQLException{
        ArrayList<Job> jobList = administratorDb.getAllJobs();
        for(Job job : jobList){
            UserOutput.printJobDetails(job);
            System.out.println("***********************************************************************************************************");
        }
    }

    public static void viewJobs(Recruiter recruiter) throws SQLException{
        ArrayList<Job> jobList = recruiterDb.getJobsPosted(recruiter.getCompanyName());
        UserOutput.printJobTitles(jobList);
        int choice = UserInput.scanChoice();
        if(choice >=1 && choice <= jobList.size())UserOutput.printJobDetails(jobList.get(choice));
        else if(choice == jobList.size()+1){
            for(Job job : jobList){
                UserOutput.printJobDetails(job);
                System.out.println("***********************************************************************************************************");
            }
        }
        else System.out.println("Invalid Choice!");
    }

   

}
