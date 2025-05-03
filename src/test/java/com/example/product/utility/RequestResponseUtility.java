package com.example.product.utility;

import com.example.product.model.request.AddOrEditProductRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import static com.example.product.constant.TestConstant.MAX_PRICE_LENGTH;
import static com.example.product.constant.TestConstant.MAX_QUANTITY;

@UtilityClass
public class RequestResponseUtility {

    public static AddOrEditProductRequest createAddOrEditProductRequest() {
        return AddOrEditProductRequest.builder()
                .name(generateRandomString(55))
                .description(generateRandomString(500))
                .price(generateRandomDouble(2))
                .quantity(RandomUtils.secure().randomLong(0, MAX_QUANTITY))
                .build();
    }


    public static String generateRandomString(int integerLength) {
        return RandomStringUtils.randomAlphabetic(integerLength);
    }

    public static String generateRandomEmail(int integerLength) {
        String randomString = RandomStringUtils.randomAlphabetic(integerLength);
        return randomString.concat("@yopmail.com");
    }

    private static String generateRandomNumericString(int integerLength, int fractionLength) {
        return RandomStringUtils.randomNumeric(integerLength)
                .concat(".")
                .concat(RandomStringUtils.randomNumeric(fractionLength));
    }

    private static Double generateRandomDouble(int fractionLength) {
        return Double.parseDouble(generateRandomNumericString(MAX_PRICE_LENGTH, fractionLength));
    }
}
