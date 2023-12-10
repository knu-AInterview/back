package knu.ainterview.controller;

import knu.ainterview.controller.dto.*;
import knu.ainterview.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
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
    public ResponseEntity<TokenDto> login(@RequestBody MemberLoginDto memberLoginDto, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(memberLoginDto);
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", tokenDto.getRefreshToken())
                // 토큰의 유효 기간
                .maxAge(24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", refreshCookie.toString());

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", tokenDto.getAccessToken())
                .maxAge( 30 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(false)
                .build();
        response.setHeader("Set-Cookie", accessCookie.toString());
        return ResponseEntity.ok(authService.login(memberLoginDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
