/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lucenecustom;

import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author Cyril
 */
public class LuceneCustom {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {
    Version matchVersion = Version.LUCENE_40; // Substitute desired Lucene version for XY
    Analyzer analyzer = new MyAnalyser(matchVersion); // or any other analyzer
    try (TokenStream ts = analyzer.tokenStream("myfield", new StringReader("some text goes here"))) {
      OffsetAttribute offsetAtt = ts.addAttribute(OffsetAttribute.class);
      CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);

      ts.reset(); // Resets this stream to the beginning. (Required)
      
      while (ts.incrementToken()) {
        // Use AttributeSource.reflectAsString(boolean)
        // for token stream debugging.
        System.out.println("token: " + ts.reflectAsString(true));

        System.out.println("token start offset: " + termAttr.toString());
      }
      
      ts.end();   // Perform end-of-stream operations, e.g. set the final offset.
    }
  }
}
