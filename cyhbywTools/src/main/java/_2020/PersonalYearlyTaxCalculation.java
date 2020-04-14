package _2020;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

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
        List<String> allLines = Files.readAllLines(Paths.get("D:\\txt\\2.txt"), StandardCharsets.UTF_8);
        double sumSalary = 0d;
        double sumRate = 0d;
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i).trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            line = line.split("\\s+")[0];
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

    @Data
    @AllArgsConstructor
    private static class SalaryData {
        /** 总月薪 */
        double totalMonthSalary;
        /** 公积金 */
        double accumulationFund;
        /** 社保 */
        double socialSecurity;
        /** 专项扣除 */
        double specialDeduction;
        /** 实际缴纳的公积金与免税公积金的差值 */
        double toMinusFundGap;
    }

    @AllArgsConstructor
    private static class Pair {
        double rate;
        double minus;
    }

}


/**

# 2019. 01-08/09 贝壳
10288.87 	158.67 	    10070.75
15283.12 	308.49 	    14920.93
15110.87 	303.33 	    14748.09
15036.31 	301.09 	    14677.69
15335.98 	1013.94 	14262.59
18391.47 	1339.14 	16994.80
15178.83 	1017.89 	14101.49
15178.83    1017.89     14101.50
1817		181.70	  	1636.36
# 2019. 09-12 Oppo
# 社保1682.62 + 免税公积金879 == 2561.62
14105.66 16667.28    273.17      13433.49  从0开始的
24766.27 27327.27    592.97      23791.68
19038.38 21600.00    904.83      17678.05
18561.11 21122.73    406.83      17773.28  是从0开始的，没有和前面三个月进行累计计税

*/
