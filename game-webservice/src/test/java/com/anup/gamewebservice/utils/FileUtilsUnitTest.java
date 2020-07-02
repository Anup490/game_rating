package com.anup.gamewebservice.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtilsUnitTest {

    @Test
    public void test_insertGame_whenFileHasContent() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("D:/Projects/Java/game_rating/game-webservice/src/test/resources/contentsfile.txt");

        byte[] data = FileUtils.toBytes(inputStream);

        Assert.assertTrue(new String(data).trim().equals("This is a test file with test content"));
    }

    @Test
    public void test_insertGame_whenFileHasNoContent() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("D:/Projects/Java/game_rating/game-webservice/src/test/resources/emptyfile.txt");

        byte[] data = FileUtils.toBytes(inputStream);

        Assert.assertEquals(0, data.length);
    }

    @Test
    public void test_insertGame_whenExceptionOccursOnReadingFile() {
        byte[] data = FileUtils.toBytes(null);

        Assert.assertEquals(0, data.length);
    }

}
