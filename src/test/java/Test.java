import org.apache.poi.ss.usermodel.*;
import org.example.service.ConvertService;

import java.io.File;
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
    public void testConvertDigits() throws Exception {
        for (String[] digit : testNumber) {
            assertEquals(digit[1], convertService.convertToString(new BigInteger(digit[0])));

        }
    }

    @org.junit.Test
    public void testConvertDigitsMoreThanThousand() throws Exception {

        String[] nameNumbers = new String[]{ "десять тысяч восемьсот двадцать пять", "тринадцать тысяч восемьсот десять", "сто тысяч один",
                 "семьдесят восемь тысяч триста шесть", "одиннадцать тысяч четыреста девяносто", "двенадцать тысяч пятьсот семьдесят",
                 "семьдесят пять тысяч двести десять"};
        int numbers[] = new int[]{10825, 13810, 100001, 78306, 11490,12570, 75210};
        for (int i = 0; i < numbers.length; i++) {
            assertEquals("Ошибка в числе: ", nameNumbers[i],
                   convertService.convertToString(new BigInteger(String.valueOf(numbers[i]))));
        }
    }

    @org.junit.Test
    public void testConvert() throws Exception {
        String[] nameNum = new String[]{"пятьсот шестьдесят восемь", "двадцать два", "девяносто восемь", "семьсот семьдесят пять"};
        int numbers[] = new int[]{568, 22, 98, 775};
        for (int i = 0; i < numbers.length; i++) {
            assertEquals(" Ошибка", nameNum[i],
                    convertService.convertToString(new BigInteger(String.valueOf(numbers[i]))));
        }
    }

    @org.junit.Test
    public void testDDTExel() throws Exception {
        long num = 0;
        String str="";
        Workbook wb = WorkbookFactory.create(new File("D:\\testWork\\src\\main\\java\\org\\example\\recourses\\test.xlsx"));
      //  Sheet s = wb.getSheetAt(0);
    //    Row r = s.getRow(0);
    //    System.out.println("Cell A1 is " + r.getCell(0));

        Sheet sheet   = wb.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case NUMERIC:
                        num = (long) cell.getNumericCellValue();
                        break;
                    case STRING:
                        str = cell.getStringCellValue();
                        break;
                    default:
                        break;
                }
            }
            assertEquals("Ошибка в числе: " + num, str, convertService.convertToString(new BigInteger(String.valueOf(num))));
        }
    }



    @org.junit.Test
    public void testConvertCheckEnding() throws Exception {

        String[] nameNum = new String[]{"пять тысяч","одна тысяча", "три тысячи", "пять миллионов", "один миллион", "три миллиона"};
       int numbers[] = new int[]{5000, 1000, 3000, 5000000, 1000000, 3000000};


        for (int i = 0; i < numbers.length; i++) {
            assertEquals("Ошибка в числе: ", nameNum[i],
                   convertService.convertToString(new BigInteger(String.valueOf(numbers[i]))));
        }
    }

}
