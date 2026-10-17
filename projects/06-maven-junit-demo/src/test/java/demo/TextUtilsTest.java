package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TextUtilsTest {

    @Test
    void palindromeIgnoresCaseAndPunctuation() {
        assertTrue(TextUtils.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(TextUtils.isPalindrome("race a car"));
    }

    @Test
    void palindromeNullIsFalse() {
        assertFalse(TextUtils.isPalindrome(null));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t\n"})
    void wordCountOfBlankIsZero(String s) {
        assertEquals(0, TextUtils.wordCount(s));
    }

    @Test
    void wordCountNormal() {
        assertEquals(3, TextUtils.wordCount("  hello   big world "));
    }
}
