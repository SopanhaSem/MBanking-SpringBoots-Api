package co.istad.springbankingapi.features.user;

import co.istad.springbankingapi.features.user.dto.CreateUserRequest;
import co.istad.springbankingapi.features.user.dto.UserResponse;
import co.istad.springbankingapi.features.user.dto.VerifyRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/verify")
    void verify(@Valid @RequestBody VerifyRequest verifyRequest){
        userService.verify(verifyRequest);

    }



    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws MessagingException {
        userService.createUser(createUserRequest);
    }

    @RequestMapping
    List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping(params = "phoneNumber")
    UserResponse findByPhoneNumber(@RequestParam String phoneNumber){
        return userService.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/{uuid}")
    UserResponse findByUuid(@PathVariable String uuid){
        return userService.findByUuid(uuid);
    }


}
