/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lucenecustom;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cyril
 */
public class MailParser {

  public static final String FROM_NAME = "From:";
  public static final String SUBJECT_NAME = "Subject:";
  public static final String DATE_NAME = "Date:";
  private List<String> lines = new ArrayList<>();
  private BufferedReader br;
  private String date;
  private String content;
  private String subject;
  private String from;

  public MailParser(BufferedReader br) throws IOException {
    this.br = br;
    this.parse();
  }

  private void parse() throws IOException {
    extractLines();
    extractDate();

  }

  private void extractLines() throws IOException {
    String line;
    do {
      line = this.br.readLine();
      if (line != null) {
        this.lines.add(line);
      }
    } while (line != null);
  }

  private void extractDate() {
    int i = 0;
    while (i < lines.size()) {
      String line = lines.get(i);
      if (line.startsWith(SUBJECT_NAME)) {
        subject = line.substring(SUBJECT_NAME.length()).trim();
      } else if (line.startsWith(DATE_NAME)) {
        date = line.substring(DATE_NAME.length()).trim();
      } else if (line.startsWith(FROM_NAME)) {
        from = line.substring(FROM_NAME.length()).trim();
      } else if (line.isEmpty()) {
        while (i < lines.size()) {
          content += lines.get(i);
          ++i;
        }
      }
      ++i;
    }
  }

  public String getDate() {
    return date;
  }

  public String getContent() {
    return content;
  }

  public String getSubject() {
    return subject;
  }

  public String getFrom() {
    return from;
  }
}
