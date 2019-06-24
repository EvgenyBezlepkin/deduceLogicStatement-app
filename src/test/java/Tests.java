

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import static com.sun.javafx.scene.control.skin.Utils.getResource;


public class Tests extends Assert {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


//    @Test       // на все эти идентификаторы вылетает Exception
//    public void testIdentificators1() throws parcer.ParserException {
//        parcer.Parser p = new parcer.Parser();
//        ArrayList<String> list = new ArrayList();
//
//        list.add("D && F -> K");
//        p.validateLine(list);
//        list.clear();
//
//        list.add("^gh && F -> K");
//        p.validateLine(list);
//        list.clear();
//
//        list.add("gh& && F -> K");
//        p.validateLine(list);
//        list.clear();
//
//        p.checkIdentificator("_");
//        p.checkIdentificator("3");
//        p.checkIdentificator("_|||");
//        p.checkIdentificator("__");
//        p.checkIdentificator("___");
//        p.checkIdentificator("_12H||");
//        p.checkIdentificator("_333");
//        p.checkIdentificator(",");
//    }
//
//
//    @Test       // валидные идентификаторы
//    public void testIdentificators2() throws Exception {
//        parcer.Parser p = new parcer.Parser(listOfLogics, listOfResults);
//        p.checkIdentificator("d");   // valid
//        p.checkIdentificator("g2h");   // valid
//        p.checkIdentificator("g_h");   // valid
//        p.checkIdentificator("_gh");  // valid

//    }


    @Test
    public void test1() {                           // валидный файл, тест на логику

        File s = new File(getClass().getResource("first.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("ZZ, Df, S, D, H, L, O", outContent.toString());
    }

    @Test
    public void test21() {                           // валидный файл посложнее, тест на логику

        File s = new File(getClass().getResource("valid.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("ZZ, _O3, S, ZZ2, D, _yu6, H, Df7g_u8", outContent.toString());
    }

    @Test
    public void test000() {                           // пустой путь

        Main.main(new String[]{});

        assertEquals("Missing argument", outContent.toString());
    }

    @Test
    public void test00() {                           // неправильный путь

        File s = new File("про");
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Error when reading file: C:\\Users\\Evgeniy.Bezlepkin\\Desktop\\EvgenyDeduce2\\про"
                , outContent.toString());
    }

    @Test
    public void test2() {                           // пустой файл

        File s = new File(getClass().getResource("empty_file.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: missing or wrong separator", outContent.toString());
    }

    @Test
    public void test3() {                           // ошибка в правилах: правила отсутствуют

        File s = new File(getClass().getResource("rule_error_emptyRule.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: missing rules", outContent.toString());
    }

    @Test
    public void test4() {                           // ошибка в фактах: факты отсутствуют

        File s = new File(getClass().getResource("facts_error_emptyFacts.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: missing facts", outContent.toString());
    }

    @Test
    public void test5() {                           // невалидный файл: нет разделителя

        File s = new File(getClass().getResource("absent_separator.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: invalid rule syntax", outContent.toString());
    }

//    @Test
//    public void test7() {                           // ошибка в данных - неверный логический символ
//
//        File s = new File(getClass().getResource("rules_error.txt").getFile());
//        Main.main(new String[]{s.getAbsolutePath()});
//
//        assertEquals("Invalid file: Wrong value S|  K", outContent.toString());
//    }

    @Test
    public void test8() {                           // ошибка в данных - отсутствует ->

        File s = new File(getClass().getResource("missing_pointer.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: invalid rule syntax", outContent.toString());
    }

    @Test
    public void test9() {                           // ошибка в данных - неправильный разделитель

        File s = new File(getClass().getResource("wrong_separator.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: invalid rule syntax", outContent.toString());
    }

    @Test
    public void test10() {                           // ошибка в данных - перепутаны правила и факты

        File s = new File(getClass().getResource("wrong_rules_facts.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: Wrong value Df || Yg && jhkl->ZZ", outContent.toString());
    }

    @Test
    public void test11() {                           // ошибка в правилах - пустая линия

        File s = new File(getClass().getResource("missing_Line_rules.txt").getFile());
        Main.main(new String[]{s.getAbsolutePath()});

        assertEquals("Invalid file: invalid rule syntax", outContent.toString());
    }

}




