import junit.framework.TestCase;
import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.english.EnglishLuceneMorphology;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

import java.io.IOException;
import java.util.List;

public class LuceneMorphologyTest extends TestCase {
    public void testLuceneMorphRus() throws IOException {
        LuceneMorphology russianLuceneMorphology =
                new RussianLuceneMorphology();
        List<String> wordBaseForms =
                russianLuceneMorphology.getNormalForms("леса");
        wordBaseForms.forEach(System.out::println);
    }
    public void testLuceneMorphEng() throws IOException {
        LuceneMorphology englishLuceneMorphology =
                new EnglishLuceneMorphology();
        List<String> wordBaseForms =
                englishLuceneMorphology.getNormalForms("forests");
        wordBaseForms.forEach(System.out::println);
    }
    public void testGetMorphInfoRus() throws IOException {
        LuceneMorphology luceneMorph =
                new RussianLuceneMorphology();
        List<String> wordBaseForms =
                luceneMorph.getMorphInfo("или");
        wordBaseForms.forEach(System.out::println);
    }
    public void testGetMorphInfoEng() throws IOException {
        LuceneMorphology luceneMorph =
                new EnglishLuceneMorphology();
        List<String> wordBaseForms =
                luceneMorph.getMorphInfo("and");
        wordBaseForms.forEach(System.out::println);
    }
}
