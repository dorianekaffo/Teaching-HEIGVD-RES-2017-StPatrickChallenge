package ch.heigvd.res.stpatrick;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * 
 * @author Olivier Liechti
 */
public class Application {

  IStreamProcessorsFactory processorsFactory = new StreamProcessorsFactory();
  
  public IStreamProcessorsFactory getStreamProcessorsFactory() {
    return processorsFactory;
  }

  IStreamDecoratorController getStreamDecoratorController() {
    return new IStreamDecoratorController() {
      @Override
      public Reader decorateReader(Reader inputReader) {
        return new Reader() {
          Reader decoratedReader = inputReader;

          @Override
          public int read(char[] chars, int i, int i1) throws IOException {
            int pos = i;
            for(;i+i1 <= chars.length && pos < i+i1; pos++) {
              int c = decoratedReader.read();

              if(c == -1) {
                return -1;
              }
              else if(!Character.toString((char)c).equalsIgnoreCase("a")) {
                chars[pos] = (char) c;
              }
              else {
                pos--;
              }
            }

            return pos-i;
          }

          @Override
          public void close() throws IOException {
            decoratedReader.close();
          }
        };
      }

      @Override
      public Writer decorateWriter(Writer outputWriter) {
        return new Writer() {
          Writer decoratedWriter = outputWriter;

          @Override
          public void write(char[] chars, int i, int i1) throws IOException {
            decoratedWriter.write(chars, i, i1);
          }

          @Override
          public void flush() throws IOException {
            decoratedWriter.close();
          }

          @Override
          public void close() throws IOException {
            decoratedWriter.close();
          }
        };
      }
    };
  }
}
