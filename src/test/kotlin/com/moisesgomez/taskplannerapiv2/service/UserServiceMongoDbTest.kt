package com.moisesgomez.taskplannerapiv2.service

import com.moisesgomez.taskplannerapiv2.controller.dto.UserDto
import com.moisesgomez.taskplannerapiv2.data.document.User
import com.moisesgomez.taskplannerapiv2.data.repository.UserRepository
import com.moisesgomez.taskplannerapiv2.exception.UserNotFoundException
import com.moisesgomez.taskplannerapiv2.services.users.UserServiceMongoDb
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceMongoDbTest {

    @Mock
    lateinit var userRepository: UserRepository

    lateinit var userServiceMongoDb: UserServiceMongoDb

    @BeforeAll
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userServiceMongoDb = UserServiceMongoDb(userRepository)
    }

    @Test
    fun saveUsersCallsSavesOnRepositoryTest() {
        val userDto = UserDto("1", "Full Name", "mail@mail.com", "123456")
        `when`(userRepository.save(any())).thenReturn(User(userDto))
        userServiceMongoDb.save(userDto)
        verify(userRepository)!!.save(any())
    }

    @Test
    fun updateNotFoundUserTest() {
        val userId = "123"
        val userDto = UserDto(userId, "Full Name", "mail@mail.com", "123456")
        val result: Optional<User> = Optional.empty()
        `when`(userRepository.findById(userId)).thenReturn(result)
        Assertions.assertThrows(UserNotFoundException::class.java) {
            userServiceMongoDb.update(userId, userDto)
        }
    }

    // Comment for Heroku integration
    @Test
    fun updateFoundUserIsSavedTest() {
        val userId = "1234"
        val userDto = UserDto(userId, "Full Name", "mail@mail.com", "123456")
        val user = User(userDto)
        val result: Optional<User> = Optional.of(User(userDto))
        `when`(userRepository.findById(userId)).thenReturn(result)
        `when`(userRepository.save(any())).thenReturn(user)
        userServiceMongoDb.update(userId, userDto)
        verify(userRepository).save(any())
    }
}