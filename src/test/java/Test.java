import org.example.service.ConvertService;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class Test {
    private final ConvertService convertService = new ConvertService();

    private final String[][] testNumber = { {"1", "один"},
            {"386", "триста восемьдесят шесть"}, {"1000", "одна тысяча"}, {"1502", "одна тысяча пятьсот два"},
            {"9861", "девять тысяч восемьсот шестьдесят один"}};

    @org.junit.Test
    public void testConverTo20() throws IOException {
        String[] nameNum = new String[]{"ноль", "один", "два", "три", "четыре",
                "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать",
                "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};



        for (int i = 0; i < 20; i++) {
            assertEquals(" ОШибка", nameNum[i],
                    convertService.convertToString(new BigInteger(String.valueOf(i))));
        }
    }


    @org.junit.Test
    public void testConvert() throws Exception {

        String[] nameNum = new String[]{"пятьсот шестьдесят восемь", "двадцать два", "девяносто восемь", "семьсот семьдесят пять"};
        int numbers[] = new int[]{568, 22, 98, 775};



        for (int i = 0; i < 4; i++) {
            assertEquals(" Ошибка", nameNum[i],
                    convertService.convertToString(new BigInteger(String.valueOf(numbers[i]))));
        }
    }


}
