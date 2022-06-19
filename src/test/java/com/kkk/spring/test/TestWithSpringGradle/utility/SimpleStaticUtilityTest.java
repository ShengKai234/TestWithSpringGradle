package com.kkk.spring.test.TestWithSpringGradle.utility;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleStaticUtilityTest {

    @Test
    void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully() {
        assertThat(SimpleStaticUtility.name()).isEqualTo("Baeldung");

        // This method returns a MockedStatic object for our type, which is a scoped mock object.
        // It's important to note that scoped mocks must be closed by the entity that activates the mock.
        try (MockedStatic<SimpleStaticUtility> simpleStaticUtility = Mockito.mockStatic(SimpleStaticUtility.class)) {
            simpleStaticUtility.when(SimpleStaticUtility::name).thenReturn("Eugen");
            assertThat(SimpleStaticUtility.name()).isEqualTo("Eugen");
        }

        assertThat(SimpleStaticUtility.name()).isEqualTo("Baeldung");
    }

    @Test
    void givenStaticMethodWithArgs_whenMocked_thenReturnsMockSuccessfully() {
        assertThat(SimpleStaticUtility.range(2, 6)).containsExactly(2, 3, 4, 5);
        System.out.println(SimpleStaticUtility.range(2, 6));
        try (MockedStatic<SimpleStaticUtility> simpleStaticUtility = Mockito.mockStatic(SimpleStaticUtility.class)) {
            simpleStaticUtility.when(() -> SimpleStaticUtility.range(2, 6))
                    .thenReturn(Arrays.asList(10, 11, 12));
            assertThat(SimpleStaticUtility.range(2, 6)).containsExactly(10, 11, 12);
        }

        assertThat(SimpleStaticUtility.range(2, 6)).containsExactly(2, 3, 4, 5);
    }
}