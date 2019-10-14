package net.barbux.creatures2d;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;

import com.diffblue.deeptestutils.Reflector;
import com.diffblue.deeptestutils.mock.DTUMemberMatcher;
import net.barbux.creatures2d.SummaryViewController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

@RunWith(PowerMockRunner.class)
public class SummaryViewControllerTest {

  @Test
  public void staticInitOutputVoid() throws InvocationTargetException {
    final Object constructed = Reflector.getInstance("net.barbux.creatures2d.SummaryViewController$CurrentMode");

    // The method returns void, testing that no exception is thrown
  }
}
