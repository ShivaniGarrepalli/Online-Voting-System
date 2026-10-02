package votingSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        VotingService service = new VotingService();

        while (true) {

            System.out.println();
            System.out.println("===== Online Voting System =====");
            System.out.println("1. Register");
            System.out.println("2. Login and Vote");
            System.out.println("3. Candidate list");
            System.out.println("4. View total votes");
            System.out.println("5. Result (Finalist)");
            System.out.println("6. Runner-up");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

           try {
                int choice = Integer.parseInt(sc.next());
                if (choice == 1) {
                    System.out.print("Enter username: ");
                    String username = sc.next();
                    System.out.print("Enter password: ");
                    String password = sc.next();
					service.registerUser(username, password);				
                } else if (choice == 2) {
                    System.out.print("Enter username: ");
                    String username = sc.next();
                    System.out.print("Enter password: ");
                    String password = sc.next();                    
					service.loginUser(username, password);
                    service.displayCandidates();
                    System.out.print("Enter candidate id: ");
                    int candidateId = Integer.parseInt(sc.next());                    
					service.castVote(username, candidateId);			
                } else if (choice == 3) {
                    service.displayCandidates();
                } else if (choice == 4) {
                    service.viewTotalVotes();
                } else if (choice == 5) {
                    service.displayResult();
                } else if (choice == 6) {
                    service.displayRunnerUp();
                } else if (choice == 0) {
                    System.out.println("Thank you!");
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
        }
	        catch (InvalidLoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        catch (AlreadyVotedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidCandidateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (UserAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            
        }
    }
}