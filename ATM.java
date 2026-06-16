import java.util.*;

class ATM {
    public static void main(String[] args) {

        int pin = 3456;
        long balance = 60000;
        final int MAX_ATTEMPTS = 3;

        Scanner sc = new Scanner(System.in);
        System.out.println("======== Welcome to HDFC Bank ========");

        // Bug Fix 3: PIN retry logic (max 3 attempts)
        int attempts = 0;
        boolean pinCorrect = false;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter PIN: ");
            int upin = sc.nextInt();
            attempts++;

            if (pin == upin) {
                pinCorrect = true;
                break;
            } else {
                int remaining = MAX_ATTEMPTS - attempts;
                if (remaining > 0) {
                    System.out.println("Invalid PIN! " + remaining + " attempt(s) remaining.");
                } else {
                    System.out.println("======== Card Blocked! Too many wrong attempts. ========");
                }
            }
        }

        if (pinCorrect) {
            char ch;
            do {
                System.out.println("\n---------- HDFC ATM Menu ----------");
                System.out.println("Press 1 : Check Balance");
                System.out.println("Press 2 : Withdraw Amount");
                System.out.println("Press 3 : Deposit Amount");
                System.out.println("Press 4 : Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: Rs. " + balance);
                        break;

                    case 2:
                        System.out.print("Enter Withdraw Amount: Rs. ");
                        long w = sc.nextLong(); // Bug Fix 2: was sc.nextInt()
                        if (w <= 0) {
                            System.out.println("Invalid amount. Please enter a positive value.");
                        } else if (w <= balance) {
                            balance -= w;
                            System.out.println("Amount Successfully Withdrawn: Rs. " + w);
                            System.out.println("Remaining Balance: Rs. " + balance);
                        } else {
                            System.out.println("Insufficient Balance! Available: Rs. " + balance);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Deposit Amount: Rs. ");
                        long deposit = sc.nextLong();
                        if (deposit <= 0) {
                            System.out.println("Invalid amount. Please enter a positive value.");
                        } else {
                            balance += deposit; // Bug Fix 1: was dbalance += balance (wrong logic)
                            System.out.println("Amount Successfully Deposited: Rs. " + deposit);
                            System.out.println("Updated Balance: Rs. " + balance);
                        }
                        break;

                    case 4:
                        System.out.println("Thank you for visiting HDFC Bank. Goodbye!");
                        sc.close();
                        return; // Bug Fix 4: exit immediately, don't ask "Continue?"

                    default:
                        System.out.println("Invalid choice! Please select 1-4.");
                }

                System.out.print("\nDo you want to continue? (Y/N): ");
                ch = sc.next().charAt(0);

            } while (ch == 'Y' || ch == 'y');

            System.out.println("Session ended. Thank you for using HDFC ATM!");
        }

        sc.close();
    }
}
