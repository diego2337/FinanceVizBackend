package com.financeviz.FinanceVizBackend.presentation

import com.financeviz.FinanceVizBackend.application.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/verify-token")
    fun verifyToken(@RequestBody request: VerifyTokenRequest): ResponseEntity<AuthResponse> {
        val result = authService.verifyToken(request.token)
        return if (result.success) {
            ResponseEntity.ok(AuthResponse(result.uid, result.message))
        } else {
            ResponseEntity.badRequest().body(AuthResponse(null, result.message))
        }
    }
}

data class VerifyTokenRequest(val token: String)
data class AuthResponse(val uid: String?, val message: String)
