//Pattrayus Chokbunlue 6313179
//Thanatorn Ruangrote 6313129
//Sorawit Kuha 6313219

import java.io.*;
import java.text.*;
import java.util.*;

class OverTime {
    //Variables
    private ArrayList<List> list;
    
    //Constructor
    public OverTime(String infile) {
        Scanner keyboardScan = new Scanner(System.in);
        boolean opensuccess = false;
        while (!opensuccess) {
            try (Scanner scans = new Scanner(new File(infile));) {
                opensuccess = true;
                list = new ArrayList<>();
            
                while (scans.hasNext()) {
                    try {
                        String line = scans.nextLine();
                        String[] buf = line.split(",");
                        ArrayList<String> list = new ArrayList<>();
                        for (int i=1; i<buf.length; i++) {
                            if (!list.contains(buf[i].trim())) {
                                list.add(buf[i].trim());
                            }
                        }
                        this.list.add(list);
                    }
                    catch (Exception e) {
                        System.err.println("An error occurs. End program.");
                        System.exit(-1);
                    }
                }
            }
            catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("New overtime file = ");
                infile = keyboardScan.next();
            }
        }
    }
    
    //Functions-Get
    public ArrayList<List> getList() { return list; }
}

class Product implements Comparable<Product>{
    //Variables
    private ArrayList<Product> list;
    
    private String name;
    private int price;
    private int totalSalesUnit;
    private int totalSalesBaht;
    private int bonusPerUnit;
    
    //Constructor
    public Product(String infile) {
        Scanner keyboardScan = new Scanner(System.in);
        boolean opensuccess = false;
        while (!opensuccess) {
            try (Scanner scans = new Scanner(new File(infile));) {
                opensuccess = true;
                list = new ArrayList<>();
            
                while (scans.hasNext()) {
                    try {
                        String line = scans.nextLine();
                        String[] buf = line.split(",");
                        name = buf[0].trim();
                        price = Integer.parseInt(buf[1].trim());
                        list.add(new Product(name, price));
                    }
                    catch (Exception e) {
                        System.err.println("An error occurs. End program.");
                        System.exit(-1);
                    }
                }
            }
            catch (FileNotFoundException e) {
                System.out.println(e);  
                System.out.println("New product file = ");
                infile = keyboardScan.next();
            }
        }
    }
    
    //Compare
    public int compareTo(Product other) {
        int compare = ((Product)other).getTotalSalesBaht();
        return compare-this.totalSalesBaht;
    }
    
    //No access from outside
    private Product(String name, int price) { this.name = name; this.price = price; }
    
    //Functions-Set
    public void setBonusPerUnit(int price) {
        if (price < 10000) {
            bonusPerUnit = (price * 1)/100;
        }
        else if (price >= 10000 && price < 30000) {
            bonusPerUnit = (price * 15)/1000;
        }
        else if (price >= 30000 & price < 50000) {
            bonusPerUnit = (price * 2)/100;
        }
        else {
            bonusPerUnit = (price * 25)/1000;
        }
    }
    public void setTotalSalesUnit(int totalSalesUnit) { this.totalSalesUnit = totalSalesUnit; }
    public void setTotalSalesBaht(int totalSalesBaht) { this.totalSalesBaht = totalSalesBaht; }
    
    //Functions-Get
    public ArrayList<Product> getList() { return list; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getTotalSalesUnit() { return totalSalesUnit; }
    public int getTotalSalesBaht() { return totalSalesBaht; }
    public int getBonusPerUnit() { return bonusPerUnit; }
}

class Employee implements Comparable<Employee>{
    //Variables
    private ArrayList<Employee> list;
    
    private String name;
    private ArrayList<Integer> sales;
    private int salesBonus;
    private int overtimeBonus;
    private int totalBonus;
    private int totalSales;
    private int extraBonus;
    
    //Constructor
    public Employee(String infile) {
        Scanner keyboardScan = new Scanner(System.in);
        boolean opensuccess = false;
        while (!opensuccess) {
            try (Scanner scans = new Scanner(new File(infile));) {
                opensuccess = true;
                list = new ArrayList<>();
                String line = null;
            
                while (scans.hasNext()) {
                    try {
                        line = scans.nextLine();
                        String[] buf = line.split(",");
                        //Checking Error
                        if (buf.length != 6) {
                            throw new ArithmeticException("My Exception for Invalid colume" + line + "");
                        }
                        for (int i=1; i<6; i++) {
                            if (Integer.parseInt(buf[i].trim()) < 0) {
                                throw new ArithmeticException("My Exception for negative value " +  "\"" + Integer.parseInt(buf[i].trim()) + "\"");
                            }
                        }
                        sales = new ArrayList<>();
                        name = buf[0].trim();
                        for (int i=1; i<buf.length; i++) {
                            sales.add(Integer.parseInt(buf[i].trim()));
                        }
                        list.add(new Employee(name, sales));
                    }
                    catch (RuntimeException e) {
                        System.out.println(e);
                        System.out.println(line + "\r\n");
                    }
                }
            }
            catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("New employee file = ");
                infile = keyboardScan.next();
            }
        }
    }
    
    //Compare
    public int compareTo(Employee other) {
        int compare = ((Employee)other).getTotalSales();
        return compare-this.getTotalSales();
    }
    
    //No access from outside
    private Employee(String name, ArrayList<Integer> sales) { this.name = name; this.sales = sales; }
    
