package _2020;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 2019年累计扣税计算
 * @author CYH
 * @date 2020-02-08
 */
public class PersonalYearlyTaxCalculation {

    public static void main(String[] args) throws Exception {
        new PersonalYearlyTaxCalculation().calculate();
    }

    private void calculate() throws Exception {
        // 月收入
        List<String> allLines = Files.readAllLines(Paths.get("D:\\txt\\3.txt"), StandardCharsets.UTF_8);
        double sumSalary = 0d;
        double sumRate = 0d;
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i).trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            double monthSalary = Double.parseDouble(line);
            sumSalary += monthSalary > 5000 ? monthSalary - 5000 : monthSalary;
            Pair pair = getTaxRate(sumSalary);
            double totalTax = sumSalary * pair.rate - pair.minus;
            double curMonthTax = totalTax - sumRate;
            System.out.printf("%.2f %.2f totalTax=%.2f sumSalary=%.2f\n", curMonthTax, monthSalary - curMonthTax,
                    totalTax, sumSalary);
            sumRate = totalTax;
        }
    }

    private Pair getTaxRate(double x) {
        if (x <= 36000) {
            return new Pair(0.03, 0);
        } else if (x < 144000) {
            return new Pair(0.1, 2520);
        } else if (x < 300000) {
            return new Pair(0.2, 16920);
        }
        throw new RuntimeException("too large value: " + x);
    }

    private static class Pair {
        double rate;
        double minus;

        public Pair(double rate, double minus) {
            this.rate = rate;
            this.minus = minus;
        }
    }

}
