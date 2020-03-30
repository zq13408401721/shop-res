package com.mytouch;

import com.mytouch.abstracts.ChickenGame;
import com.mytouch.abstracts.ChildView;
import com.mytouch.abstracts.WangZheGame;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testStr(){
        String str1 = "abc";
        String str2 = new String("abc");
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        if(str1.equals(str2) == true){
            System.out.print("is true");
        }else{
            System.out.print("is false");
        }

    }


    @Test
    public void testFun(){
        //王者的游戏
        WangZheGame wzGame = new WangZheGame();
        //吃鸡的游戏
        ChickenGame chickenGame = new ChickenGame();
    }
}