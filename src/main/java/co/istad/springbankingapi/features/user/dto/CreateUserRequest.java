package co.istad.springbankingapi.features.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUserRequest(
        @Email(message = "Invalid email")
        @NotBlank(message = "This field is required")
        String email,
        @Size(max = 6 , message = "Pin must be 6 digit")
        @NotBlank(message = "This field is required")
        String pin,
        @NotBlank(message = "This field is required")
        String password,
        @NotBlank(message = "This field is required")
        String confirmPassword,
        @NotBlank(message = "This field is required")
        String nationalCardId,

        @NotBlank(message = "This field is required")
                @Size(max = 100, message = "Name must be less than 100 character")
        String name,

        @NotNull(message = "This field is required")
        LocalDate dob,
        @NotBlank(message = "This field is required")
        String gender,
        @NotBlank(message = "This field is required")
        String phoneNumber,
        @NotBlank(message = "This field is required")
        String profileImage,
        @NotBlank(message = "This field is required")
        String studentCardId,
        @NotBlank(message = "This field is required")
        String position,
        @NotBlank(message = "This field is required")
        String companyName,
        @NotBlank(message = "This field is required")
        String employeeType,
        @NotBlank(message = "This field is required")
        String cityOrProvince,
        @NotBlank(message = "This field is required")
        String khanOrDistrict,
        @NotBlank(message = "This field is required")
        String songKatOrCommune,
        @NotBlank(message = "This field is required")
        String street,
        @NotBlank(message = "This field is required")
        String village,
        @NotBlank(message = "This field is required")
        String monthlyIncomeRange,
        @NotBlank(message = "This field is required")
        String mainSrcIncome

) {
}
