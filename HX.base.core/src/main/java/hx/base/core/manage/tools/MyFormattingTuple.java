package hx.base.core.manage.tools;

/**
 * @name: MyFormattingTuple
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/7/8 10:56
 */
public class MyFormattingTuple {

    static public MyFormattingTuple NULL = new MyFormattingTuple(null);

    private String message;
    private Throwable throwable;
    private Object[] argArray;

    public MyFormattingTuple(String message)
    {
        this(message, null, null);
    }

    public MyFormattingTuple(String message, Object[] argArray, Throwable throwable)
    {
        this.message = message;
        this.throwable = throwable;
        this.argArray = throwable == null ? argArray : trimmedCopy(argArray);
    }

    static Object[] trimmedCopy(Object[] argArray)
    {
        if (argArray == null || argArray.length == 0)
            throw new IllegalStateException("non-sensical empty or null argument array");
        final int trimemdLen = argArray.length - 1;
        Object[] trimmed = new Object[trimemdLen];
        System.arraycopy(argArray, 0, trimmed, 0, trimemdLen);
        return trimmed;
    }

    public String getMessage()
    {
        return message;
    }

    public Object[] getArgArray()
    {
        return argArray;
    }

    public Throwable getThrowable()
    {
        return throwable;
    }
}
