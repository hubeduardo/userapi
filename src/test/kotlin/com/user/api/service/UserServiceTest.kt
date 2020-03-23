package com.user.api.service

import com.user.api.domain.User
import com.user.api.entities.request.UpdateUserRequest
import com.user.api.repository.UserRepository
import java.util.Optional
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`doThrow`
import org.mockito.Mockito.`mock`
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import java.time.LocalDate

@RunWith(SpringRunner::class)
@SpringBootTest
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class)
class UserServiceTest {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    @MockBean
    private lateinit var userRepository: UserRepository

    private fun getUser(id : String): User {


        return User(id,
                "Nome",
                "LastName",
                "teste@gmail.com",
                "222.111.222-11",
                "",
                1,
                "teste",
                LocalDate.of(2002,1,2))
    }

    @Test
    fun test_update_user_ok() {
        val oldUser = getUser("w123")
        val newUser = UpdateUserRequest(id = oldUser.id!!, imageUrl = "")

        val responseFindById: Optional<User> = Optional.ofNullable(oldUser)
        `when`(userRepository.findById(newUser.id.toString())).thenReturn(responseFindById)

        val responseNewEvent = User().mergeDataUser(newUser, responseFindById.get())
        `when`(userRepository.save(responseNewEvent)).thenReturn(responseNewEvent)

        val expected: User? = userService.updateUser(newUser, "q11qq1q1q1q1").toFuture().get()

        Assert.assertEquals(true, expected?.name == oldUser.name)
        Assert.assertEquals(true, expected?.imageUrl == newUser.imageUrl)
    }

    @Test
    fun test_update_user_error() {
        val newUser = UpdateUserRequest(id = "5e77608ae8fe1146d83ec679",
                name = "teste",
                imageUrl = "")
        try {
            userService.updateUser(newUser, "q11qq1q1q1q1").toFuture().get()
        } catch (ex: Exception) {
            Assert.assertEquals("", ex.message)
        }
    }

    @Test
    fun test_save_user_ok() {
        val user = getUser("w123")

        `when`(userRepository.save(user)).thenReturn(user)
        val expected: User? = userService.saveUser(user).toFuture().get()

        Assert.assertEquals(user.id, expected?.id)
        Assert.assertEquals(user.name, expected?.name)
        Assert.assertEquals(user.lastName, expected?.lastName)
        Assert.assertEquals(user.email, expected?.email)
        Assert.assertEquals(user.CPF, expected?.CPF)
        Assert.assertEquals(user.imageUrl, expected?.imageUrl)
    }

    @Test
    fun test_remove_user_ok() {
        val user = getUser("w123")
        val responseFindById: Optional<User> = Optional.ofNullable(user)
        `when`(userRepository.findById(user.id.toString())).thenReturn(responseFindById)

        val mock: UserRepository = mock(UserRepository::class.java)
        doThrow(Exception::class.java).`when`(mock).delete(user)

        val expected = userService.removeUser(user, "q11qq1q1q1q1").toFuture().get()
        Assert.assertEquals(true, expected?.message == "Usuário removido com sucesso!")
    }

    @Test
    fun test_check_update_user() {
        val updateUserRequest = UpdateUserRequest(id = "w123", name = "name")
        val applicationUserId = "q11qq1q1q1q1"
        val userNew = getUser("newUser")
        val userOld = getUser("w123")

        val responseFindById: Optional<User> = Optional.ofNullable(userOld)
        `when`(userRepository.findById(updateUserRequest.id)).thenReturn(responseFindById)

        val user = User().mergeDataUser(updateUserRequest, userOld)

        `when`(userRepository.save(user)).thenReturn(userNew)
        val expected = userService.checkUpdateUser(updateUserRequest, applicationUserId).toFuture().get()

        Assert.assertEquals(true, expected.name == updateUserRequest.name)
    }
}
