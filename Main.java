import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "EXPENSE TRACKING SYSTEM";

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"WELCOME TO EXPENSE TRACKING SYSTEM");
        Exts exts = getExts();
        exts = getMainMenu(exts);
        if (!setExts(exts)) {
            System.out.println("ERROR: An error occurred when saving the file");
        }
    }

    private static Exts getExts() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(FILE_NAME)));
            Exts exts = (Exts) objectInputStream.readObject();
            objectInputStream.close();
            return exts;
        } catch (Exception e) {
            return new Exts();
        }
    }

    private static Exts getMainMenu(Exts exts) {
        int menu = Integer.parseInt(JOptionPane.showInputDialog("EXPENSE TRACKING MENU\n| 1 | DAILY & MONTHLY TRACKING\n| 2 | MONEY SAVER \n| 3 | SAVE & QUIT"));
        
        switch (menu) {
            case 0:
                JOptionPane.showMessageDialog(null,"Saving data to disk...\nThank you for using Exts");
                return exts;
            case 1:
             
                int selected1 = Integer.parseInt(JOptionPane.showInputDialog("DAILY & MONTHLY TRACKING\n| 1 | Enter expense\n| 2 | Set daily expense limit\n| 3 | Set monthly expense limit\n| 4 | View expenses"));

                switch (selected1) {
                    case 1:
                  
                        String amount=JOptionPane.showInputDialog("Please enter expense amount:");
        
                        try {
                            exts.getExpenses().add(new Expense(new Date(), Double.parseDouble(amount)));
                            JOptionPane.showMessageDialog(null,"Daily expense successfully recorded");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,"WARNING: Invalid value");
                        }
                        break;
                    case 2:
                        System.out.println("Please enter your daily expense limit:");
        
                        try {
                            exts.setDailyLimit(new Scanner(System.in).nextDouble());
                            JOptionPane.showMessageDialog(null,"Daily expense limit set successfully");
                        } catch (InputMismatchException e) {
                            JOptionPane.showMessageDialog(null,"WARNING: Invalid value");
                        }
                        break;
                    case 3:
                        System.out.println("Please enter your monthly expense limit:");
                        
                        try {
                            exts.setMonthlyLimit(new Scanner(System.in).nextDouble());
                            JOptionPane.showMessageDialog(null,"Monthly expense limit set successfully");
                        } catch (InputMismatchException e) {
                            JOptionPane.showMessageDialog(null,"WARNING: Invalid value");
                        }
                        break;
                    case 4:
                        double today = .0;
                        double month = .0;
                        Date now = new Date();

                        if (!exts.getExpenses().isEmpty()) {
                            for (int i = 0; i < exts.getExpenses().size(); i++) {
                                System.out.println("Date: " + exts.getExpenses().get(i).getDate().toString());
                                System.out.println("Amount: RM" + exts.getExpenses().get(i).getAmount());
                                System.out.println("===========================");
        
                                if (now.getMonth() == exts.getExpenses().get(i).getDate().getMonth()) {
                                    month += exts.getExpenses().get(i).getAmount();
        
                                    if (now.getDate() == exts.getExpenses().get(i).getDate().getDate()) {
                                        today += exts.getExpenses().get(i).getAmount();
                                    }
                                }
                            }
                            System.out.println();
                        }
        
                        System.out.println("Daily limit: RM" + exts.getDailyLimit());
                        System.out.println("Daily spending: RM" + today);
                        System.out.println("Monthly limit: RM" + exts.getMonthlyLimit());
                        System.out.println("Monthly spending: RM" + month);
                        break;}
                break;
            case 2 :
                int selected2 = Integer.parseInt(JOptionPane.showInputDialog("MONEY SAVER\n| 5 | Set money saver\n| 6 | Add money to money saver\n| 7 | View savings"));
                
                switch (selected2) {                    
                    case 5:
                        String description=JOptionPane.showInputDialog("Please enter savings name:");
                        String savingsTarget=JOptionPane.showInputDialog("Please enter savings target:");
                        String startDate=JOptionPane.showInputDialog("Please enter start date (dd/MM/yyyy):");
                        String endDate=JOptionPane.showInputDialog("Please enter end date (dd/MM/yyyy):");

                        try {
                            exts.setRinggitSaver(new RinggitSaver(description, Double.parseDouble(savingsTarget), new SimpleDateFormat("dd/MM/yyyy").parse(startDate), new SimpleDateFormat("dd/MM/yyyy").parse(endDate)));
                            JOptionPane.showMessageDialog(null,"Saver " + exts.getRinggitSaver().getDescription() + " is set");
                        } catch (Exception e) {
                            System.out.println("ERROR: Invalid values");
                        }
                        break;
                    case 6:
                        if (exts.getRinggitSaver() != null) {
                            System.out.println("Please enter your amount to add to " + exts.getRinggitSaver().getDescription());
        
                            try {
                                exts.getRinggitSaver().setCurrentSaving(exts.getRinggitSaver().getCurrentSaving() + new Scanner(System.in).nextDouble());
                                System.out.println("New amount: RM" + exts.getRinggitSaver().getCurrentSaving());
                                System.out.println("Target: RM" + exts.getRinggitSaver().getSavingTarget());
                            } catch (InputMismatchException e) {
                                System.out.println("WARNING: Invalid value");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"Ringgit saver is not set");
                        }
                        break;
                    case 7:
                        if (exts.getRinggitSaver() != null) {
                            System.out.println(exts.getRinggitSaver().getDescription());
                            System.out.println("Start date: " + exts.getRinggitSaver().getStart().toString());
                            System.out.println("End date: " + exts.getRinggitSaver().getEnd().toString());
                            System.out.println("Savings total: RM" + exts.getRinggitSaver().getCurrentSaving());
                            System.out.println("Target: RM" + exts.getRinggitSaver().getSavingTarget());
                        } else {
                            System.out.println("Ringgit saver is not set");
                        }
                        break;}
                break;
            case 3: 
                JOptionPane.showMessageDialog(null,"Saving data to disk...\nThank you for using EXPENSE TRACKING SYSTEM");
                return exts;
        }
        setExts(exts);
        return getMainMenu(exts);
    }

    private static int getSelected1() {
        int selected1 = -1;
        try {
            selected1 = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("WARNING: Invalid value, please try again");
            getSelected1();
        }
        return selected1;
    }
    private static int getSelected2() {
        int selected2 = -1;
        try {
            selected2 = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("WARNING: Invalid value, please try again");
            getSelected2();
        }
        return selected2;
    }
    private static boolean setExts(Exts exts) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(exts);
            fileOutputStream.close();
            return true;} 
        catch (IOException e) {
            return false;
        }
    }
}
