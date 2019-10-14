package net.barbux.creatures2d;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

import net.barbux.creatures2d.SummaryViewer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javafx.application.Application;

@RunWith(PowerMockRunner.class)
public class SummaryViewerTest {

  @PrepareForTest(SummaryViewer.class)
  @Test
  public void constructorOutputVoid() throws Exception {

    PowerMockito.suppress(PowerMockito.constructorsDeclaredIn(Application.class));
    final SummaryViewer actual = new SummaryViewer();

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest(SummaryViewer.class)
  @Test
  public void mainInput0OutputVoid() throws Exception {

    PowerMockito.mockStatic(SummaryViewer.class);
    final String[] args = { };
    SummaryViewer.main(args);

    // The method returns void, testing that no exception is thrown
  }

}
