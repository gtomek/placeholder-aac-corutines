package uk.co.tomek.jsonplaceholderdemoapp.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import uk.co.tomek.jsonplaceholderdemoapp.data.Repostitory
import uk.co.tomek.jsonplaceholderdemoapp.data.model.*
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel

@RunWith(MockitoJUnitRunner::class)
class MainInteractorTest {

    private lateinit var interactor: MainInteractor

    @Mock
    private lateinit var repostitory: Repostitory

    @Before
    fun setUp() {
        interactor = MainInteractor(repostitory)
    }

    @Test
    fun verifyForAnEmptyResponsesWeGetAnEmptyResult() {
        // given
        val commentsList = listOf<Comment>()
        val postsList = listOf<Post>()
        val usersList = listOf<User>()

        // when
        runBlocking { whenever(repostitory.fetchComments()) }.thenReturn(commentsList)
        runBlocking { whenever(repostitory.fetchPosts()) }.thenReturn(postsList)
        runBlocking { whenever(repostitory.fetchUsers()) }.thenReturn(usersList)
        val result = runBlocking { interactor.fetchAll() }

        // then
        assert(result.isEmpty())
    }

    @Test
    fun verifyForValidResponsesWeGetOneResult() {
        // given
        val id1 = 1
        val comment1 = Comment("commentBody", "comment@email.com", id1, "commentName", 1)
        val commentsList = listOf(comment1)
        val postBody = "postBody"
        val postTitle = "postTitle"
        val userId1 = 11
        val post1 = Post(postBody, id1, postTitle, userId1)
        val postsList = listOf(post1)
        val address = mock<Address>()
        val company = mock<Company>()
        val userName = "name"
        val user1 = User(address, company, "user@email.com", userId1, userName, "phone", "userName", "website")
        val usersList = listOf(user1)
        val expectedResult = PostItemModel(postTitle, postBody, userName, commentsList.size)

        // when
        runBlocking { whenever(repostitory.fetchComments()) }.thenReturn(commentsList)
        runBlocking { whenever(repostitory.fetchPosts()) }.thenReturn(postsList)
        runBlocking { whenever(repostitory.fetchUsers()) }.thenReturn(usersList)
        val result = runBlocking { interactor.fetchAll() }

        // then
        assert(result.size == 1)
        assertEquals(expectedResult, result[0])
    }

    @Test
    fun verifyForValidResponsesWeGetOneResultNoMatchingUser() {
        // given
        val id1 = 1
        val comment1 = Comment("commentBody", "comment@email.com", id1, "commentName", 1)
        val commentsList = listOf(comment1)
        val postBody = "postBody"
        val postTitle = "postTitle"
        val userId1 = 11
        val post1 = Post(postBody, id1, postTitle, userId1)
        val postsList = listOf(post1)
        val address = mock<Address>()
        val company = mock<Company>()
        val userName = "name"
        val user1 = User(address, company, "user@email.com", id1, userName, "phone", "userName", "website")
        val usersList = listOf(user1)
        val expectedResult = PostItemModel(postTitle, postBody, "", commentsList.size)

        // when
        runBlocking { whenever(repostitory.fetchComments()) }.thenReturn(commentsList)
        runBlocking { whenever(repostitory.fetchPosts()) }.thenReturn(postsList)
        runBlocking { whenever(repostitory.fetchUsers()) }.thenReturn(usersList)
        val result = runBlocking { interactor.fetchAll() }

        // then
        assert(result.size == 1)
        assertEquals(expectedResult, result[0])
    }

    @Test
    fun verifyForValidResponsesWeGetOneResultButNoComments() {
        // given
        val id1 = 1
        val commentsList = emptyList<Comment>()
        val postBody = "postBody"
        val postTitle = "postTitle"
        val userId1 = 11
        val post1 = Post(postBody, id1, postTitle, userId1)
        val postsList = listOf(post1)
        val address = mock<Address>()
        val company = mock<Company>()
        val userName = "name"
        val user1 = User(address, company, "user@email.com", userId1, userName, "phone", "userName", "website")
        val usersList = listOf(user1)
        val expectedResult = PostItemModel(postTitle, postBody, userName, 0)

        // when
        runBlocking { whenever(repostitory.fetchComments()) }.thenReturn(commentsList)
        runBlocking { whenever(repostitory.fetchPosts()) }.thenReturn(postsList)
        runBlocking { whenever(repostitory.fetchUsers()) }.thenReturn(usersList)
        val result = runBlocking { interactor.fetchAll() }

        // then
        assert(result.size == 1)
        assertEquals(expectedResult, result[0])
    }
}