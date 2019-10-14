package net.barbux.creatures2d;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.diffblue.deeptestutils.Reflector;
import com.diffblue.deeptestutils.mock.DTUMemberMatcher;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Parser;
import net.barbux.creatures2d.SerialUtil;
import net.barbux.creatures2d.proto.Creatures.Generation;
import net.barbux.creatures2d.proto.Creatures.Results;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.expectation.PowerMockitoStubber;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

@RunWith(PowerMockRunner.class)
public class SerialUtilTest {

  @Test
  public void constructorOutputNotNull() {
    final SerialUtil actual = new SerialUtil();
    Assert.assertNotNull(actual);
  }

  @PrepareForTest({FileInputStream.class, CodedInputStream.class, SerialUtil.class})
  @Test
  public void readFileInputNotNullNotNullNotNullOutputVoid() throws Exception, InvocationTargetException {

    PowerMockito.mockStatic(CodedInputStream.class);
    final String fileName = "foo";
    final Consumer<Generation> generationConsumer = (Consumer<Generation>) Reflector.getInstance("java.util.function.Consumer");
    final Consumer<Results> resultsConsumer = (Consumer<Results>) Reflector.getInstance("java.util.function.Consumer");
    final FileInputStream fileInputStream = PowerMockito.mock(FileInputStream.class);
    Reflector.setField(fileInputStream, "closed", false);
    Reflector.setField(fileInputStream, "path", "foo");
    PowerMockito.whenNew(FileInputStream.class).withParameterTypes(String.class).withArguments(or(isA(String.class), isNull(String.class))).thenReturn(fileInputStream);
    final CodedInputStream codedInputStream = PowerMockito.mock(CodedInputStream.class);
    final Method isAtEndMethod = DTUMemberMatcher.method(CodedInputStream.class, "isAtEnd");
    PowerMockito.doReturn(true).when(codedInputStream, isAtEndMethod).withNoArguments();
    final Method newInstanceMethod = DTUMemberMatcher.method(CodedInputStream.class, "newInstance", InputStream.class);
    PowerMockito.doReturn(codedInputStream).when(CodedInputStream.class, newInstanceMethod).withArguments(or(isA(InputStream.class), isNull(InputStream.class)));
    SerialUtil.readFile(fileName, generationConsumer, resultsConsumer);

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest({FileInputStream.class, CodedInputStream.class, SerialUtil.class})
  @Test
  public void readFileInputNotNullNullNullOutputVoid() throws Exception {

    PowerMockito.mockStatic(CodedInputStream.class);
    final String fileName = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001";
    final Consumer generationConsumer = null;
    final Consumer resultsConsumer = null;
    final FileInputStream fileInputStream = PowerMockito.mock(FileInputStream.class);
    Reflector.setField(fileInputStream, "closed", false);
    Reflector.setField(fileInputStream, "path", "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001");
    PowerMockito.whenNew(FileInputStream.class).withParameterTypes(String.class).withArguments(or(isA(String.class), isNull(String.class))).thenReturn(fileInputStream);
    final CodedInputStream codedInputStream = PowerMockito.mock(CodedInputStream.class);
    final Method isAtEndMethod = DTUMemberMatcher.method(CodedInputStream.class, "isAtEnd");
    PowerMockito.doReturn(true).when(codedInputStream, isAtEndMethod).withNoArguments();
    final Method newInstanceMethod = DTUMemberMatcher.method(CodedInputStream.class, "newInstance", InputStream.class);
    PowerMockito.doReturn(codedInputStream).when(CodedInputStream.class, newInstanceMethod).withArguments(or(isA(InputStream.class), isNull(InputStream.class)));
    SerialUtil.readFile(fileName, generationConsumer, resultsConsumer);

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest({FileInputStream.class, CodedInputStream.class, SerialUtil.class})
  @Test
  public void readFileInputNullNullNotNullOutputVoid() throws Exception, InvocationTargetException {

    PowerMockito.mockStatic(CodedInputStream.class);
    final String fileName = null;
    final Consumer generationConsumer = null;
    final Consumer<Results> resultsConsumer = (Consumer<Results>) Reflector.getInstance("java.util.function.Consumer");
    final FileInputStream fileInputStream = PowerMockito.mock(FileInputStream.class);
    Reflector.setField(fileInputStream, "closed", false);
    Reflector.setField(fileInputStream, "path", null);
    PowerMockito.whenNew(FileInputStream.class).withParameterTypes(String.class).withArguments(or(isA(String.class), isNull(String.class))).thenReturn(fileInputStream);
    final CodedInputStream codedInputStream = PowerMockito.mock(CodedInputStream.class);
    final Method isAtEndMethod = DTUMemberMatcher.method(CodedInputStream.class, "isAtEnd");
    PowerMockito.doReturn(true).when(codedInputStream, isAtEndMethod).withNoArguments();
    final Method newInstanceMethod = DTUMemberMatcher.method(CodedInputStream.class, "newInstance", InputStream.class);
    PowerMockito.doReturn(codedInputStream).when(CodedInputStream.class, newInstanceMethod).withArguments(or(isA(InputStream.class), isNull(InputStream.class)));
    SerialUtil.readFile(fileName, generationConsumer, resultsConsumer);

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest({FileInputStream.class, CodedInputStream.class, SerialUtil.class})
  @Test
  public void readFileInputNullNullNullOutputVoid() throws Exception {

    PowerMockito.mockStatic(CodedInputStream.class);
    final String fileName = null;
    final Consumer generationConsumer = null;
    final Consumer resultsConsumer = null;
    final FileInputStream fileInputStream = PowerMockito.mock(FileInputStream.class);
    Reflector.setField(fileInputStream, "closed", false);
    Reflector.setField(fileInputStream, "path", null);
    PowerMockito.whenNew(FileInputStream.class).withParameterTypes(String.class).withArguments(or(isA(String.class), isNull(String.class))).thenReturn(fileInputStream);
    final CodedInputStream codedInputStream = PowerMockito.mock(CodedInputStream.class);
    final Method isAtEndMethod = DTUMemberMatcher.method(CodedInputStream.class, "isAtEnd");
    PowerMockito.doReturn(true).when(codedInputStream, isAtEndMethod).withNoArguments();
    final Method newInstanceMethod = DTUMemberMatcher.method(CodedInputStream.class, "newInstance", InputStream.class);
    PowerMockito.doReturn(codedInputStream).when(CodedInputStream.class, newInstanceMethod).withArguments(or(isA(InputStream.class), isNull(InputStream.class)));
    SerialUtil.readFile(fileName, generationConsumer, resultsConsumer);

    // The method returns void, testing that no exception is thrown
  }

}
