package uk.co.tomek.jsonplaceholderdemoapp.ui

import android.content.Intent
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.tomek.jsonplaceholderdemoapp.R
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel
import com.zhuinden.espressohelper.*

@SmallTest
@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(DetailsActivity::class.java, false, false)

    @Test
    fun verifyCorrectDetailsAreDisplayed() {
        // given
        val testTitle = "Title"
        val testBody = "Body"
        val testUser = "User"
        val testCount = 99
        val testPost = PostItemModel(testTitle, testBody, testUser, testCount)
        val launchIntent = Intent()
        launchIntent.putExtra(DetailsActivity.KEY_POST_DETAILS, testPost)

        // when
        activityTestRule.launchActivity(launchIntent)

        // then
        R.id.text_view_post_title.checkIsVisible()
        R.id.text_view_post_title.checkHasText(testTitle)
        R.id.text_view_post_body.checkIsVisible()
        R.id.text_view_post_body.checkHasText(testBody)
        R.id.text_view_user_name.checkIsVisible()
        R.id.text_view_user_name.checkHasText(testUser)
        R.id.text_view_counter.checkIsVisible()
        R.id.text_view_counter.checkHasText(testCount.toString())
    }

}