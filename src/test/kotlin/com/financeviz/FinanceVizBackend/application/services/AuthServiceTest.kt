import com.financeviz.FinanceVizBackend.application.services.AuthService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class AuthServiceTest {

    private val firebaseAuth = mock(FirebaseAuth::class.java)
    private val authService = AuthService(firebaseAuth)

    @Test
    fun `verifyToken should return success when token is valid`() {
        val mockToken = mock(FirebaseToken::class.java)
        `when`(mockToken.uid).thenReturn("test-uid")
        `when`(firebaseAuth.verifyIdToken("valid-token")).thenReturn(mockToken)

        val result = authService.verifyToken("valid-token")

        assertTrue(result.success)
        assertEquals("test-uid", result.uid)
    }

    @Test
    fun `verifyToken should return failure when token is invalid`() {
        `when`(firebaseAuth.verifyIdToken("invalid-token")).thenThrow(RuntimeException("Invalid token"))

        val result = authService.verifyToken("invalid-token")

        assertFalse(result.success)
        assertEquals("Invalid token: Invalid token", result.message)
    }
}
