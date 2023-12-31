package knu.ainterview.controller;

import knu.ainterview.controller.dto.*;
import knu.ainterview.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberSignUpDto memberSignUpDto) {
        return ResponseEntity.ok(authService.signup(memberSignUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<NicknameDto> login(@RequestBody MemberLoginDto memberLoginDto, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(memberLoginDto, response));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
