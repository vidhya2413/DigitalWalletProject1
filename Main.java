package wallet;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Wallet> wallets = new ArrayList<>();
    static int txnCounter = 1;
    static WalletService service = new WalletService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== DIGITAL WALLET SYSTEM =====");
            System.out.println("1. Add User");
            System.out.println("2. Create Wallet");
            System.out.println("3. Top-up");
            System.out.println("4. Transfer");
            System.out.println("5. Pay Merchant");
            System.out.println("6. Refund");
            System.out.println("7. View Statement");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter User ID: "); String id = sc.nextLine();
                        System.out.print("Enter Name: "); String name = sc.nextLine();
                        service.addUser(users, id, name); break;
                    case 2:
                        System.out.print("Enter User ID: "); service.createWallet(users, wallets, sc.nextLine()); break;
                    case 3:
                        System.out.print("Enter User ID: "); String tid = sc.nextLine();
                        System.out.print("Enter Amount: "); double tAmt = Double.parseDouble(sc.nextLine());
                        service.topUp(wallets, tid, tAmt, txnCounter++); break;
                    case 4:
                        System.out.print("From User ID: "); String from = sc.nextLine();
                        System.out.print("To User ID: "); String to = sc.nextLine();
                        System.out.print("Enter Amount: "); double trAmt = Double.parseDouble(sc.nextLine());
                        service.transfer(wallets, from, to, trAmt, txnCounter++); break;
                    case 5:
                        System.out.print("Enter User ID: "); String pid = sc.nextLine();
                        System.out.print("Enter Amount: "); double pAmt = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter Merchant: "); String merchant = sc.nextLine();
                        service.payMerchant(wallets, pid, pAmt, merchant, txnCounter++); break;
                    case 6:
                        System.out.print("Enter User ID: "); String rid = sc.nextLine();
                        System.out.print("Enter Amount: "); double rAmt = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter Original Payment ID: "); String orig = sc.nextLine();
                        service.refund(wallets, rid, rAmt, orig, txnCounter++); break;
                    case 7:
                        System.out.print("Enter User ID: "); service.printStatement(wallets, sc.nextLine()); break;
                    case 8:
                        System.out.println("Exiting..."); return;
                    default: System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}