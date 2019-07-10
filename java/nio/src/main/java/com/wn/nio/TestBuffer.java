package com.wn.nio;

import org.testng.annotations.Test;

import java.nio.ByteBuffer;

/**
 * Created by wangning on 2017/3/27.
 * 一、缓冲区（Buffer）：在Java NIO中负责数据的存取。缓冲区就是数组，用于存储不同的数据类型的数据
 * <p>
 * 根据数据类型的不同（boolean除外），提供相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * <p>
 * 上述缓冲区的管理方式几乎一致，通过allocate() 获取缓冲区
 * <p>
 * 二、缓冲区存储数据的两个核心方法：
 * put(): 存入数据到缓存区中
 * get(): 获取缓冲区中的数据
 * <p>
 * 三、缓冲区的四个核心属性：
 * capacity：容量，表示缓冲区最大存储数据的容量，一旦声明，不容更改
 * limit：界限，表示缓冲区中可以操作数据的大小（limit后数据不能进行读写）
 * position：位置，表示缓冲区正在操作数据的位置
 * mark：标记，表示记录当前position的位置。可以通过reset()恢复到mark的位置
 * <p>
 * 四个量之间的关系：
 * 0 <= mark <= position <= limit <=capacity
 * <p>
 * 四、直接缓冲区和非直接缓冲区
 * 非直接缓冲区： 通过allocate() 方法分配缓冲区，将缓冲区建立在JVM的内存中
 * 直接缓冲区： 通过allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存上，可以提高效率
 */
public class TestBuffer {

    public static void main(String[] args) {
//        testBuffer1();

        testMark();
    }

    public static void testBuffer1() {
        //1.分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("---------allocate--------");
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());

        //2. 通过put()往缓冲区中存放数据
        byteBuffer.put("abcdefg".getBytes());
        System.out.println("---------put--------");
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());

        //3.通过flip()切换成读模式
        byteBuffer.flip();
        System.out.println("---------flip--------");
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());


        //4.通过get()读取数据
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("---------get--------");
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());

        //5.通过rewind() 可重复读
        byteBuffer.rewind();
        System.out.println("---------rewind--------");
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());

        //6.clear(): 清空缓冲区，但是缓冲区的数据依然存在，只是出于“被遗忘”状态
        byteBuffer.clear();
        System.out.println("---------clear--------");
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());

        //证明byteBuffer中的数据依然存在
        System.out.println((char) byteBuffer.get());
    }

    public static void testMark() {
        String str = "I like programming.";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());

        buf.flip();

        byte[] det = new byte[buf.limit()];
        buf.get(det, 0, 3);
        System.out.println(new String(det, 0, 3));

        //mark()： 标记
        buf.mark();
        buf.get(det, 3, 3);
        System.out.println(new String(det, 3, 3));
        System.out.println(buf.position());

        //reset(): 恢复到mark的位置
        buf.reset();
        System.out.println(buf.position());

        //判断缓冲区是否还有剩余的数据
        if(buf.hasRemaining()){
            //获取缓冲去中可以操作的数量
            System.out.println(buf.remaining());
        }

    }

}
