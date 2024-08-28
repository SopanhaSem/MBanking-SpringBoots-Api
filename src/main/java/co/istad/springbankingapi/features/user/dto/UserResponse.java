package co.istad.springbankingapi.features.user.dto;

import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String email,
        String nationalCardId,
        String name,
        LocalDate dob,
        String gender,
        String phoneNumber,
        String profileImage,
        String studentCardId,
         String position,
        String companyName,
        String employeeType,
        String cityOrProvince,
        String khanOrDistrict,
        String  songKatOrCommune,
        String street,
        String village,
        String monthlyIncomeRange,
        String mainSrcIncome
) {
}
