package com.mobile.meishang.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.protocol.HTTP;

import com.mobile.meishang.logger.MyLog;

/**
 * 
 * @Title:
 * @Description:������
 * @Author:11120500
 * @Since:2013-4-24
 * @Version:
 */
public class InputStreamUtils
{
    static final int BUFFER_SIZE = 1024;

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public static String InputStreamTOString(InputStream in)
    {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try
        {
            byte[] data = new byte[BUFFER_SIZE];
            int count = -1;
            while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
                outStream.write(data, 0, count);

            data = null;
            return new String(outStream.toByteArray(), HTTP.UTF_8);
        }
        catch (IOException e)
        {
            // e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != outStream)
                {
                    outStream.close();
                    outStream = null;
                }
                if (null != in)
                {
                    in.close();
                    in = null;
                }
            }
            catch (IOException e)
            {
                MyLog.e(e.getMessage());
            }
        }
        return null;

    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public static String InputStreamTOString(InputStream in, String encoding)
            throws Exception
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return new String(outStream.toByteArray(), encoding);
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public static InputStream StringTOInputStream(String in) throws Exception
    {
        ByteArrayInputStream is = new ByteArrayInputStream(
                in.getBytes(HTTP.UTF_8));
        return is;
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public static byte[] InputStreamTOByte(InputStream in)
    {
        byte[] data = new byte[BUFFER_SIZE];
        ByteArrayOutputStream outStream = null;
        try
        {
            outStream = new ByteArrayOutputStream();

            int count = -1;
            while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
                outStream.write(data, 0, count);
            data = null;
            return outStream.toByteArray();
        }
        catch (IOException e)
        {
            MyLog.e(e.getMessage());
        }
        finally
        {
            try
            {
                if (null != outStream)
                    outStream.close();
            }
            catch (IOException e)
            {
                MyLog.e(e.getMessage());
            }
        }
        return null;

    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public static InputStream byteTOInputStream(byte[] in) throws Exception
    {

        ByteArrayInputStream is = new ByteArrayInputStream(in);
        return is;
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public static String byteTOString(byte[] in) throws Exception
    {
        InputStream is = byteTOInputStream(in);
        return InputStreamTOString(is);
    }
}
