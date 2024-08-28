    package co.istad.springbankingapi.mapper;

    import co.istad.springbankingapi.domain.User;
    import co.istad.springbankingapi.features.user.dto.CreateUserRequest;
    import co.istad.springbankingapi.features.user.dto.UserResponse;
    import org.mapstruct.Mapper;

    import java.util.List;

    @Mapper(componentModel = "spring")
    public interface UserMapper {

        User fromCreateUserMapper(CreateUserRequest createUserRequest);

        List<UserResponse> toUserResponseMapper(List<User> users);

        UserResponse toUserResponse(User user);
    }
