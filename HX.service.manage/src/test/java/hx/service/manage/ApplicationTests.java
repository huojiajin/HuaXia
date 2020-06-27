package hx.service.manage;

import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.tools.JsonTools;

import java.io.IOException;
import java.util.*;

//@SpringBootTest
//@RunWith(SpringRunner.class)
class ApplicationTests {

    public static void echoList(Collection<?> list)
    {
        echoList(list, 100);
    }

    public static void echoCollection(Collection<?> list)
    {
        echoList(list, 100);
    }

    public static void echoList(Collection<?> list, int echoSize)
    {
        if (list == null)
        {
            System.out.println("list is null");
            return;
        }
        System.out.println("\n==================List<?> Begin=============================");
        System.out.println("list size = " + list.size() + "\n");
        if (list.size() < echoSize)
        {
            for (Object obj : list)
            {
                echoObj(obj);
            }
        }
        else
        {
            Iterator<?> it = list.iterator();
            for (int i = 0; i < echoSize; i++)
            {
                Object obj = it.next();
                echoObj(obj);
            }
        }
        System.out.println("===================List<?> End===================================\n");
    }

    protected static void echoObj(Object obj)
    {
        if (obj instanceof Object[])
        {
            try
            {
                System.out.println("obj = " + JsonTools.toJsonStr(Arrays.asList((Object[]) obj)));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("obj = " + obj);
        }
    }

    public static void echoObjects(Object[] objs)
    {
        if (objs == null)
        {
            System.out.println("objs is null");
            return;
        }
        System.out.println("\n==================objs[] Begin=============================");
        System.out.println("list size = " + objs.length + "\n");
        if (objs.length < 100)
        {
            for (Object obj : objs)
            {
                System.out.println("obj = " + obj);
            }
        }
        System.out.println("===================objs[] End===================================\n");
    }

    public static void echoMap(Map<?, ?> map)
    {
        System.out.println("\n=====================Map<?,?> Begin=================================");
        System.out.println("map size = " + map.size() + "\n");
        if (map.size() < 100)
        {
            Set<?> set = map.entrySet();
            for (Object obj : set)
            {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) obj;
                System.out.println("key = " + entry.getKey() + " ; value = " + entry.getValue());
            }
        }
        System.out.println("========================Map<?,?> End============================\n");
    }

    public static void echoPagin(Pagination pg)
    {
        echo(pg.toString());
        echoList(pg.getResult());
    }

    public static void echo(Object obj)
    {
        if (obj instanceof Collection<?>)
        {
            echoCollection((Collection<?>) obj);
            return;
        }
        if (obj instanceof Map<?, ?>)
        {
            echoMap((Map<?, ?>) obj);
            return;
        }
        if (obj instanceof Pagination)
        {
            echoPagin((Pagination) obj);
            return;
        }
        if (obj instanceof Object[])
        {
            echoObjects((Object[]) obj);
            return;
        }
        System.out.println(obj);
    }

    public static void echo()
    {
        System.out.println();
    }

}
