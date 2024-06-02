package org.example.userinterface;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInterfaceTest {

//    @Mock
    Scanner mockScanner = Mockito.mock(Scanner.class);

    @InjectMocks
    UserInterface userInterface;

    @BeforeEach
    void setup() throws Exception {
        userInterface = new UserInterface();
        Field scannerField = UserInterface.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(userInterface, mockScanner);
    }

    @Test
    void takeUserOptionReturnsCorrectInt() {
        when(mockScanner.nextLine()).thenReturn("3").thenReturn("2").thenReturn("1").thenReturn("0");
        assertAll(() -> {
            assertEquals(3, userInterface.takeUserOption());
            assertEquals(2, userInterface.takeUserOption());
            assertEquals(1, userInterface.takeUserOption());
            assertEquals(0, userInterface.takeUserOption());
        });
    }

    @Test
    void takeUserOptionPromptsAgainAfterInvalidInput() {
        when(mockScanner.nextLine()).thenReturn("5").thenReturn("3");
        assertEquals(3, userInterface.takeUserOption());
        verify(mockScanner, times(2)).nextLine();
    }


}