    //Functions-Set
    public void setSalesBonus(int salesBonus) { this.salesBonus = salesBonus; }
    public void setTotalSales(int totalSales) { this.totalSales = totalSales; }
    public void setExtraBonus(int extraBonus) { this.extraBonus = extraBonus; }
    public void setOvertimeBonus(int overtimeBonus) { this.overtimeBonus = overtimeBonus; }
    public void setTotalBonus(int totalBonus) { this.totalBonus = totalBonus; }
    
    //Functions-Get
    public ArrayList<Employee> getList() { return list; }
    public String getName() { return name; }
    public ArrayList<Integer> getSales() { return sales; }
    public int getSalesBonus() { return salesBonus; }
    public int getTotalSales() { return totalSales; }
    public int getExtraBonus() { return extraBonus; }
    public int getOvertimeBonus() { return overtimeBonus; }
    public int getTotalBonus() { return totalBonus; }
}

class Main {
    public static void main(String[] args) {
        String infile;
        Scanner keyboardScan = new Scanner(System.in);
        
        System.out.println("Enter product file = ");
        infile = keyboardScan.next();
        Product product = new Product(infile);
        
        product.getBonusPerUnit();
        
        System.out.println("Enter employee file = ");
        infile = keyboardScan.next();
        Employee employee = new Employee(infile);
        
        System.out.println("Enter overtime file = ");
        infile = keyboardScan.next();
        OverTime overtime = new OverTime(infile);
        
        for (int i=0; i<product.getList().size(); i++) {
            product.getList().get(i).setBonusPerUnit(product.getList().get(i).getPrice());
        }
        
        ArrayList<Integer> allTotalSales = new ArrayList<>();
        for (int i=0; i<employee.getList().size(); i++) {
            int totalSales = 0;
            int saleBonus = 0;
            int overtimeBonus = 0;
            for (int j=0; j<employee.getList().get(i).getSales().size(); j++) {
                totalSales += product.getList().get(j).getPrice() * employee.getList().get(i).getSales().get(j);
                product.getList().get(j).setTotalSalesUnit((product.getList().get(j).getTotalSalesUnit() + employee.getList().get(i).getSales().get(j)));
                product.getList().get(j).setTotalSalesBaht((product.getList().get(j).getTotalSalesBaht() + (product.getList().get(j).getPrice() * employee.getList().get(i).getSales().get(j))));
                
                saleBonus += product.getList().get(j).getBonusPerUnit() * employee.getList().get(i).getSales().get(j);
            }
            for (int j=0; j<overtime.getList().size(); j++) {
                for (int k=0; k<overtime.getList().get(j).size(); k++) {
                    if (employee.getList().get(i).getName().equals(overtime.getList().get(j).get(k))) {
                        overtimeBonus += 2000;
                    }
                }
            }
            employee.getList().get(i).setTotalSales(totalSales);
            employee.getList().get(i).setSalesBonus(saleBonus);
            employee.getList().get(i).setOvertimeBonus(overtimeBonus);
            allTotalSales.add(totalSales);
        }
        
        int max = Collections.max(allTotalSales);
        int n = 0;
        for (int obj : allTotalSales) {
            if (obj == max) {
                n++;
            }
        }
        for (int i=0; i<employee.getList().size(); i++) {
            if (employee.getList().get(i).getTotalSales() == max) {
                employee.getList().get(i).setExtraBonus((employee.getList().get(i).getTotalSales() * 5) / (1000 * n));
            }
            employee.getList().get(i).setTotalBonus((employee.getList().get(i).getSalesBonus() + employee.getList().get(i).getOvertimeBonus() + employee.getList().get(i).getExtraBonus()));
        }

        Collections.sort(product.getList());
        Collections.sort(employee.getList());
        
        //Print output
        DecimalFormat formatter = new DecimalFormat("#,###");
        System.out.println("=== Bonus Calculation ===");
        for (int i=0; i<employee.getList().size(); i++) {
            System.out.printf("%7s >> ", employee.getList().get(i).getName());
            for (int j=0; j<employee.getList().get(i).getSales().size(); j++) {
                System.out.printf("%s (%2d)   ", product.getList().get(j).getName(), employee.getList().get(i).getSales().get(j));
            }
            if (employee.getList().get(i).getExtraBonus() > 0) {
                System.out.printf("\r\n\t   total sales\t  = %10s   sales bonus = %10s   extra bonus = %10s\r\n\t   overtime bonus = %10s   total bonus = %10s\r\n\n", formatter.format(employee.getList().get(i).getTotalSales()), formatter.format(employee.getList().get(i).getSalesBonus()), formatter.format(employee.getList().get(i).getExtraBonus()), formatter.format(employee.getList().get(i).getOvertimeBonus()), formatter.format(employee.getList().get(i).getTotalBonus()));
            }
            else {
                System.out.printf("\r\n\t   total sales\t  = %10s   sales bonus = %10s\r\n\t   overtime bonus = %10s   total bonus = %10s\r\n\n", formatter.format(employee.getList().get(i).getTotalSales()), formatter.format(employee.getList().get(i).getSalesBonus()), formatter.format(employee.getList().get(i).getOvertimeBonus()), formatter.format(employee.getList().get(i).getTotalBonus()));
            }
        }
        System.out.println("=== Product summary ===");
        for (int i=0; i<product.getList().size(); i++) {
            System.out.printf("%-20s price = %10s (bonus = %10s)    total sales = %10s units    %10s bath\r\n", product.getList().get(i).getName(), formatter.format(product.getList().get(i).getPrice()), formatter.format(product.getList().get(i).getBonusPerUnit()), formatter.format(product.getList().get(i).getTotalSalesUnit()), formatter.format(product.getList().get(i).getTotalSalesBaht()));
        }
    }
}