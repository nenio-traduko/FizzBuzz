package com.dalvarado.fizzbuzz

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dalvarado.fizzbuzz.view.FizzBuzzApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FizzBuzzAppUITests {
    private val firstIntegerTextFieldMatcher = hasText("First Integer") and hasSetTextAction()
    private val firstWordTextFieldMatcher = hasText("First Word") and hasSetTextAction()
    private val secondIntegerTextFieldMatcher = hasText("Second Integer") and hasSetTextAction()
    private val secondWordTextFieldMatcher = hasText("Second Word") and hasSetTextAction()
    private val limitTextFieldMatcher = hasText("Limit") and hasSetTextAction()
    private val submitButtonMatcher = hasText("Submit") and hasClickAction()
    private val expectedOutputList =
        listOf("1", "2", "fizz", "4", "buzz", "fizz", "7", "8", "fizz", "buzz", "11", "fizz", "13", "14", "buzz")

    @get:Rule
    val testRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        testRule.setContent { FizzBuzzApp() }
    }

    @Test
    fun appDisplaysErrorMessageIfFirstIntegerInputReceivesDecimal() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput("5.8")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstIntegerInputReceivesNonNumericInput() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput(" -")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysNoErrorMessageIfFirstIntegerInputIsValid() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput("58")
        testRule.onNodeWithText(errorMessage).assertDoesNotExist()
    }

    @Test
    fun appDisplaysNoErrorMessageIfFirstWordInputIsNumeric() {
        val errorMessage = testRule.activity.getString(R.string.invalid_word_error_message)
        testRule.onNode(firstWordTextFieldMatcher).performTextInput("48")
        testRule.onNodeWithText(errorMessage).assertDoesNotExist()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstWordInputIsSpecialCharacters() {
        val errorMessage = testRule.activity.getString(R.string.invalid_word_error_message)
        testRule.onNode(firstWordTextFieldMatcher).performTextInput("()_+")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondIntegerInputReceivesDecimal() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("5.8")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondIntegerInputReceivesNonNumericInput() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput(" -")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysNoErrorMessageIfSecondIntegerInputIsValid() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("58")
        testRule.onNodeWithText(errorMessage).assertDoesNotExist()
    }

    @Test
    fun appDisplaysNoErrorMessageIfSecondWordInputIsNumeric() {
        val errorMessage = testRule.activity.getString(R.string.invalid_word_error_message)
        testRule.onNode(secondWordTextFieldMatcher).performTextInput("48")
        testRule.onNodeWithText(errorMessage).assertDoesNotExist()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondWordInputIsSpecialCharacters() {
        val errorMessage = testRule.activity.getString(R.string.invalid_word_error_message)
        testRule.onNode(secondWordTextFieldMatcher).performTextInput("()_+")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfLimitInputReceivesDecimal() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(limitTextFieldMatcher).performTextInput("5.8")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfLimitInputReceivesNonNumericInput() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(limitTextFieldMatcher).performTextInput(" -")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysNoErrorMessageIfLimitInputIsValid() {
        val errorMessage = testRule.activity.getString(R.string.invalid_integer_error_message)
        testRule.onNode(limitTextFieldMatcher).performTextInput("58")
        testRule.onNodeWithText(errorMessage).assertDoesNotExist()
    }

    @Test
    fun appDisplaysADisabledSubmitButtonIfNoInputIsPresent() {
        testRule.onNode(submitButtonMatcher).assertIsNotEnabled()
    }

    @Test
    fun appDisplaysAnEnabledSubmitButtonIfAllInputIsPresentAndValid() {
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput("3")
        testRule.onNode(firstWordTextFieldMatcher).performTextInput("fizz")
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("5")
        testRule.onNode(secondWordTextFieldMatcher).performTextInput("buzz")
        testRule.onNode(limitTextFieldMatcher).performTextInput("16")
        testRule.onNode(submitButtonMatcher).assertIsEnabled()
    }

    @Test
    fun appDisplaysExpectedListWhenSubmitButtonIsPressedOnValidInput() {
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput("3")
        testRule.onNode(firstWordTextFieldMatcher).performTextInput("fizz")
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("5")
        testRule.onNode(secondWordTextFieldMatcher).performTextInput("buzz")
        testRule.onNode(limitTextFieldMatcher).performTextInput("16")
        testRule.onNode(submitButtonMatcher).performClick()
        testRule.onRoot().printToLog("APP_NODES")
        expectedOutputList.forEach { element ->
            testRule.onNode(
                hasScrollAction(),
            ).assert(
                hasAnyChild(hasTextExactly(element)),
            )
        }
    }
}
