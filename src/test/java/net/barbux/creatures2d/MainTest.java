package net.barbux.creatures2d;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

import net.barbux.creatures2d.Main;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javafx.application.Application;

@RunWith(PowerMockRunner.class)
public class MainTest {

  @PrepareForTest(Main.class)
  @Test
  public void constructorOutputVoid() throws Exception {

    PowerMockito.suppress(PowerMockito.constructorsDeclaredIn(Application.class));
    final Main actual = new Main();

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest(Main.class)
  @Test
  public void mainInput0OutputVoid() throws Exception {

    PowerMockito.mockStatic(Main.class);
    final String[] args = { };
    Main.main(args);

    // The method returns void, testing that no exception is thrown
  }

}
