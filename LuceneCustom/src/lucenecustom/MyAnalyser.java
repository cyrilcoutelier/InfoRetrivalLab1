/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lucenecustom;

import java.io.Reader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.util.Version;

/**
 *
 * @author Cyril
 */
public class MyAnalyser extends Analyzer {

  Version matchVersion;
  public static final String[] ENGLISH_STOP_WORDS = {
    "test"
  };

  public MyAnalyser(Version version) {
    this.matchVersion = version;
  }

  @Override
  protected Analyzer.TokenStreamComponents createComponents(String fieldName, Reader reader) {
    Tokenizer src = new WhitespaceTokenizer(this.matchVersion, reader);
    TokenStream result = new StopFilter(this.matchVersion, src, StopFilter.makeStopSet(this.matchVersion, MyAnalyser.ENGLISH_STOP_WORDS));
    Analyzer.TokenStreamComponents tsc = new Analyzer.TokenStreamComponents(src, result);
    
    return tsc;
  }
}
