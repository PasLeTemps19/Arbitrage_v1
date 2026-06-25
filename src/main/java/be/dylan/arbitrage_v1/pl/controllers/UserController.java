package be.dylan.arbitrage_v1.pl.controllers;

import be.dylan.arbitrage_v1.bll.mappers.UserMapper;
import be.dylan.arbitrage_v1.bll.services.user.UserService;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.pl.dtos.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserIndexDto>> findAll() {
        List<UserIndexDto> users = userService.getAllUsers()
                .stream()
                .map(UserMapper::convertToUserIndexDto)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsUserViewDto> findById(@PathVariable Long id) {
        UserDetailsUserViewDto user = UserMapper.convertToUserDetailsUserViewDto(userService.getByIdUser(id));
        return ResponseEntity.ok(user);

    }

    @PostMapping("/create")
    public ResponseEntity<UserDetailsUserViewDto> create(@RequestBody @Valid UserCreateFormDto userCreateFormDto) {
        User userCreate = userService.addUser(userCreateFormDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.convertToUserDetailsUserViewDto(userCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsUserViewDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateFormDto userUpdateFormDto) {
        User userUpdate = userService.updateUser(id, userUpdateFormDto);
        return ResponseEntity.ok(UserMapper.convertToUserDetailsUserViewDto(userUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/invite")
    public ResponseEntity<Void> invite(@RequestBody @Valid UserInviteFormDto userInviteFormDto) {
        userService.inviteUser(userInviteFormDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/register/{token}")
    public ResponseEntity<Void> register(@PathVariable String token) {
        userService.registerUser(token);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:8081/realms/Arbitrage/protocol/openid-connect/registrations?client_id=spring-app-arbitrage&response_type=code"))
                .build();
    }

    @PostMapping("/complete-profile")
    public ResponseEntity<Void> completeProfile(
            @RequestBody @Valid UserCompleteProfileFormDto dto,
            Authentication authentication) {
        userService.completeProfile(dto, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsUserViewDto> getMe(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaim("email");
        User user = userService.getMe(email);
        return ResponseEntity.ok(UserMapper.convertToUserDetailsUserViewDto(user));
    }

    @PatchMapping("/{id}/toggle-active")
    public ResponseEntity<Void> toggleActive(@PathVariable Long id) {
        userService.toggleActive(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/resend-invitation")
    public ResponseEntity<Void> resendInvitation(@PathVariable Long id) {
        userService.resendInvitation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/external")
    public ResponseEntity<UserDetailsUserViewDto> createExternal(@RequestBody @Valid UserExternalCreateFormDto dto) {
        User user = userService.createExternalUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.convertToUserDetailsUserViewDto(user));
    }

}
