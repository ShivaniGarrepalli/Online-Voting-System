package votingSystem;

import java.util.HashMap;

public class VotingService {

    HashMap<String, User> users = new HashMap<>();
    HashMap<String, Boolean> hasVoted = new HashMap<>();
    HashMap<Integer, Candidate> candidates = new HashMap<>();
    HashMap<String, Integer> votes = new HashMap<>();

    public VotingService() {
        candidates.put(1, new Candidate(1, "GNR"));
        candidates.put(2, new Candidate(2, "Madhavi"));
        candidates.put(3, new Candidate(3, "Bhavani"));
        candidates.put(4, new Candidate(4, "Shivani"));

        votes.put("GNR", 0);
        votes.put("Madhavi", 0);
        votes.put("Bhavani", 0);
        votes.put("Shivani", 0);
    }

    public void registerUser(String username, String password) throws UserAlreadyExistsException {
        if (users.containsKey(username)) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        users.put(username, new User(username, password));
        hasVoted.put(username, false);      // new user has not voted yet
        System.out.println("Registered successfully");
    }

    public void loginUser(String username, String password) throws InvalidLoginException {
        if (!users.containsKey(username)) {
            throw new InvalidLoginException("Invalid username or password");
        }
        User user = users.get(username);
        if (!user.getPassword().equals(password)) {
            throw new InvalidLoginException("Invalid username or password");
        }
        System.out.println("Login successful");
    }
    public void displayCandidates() {
        for (Candidate candidate : candidates.values()) {
            System.out.println(candidate.getId() + ". " + candidate.getName());
        }
    }
    public void castVote(String username, int candidateId) throws AlreadyVotedException, InvalidCandidateException {
        if (hasVoted.get(username)) {
            throw new AlreadyVotedException("You have already voted");
        }

        if (!candidates.containsKey(candidateId)) {
            throw new InvalidCandidateException("Invalid candidate number");
        }

        String name = candidates.get(candidateId).getName();
        int oldVotes = votes.get(name);
        votes.put(name, oldVotes + 1);
        hasVoted.put(username, true);
        System.out.println("Vote recorded for " + name);
    }
    public void viewTotalVotes() {
        int total = 0;
        for (String name : votes.keySet()) {
            int count = votes.get(name);
            System.out.println(name + " : " + count + " votes");
            total = total + count;
        }
        System.out.println("Total votes : " + total);
    }
    public void displayResult() {
        String winnerName = "";
        int highestVotes = 0;
        for (String name : votes.keySet()) {
            int count = votes.get(name);
            if (count > highestVotes) {
                highestVotes = count;
                winnerName = name;
            }
        }

        if (highestVotes == 0) {
            System.out.println("No votes yet");
        } else {
            System.out.println("Finalist : " + winnerName + " (" + highestVotes + " votes)");
        }
    }
    public void displayRunnerUp() {
    	String winnerName = "";
        int highestVotes = 0;

        for (String name : votes.keySet()) {
            int count = votes.get(name);
            if (count > highestVotes) {
                highestVotes = count;
                winnerName = name;
            }
        }

        if (highestVotes == 0) {
            System.out.println("No votes yet");
            return;
        }
        String runnerUpName = "";
        int secondVotes = 0;

        for (String name : votes.keySet()) {
            int count = votes.get(name);
            if (!name.equals(winnerName) && count > secondVotes) {
                secondVotes = count;
                runnerUpName = name;
            }
        }

        if (secondVotes == 0) {
            System.out.println("No runner-up yet");
        } else {
            System.out.println("Runner-Up : " + runnerUpName + " (" + secondVotes + " votes)");
        }
    }
}