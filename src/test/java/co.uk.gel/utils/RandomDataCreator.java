package co.uk.gel.utils;

import com.github.javafaker.Faker;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class RandomDataCreator {

    private static final Logger log = LoggerFactory.getLogger(RandomDataCreator.class);
    static Faker fake = new Faker();


    public static String getRandomAplhabetsOfGivenSize(int size) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        String randomWord = "Test" + generator.generate(size);
        return randomWord;
    }

    public static String getRandomNumberOfGivenSize(int size) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('1', '9').build();
        String randomNumbers = generator.generate(size);
        return randomNumbers;
    }


    public static String getRandomPrefix() {
        String[] allPrefix = {"Mr", "Mrs", "Miss", "Master", "Ms"};
        int generator = RandomDataCreator.getRandomNumberRange(0, allPrefix.length - 1);
        String prefix = allPrefix[generator];
        return prefix;
    }


    public static String getRandomUKPostCode() {
        String[] allPostcode = {"AB1 0AB", "AL10 0QQ", "B1 1TA", "B99 1DY", "CA1 1AA", "CA28 6AQ", "DA1 1AQ", "DA1 1AQ",
                "E1 0SF", "E20 3PS"};
        int generator = RandomDataCreator.getRandomNumberRange(0, allPostcode.length - 1);
        //int index = Integer.parseInt(generator.generate(1));
        String postCode = allPostcode[generator];
        return postCode;
    }

    public static int getRandomNumberRange(int lowerLimit, int upperLimit) {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(upperLimit) + lowerLimit;
        //System.out.println("Random number generated is : " + randomInt);
        return randomInt;
    }


    public static void main(String[] args) {


        log.info(RandomDataCreator.getRandomAplhabetsOfGivenSize(10));
        log.info(RandomDataCreator.getRandomNumberOfGivenSize(11));
        log.info(RandomDataCreator.getRandomPrefix());
        System.out.println(RandomDataCreator.getRandomNumberRange(3, 125));
        log.info(RandomDataCreator.generateRandomNHSNumber());
    }


    public static String currentDate(int days) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        //String requiredDate = df.format(new Date((System.currentTimeMillis()-315569260000l))).toString();
        //System.out.println(requiredDate);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return df.format(cal.getTime());
    }


    public static String generateRandomNHSNumber() {

        boolean flagOfNHSNUmberValidity = false;
        String generatedNHSNumber = "";
        do {
            generatedNHSNumber = String.valueOf(Long.parseLong(RandomDataCreator.getRandomNumberOfGivenSize(10)));
            flagOfNHSNUmberValidity = checkForValidityOfNHSNumber(generatedNHSNumber);

        } while (!flagOfNHSNUmberValidity);

        System.out.println("Generated Valid NHSNumber " + generatedNHSNumber);
        return generatedNHSNumber;
    }

    public static boolean checkForValidityOfNHSNumber(String baseNHSNumber) {

        List<Long> digitOfNHS = new ArrayList<Long>();
        Long baseNHSNumberL = Long.parseLong(baseNHSNumber.replaceAll("\\s+", ""));
        for (int i = 0; i < 10; i++) {
            digitOfNHS.add(baseNHSNumberL % 10);
            baseNHSNumberL = baseNHSNumberL / 10;
        }
        Collections.reverse(digitOfNHS);
        Long sumOfdigitOfNHS = 0L;
        for (int i = 0, j = 10; i < 9; i++) {
            sumOfdigitOfNHS = sumOfdigitOfNHS + digitOfNHS.get(i) * (j - i);
        }
        Long tenthDigit = digitOfNHS.get(9) * 1;

        Long reminderOfElevenNum = sumOfdigitOfNHS % 11;
        Long substractReminderByEleven = 11 - reminderOfElevenNum;
        if (substractReminderByEleven == 10) {
            return false;
        }
        if (substractReminderByEleven == 11) {
            if (tenthDigit == 0) {
                return true;
            } else {
                return false;
            }
        }
        if (substractReminderByEleven == tenthDigit) {
            return true;
        } else {
            return false;
        }
    }


    public static String getRandomFirstName() {
        return fake.name().firstName();
    }

    public static String getRandomLastName() {
        return fake.name().lastName();
    }

    public static String getRandomPhoneNumber() {
        return fake.number().digits(10);
    }

    public static String getRandomEmailAddress() {
        return fake.internet().emailAddress();
    }

    public static String getRandomAddress() {
        return fake.address().streetAddress();
    }

    public static String getRandomProfessionalRegistrationNumber() {
        return fake.number().digits(12);
    }

}
