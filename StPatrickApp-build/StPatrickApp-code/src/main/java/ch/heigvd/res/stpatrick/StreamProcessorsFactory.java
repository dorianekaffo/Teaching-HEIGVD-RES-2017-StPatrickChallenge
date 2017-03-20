package ch.heigvd.res.stpatrick;

/**
 * This class is responsible for providing different types of Stream Processors.
 * 
 * @author Olivier Liechti
 */
public class StreamProcessorsFactory implements IStreamProcessorsFactory {

  @Override
  public IStreamProcessor getProcessor() {
    return new BasicStreamProcessor();
  }

  @Override
  public IStreamProcessor getProcessor(String processorName) throws UnknownNameException {
    if(processorName.equalsIgnoreCase("e-remover")) {
      return new ERemoverStreamProcessor();
    }
    else {
      return new BasicStreamProcessor();
    }
  }

}
