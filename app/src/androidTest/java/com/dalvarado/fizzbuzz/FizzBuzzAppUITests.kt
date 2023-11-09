package com.dalvarado.fizzbuzz

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.SemanticsMatcher
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
import com.dalvarado.fizzbuzz.view.FizzBuzzApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigInteger

class FizzBuzzAppUITests {
    private lateinit var firstIntegerTextFieldMatcher: SemanticsMatcher
    private lateinit var firstWordTextFieldMatcher: SemanticsMatcher
    private lateinit var secondIntegerTextFieldMatcher: SemanticsMatcher
    private lateinit var secondWordTextFieldMatcher: SemanticsMatcher
    private lateinit var limitTextFieldMatcher: SemanticsMatcher
    private lateinit var submitButtonMatcher: SemanticsMatcher
    private val expectedOutputList =
        listOf("1", "2", "fizz", "4", "buzz", "fizz", "7", "8", "fizz", "buzz", "11", "fizz", "13", "14", "buzz")

    @get:Rule
    val testRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        testRule.setContent { FizzBuzzApp() }
        firstIntegerTextFieldMatcher = matchTextField(label = R.string.first_integer_input_label)
        firstWordTextFieldMatcher = matchTextField(label = R.string.first_word_input_label)
        secondIntegerTextFieldMatcher = matchTextField(label = R.string.second_integer_input_label)
        secondWordTextFieldMatcher = matchTextField(label = R.string.second_word_input_label)
        limitTextFieldMatcher = matchTextField(label = R.string.limit_input_label)
        submitButtonMatcher = matchButton(text = R.string.submit_button_label)
    }

    private fun matchTextField(label: Int): SemanticsMatcher {
        return hasText(
            testRule.activity.getString(label),
        ) and hasSetTextAction()
    }

    private fun matchButton(text: Int): SemanticsMatcher {
        return hasText(
            testRule.activity.getString(text),
        ) and hasClickAction()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstIntegerInputIsEmpty() {
        val errorMessage = testRule.activity.getString(R.string.field_empty_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput("   ")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstWordInputIsEmpty() {
        val errorMessage = testRule.activity.getString(R.string.field_empty_error_message)
        testRule.onNode(firstWordTextFieldMatcher).performTextInput("   ")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondIntegerInputIsEmpty() {
        val errorMessage = testRule.activity.getString(R.string.field_empty_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("   ")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondWordInputIsEmpty() {
        val errorMessage = testRule.activity.getString(R.string.field_empty_error_message)
        testRule.onNode(secondWordTextFieldMatcher).performTextInput("   ")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfLimitInputIsEmpty() {
        val errorMessage = testRule.activity.getString(R.string.field_empty_error_message)
        testRule.onNode(limitTextFieldMatcher).performTextInput("   ")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstIntegerInputReceivesDecimal() {
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput("5.8")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstIntegerInputReceivesNegativeNumber() {
        val errorMessage = testRule.activity.getString(R.string.non_positive_number_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput("-12")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstIntegerInputReceivesLargeNumber() {
        val errorMessage = testRule.activity.getString(R.string.number_too_large_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput((BigInteger(Int.MAX_VALUE.toString()) + BigInteger.ONE).toString())
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfFirstIntegerInputReceivesNonNumericInput() {
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
        testRule.onNode(firstIntegerTextFieldMatcher).performTextInput(" -")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysNoErrorMessageIfFirstIntegerInputIsValid() {
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
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
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("5.8")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondIntegerInputReceivesNonNumericInput() {
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput(" -")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysNoErrorMessageIfSecondIntegerInputIsValid() {
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("58")
        testRule.onNodeWithText(errorMessage).assertDoesNotExist()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondIntegerInputReceivesNegativeNumber() {
        val errorMessage = testRule.activity.getString(R.string.non_positive_number_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput("-12")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfSecondIntegerInputReceivesLargeNumber() {
        val errorMessage = testRule.activity.getString(R.string.number_too_large_error_message)
        testRule.onNode(secondIntegerTextFieldMatcher).performTextInput((BigInteger(Int.MAX_VALUE.toString()) + BigInteger.ONE).toString())
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
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
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
        testRule.onNode(limitTextFieldMatcher).performTextInput("5.8")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysErrorMessageIfLimitInputReceivesNonNumericInput() {
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
        testRule.onNode(limitTextFieldMatcher).performTextInput(" -")
        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun appDisplaysNoErrorMessageIfLimitInputIsValid() {
        val errorMessage = testRule.activity.getString(R.string.not_a_number_error_message)
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
